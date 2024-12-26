package org.order.domain.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.FlowNodeTypeEnum;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.converter.RuleConverter;
import org.order.domain.entity.Rule;
import org.order.domain.entity.RuleItem;
import org.order.domain.entity.version.VersionRule;
import org.order.domain.entity.version.VersionRuleItem;
import org.order.domain.event.ConfigChangeDomainEvent;
import org.order.domain.event.DomainEventEnum;
import org.order.domain.event.DomainEventPublisher;
import org.order.domain.event.VersionConfigChangeDomainEvent;
import org.order.domain.repository.FlowNodeRepository;
import org.order.domain.repository.RuleItemRepository;
import org.order.domain.repository.RuleRepository;
import org.order.domain.repository.version.VersionFlowNodeRepository;
import org.order.domain.repository.version.VersionRuleItemRepository;
import org.order.domain.repository.version.VersionRuleRepository;
import org.order.domain.validator.RuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 规则聚合服务
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Slf4j
@Component
public class RuleAggregateService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleItemRepository ruleItemRepository;

    @Autowired
    private VersionRuleRepository versionRuleRepository;

    @Autowired
    private VersionRuleItemRepository versionRuleItemRepository;

    @Autowired
    private FlowNodeRepository flowNodeRepository;

    @Autowired
    private VersionFlowNodeRepository versionFlowNodeRepository;


    @Autowired
    private RuleValidator ruleValidator;

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public void create(RuleAggregate ruleAgg) {
        // 判断是否有同名规则
        ruleRepository.checkDuplicateName(ruleAgg.getName());

        // 新增规则
        Rule rule = new Rule(
            ruleAgg.getName(),
            ruleAgg.getDescription(),
            ruleAgg.getExpression(),
            ruleAgg.getResultType()
        );

        //- 初始化变量版本号
        rule.initVersion();
        ruleRepository.save(rule);

        // 保存规则子项
        ruleItemRepository.saveAll(RuleConverter.convertNewRuleItems(rule.getId(), ruleAgg.getItems()));

        // 发送规则创建领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.RULE_CREATED_EVENT, rule.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(RuleAggregate ruleAgg) {
        // 判断规则id是否存在,如果不存在,抛出异常
        Rule rule = ruleRepository.findByIdWithEx(ruleAgg.getId());

        // 判断是否有同名变量
        ruleRepository.checkDuplicateName(ruleAgg.getName(), ruleAgg.getId());

        // 更新规则
        rule.change(ruleAgg.getName(), ruleAgg.getDescription(), ruleAgg.getExpression(), ruleAgg.getResultType());
        rule.versioned();
        ruleRepository.save(rule);

        // 更新规则子项
        ruleItemRepository.deleteByRuleId(rule.getId());
        ruleItemRepository.saveAll(RuleConverter.convertNewRuleItems(rule.getId(), ruleAgg.getItems()));

        // 发送规则更新领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.RULE_UPDATED_EVENT, rule.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long ruleId) {
        // 判断规则是否存在, 如果不存在, 则抛出异常
        Rule rule = ruleRepository.findByIdWithEx(ruleId);

        List<RuleItem> ruleItems = ruleItemRepository.findRuleItemsByRuleIdWithEx(ruleId);

        // 判断规则子项所使用的变量/规则状态是否已发布，
        ruleItemRepository.checkRuleItemActive(ruleItems);

        // 校验规则
        ruleValidator.validate(rule, ruleItems);

        // 新增版本规则
        VersionRule versionRule = new VersionRule(
                rule.getId(),
                rule.getName(),
                rule.getDescription(),
                rule.getExpression(),
                rule.getResultType(),
                rule.getVersion()
        );
        versionRule.active();
        versionRuleRepository.save(versionRule);
        versionRuleItemRepository.saveAll(RuleConverter.convertNewVersionRuleItems(versionRule.getId(), ruleItems));

        rule.versioned();
        ruleRepository.save(rule);

        // 发送版本规则发布领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_RULE_ACTIVED_EVENT, rule.getId(), versionRule.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long ruleId, Integer version) {
        // 判断 version 版本规则是否存在, 如果不存在, 则抛出异常
        VersionRule versionRule = versionRuleRepository.findByRuleIdAndVersionWithEx(ruleId, version);

        // 判断 version 版本规则是否处于发布状态, 如果处于发布状态, 则抛出异常
        if (StatusEnum.isActive(versionRule.getStatus())) {
            log.error("version rule has been actived. ruleId:{}. version:{}", ruleId, version);
            throw new CustomBusinessException(ErrorCode.RULE_ALREADY_ACTIVED);
        }

        // 判断版本规则子项是否存在, 如果不存在, 则抛出异常
        List<VersionRuleItem> items = versionRuleItemRepository.findRuleItemsByVersionRuleIdWithEx(versionRule.getId());

        // 判断规则子项所使用的变量/规则状态是否已发布，如果存在引用未发布或删除的项(规则/变量)，则抛出异常
        versionRuleItemRepository.checkVersionRuleItemActive(items);

        // 校验规则
        ruleValidator.validate(versionRule, items);

        // 发布版本规则
        versionRule.active();
        versionRuleRepository.save(versionRule);

        // 发送版本规则发布领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_RULE_ACTIVED_EVENT, versionRule.getRuleId(), versionRule.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void inactive(Long ruleId, Integer version) {
        // 判断 version 版本规则是否存在, 如果不存在, 则抛出异常
        VersionRule versionRule = versionRuleRepository.findByRuleIdAndVersionWithEx(ruleId, version);

        // 判断 version 版本规则是否处于下架状态, 如果处于下架状态, 则抛出异常
        if (StatusEnum.isInactive(versionRule.getStatus())) {
            log.error("version rule has been inactived. ruleId:{}. version:{}", ruleId, version);
            throw new CustomBusinessException(ErrorCode.RULE_ALREADY_INACTIVED);
        }

        // 判断规则的引用关系
        // 判断 version 版本规则是否被其它已发布的版本规则引用,如果有，则不能下架
        versionRuleItemRepository.checkRefByActiveVersionRule(RuleItemTypeEnum.RULE, ruleId, version);

        // 判断 version 版本规则是否被其它规则引用,如果有，则不能下架
        ruleItemRepository.checkRefByRule(RuleItemTypeEnum.RULE, ruleId, version);

        // 判断 version 版本规则是否被其它已发布的版本流程引用，如果有，则不能下架
        versionFlowNodeRepository.checkRefByActiveVersionFlow(FlowNodeTypeEnum.RULE, ruleId, version);

        // 判断 version 版本规则是否被其它流程引用，如果有，则不能下架
        flowNodeRepository.checkRefByFlow(FlowNodeTypeEnum.RULE, ruleId, version);

        // 下架版本规则
        versionRule.inactive();
        versionRuleRepository.save(versionRule);

        // 发送版本规则下架领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_RULE_INACTIVED_EVENT, versionRule.getRuleId(), versionRule.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long ruleId) {
        // 判断规则是否存在, 如果不存在, 则抛出异常
        ruleRepository.checkRuleExist(ruleId);

        // 判断规则没有被其它规则引用, 如果有不能被删除
        ruleItemRepository.checkRefByRule(RuleItemTypeEnum.RULE, ruleId);

        // 判断规则没有被其它版本规则引用, 如果有不能被删除
        versionRuleItemRepository.checkRefByVersionRule(RuleItemTypeEnum.RULE, ruleId);

        // 判断规则没有被其它流程引用, 如果有不能被删除
        flowNodeRepository.checkRefByFlow(FlowNodeTypeEnum.RULE, ruleId);

        // 判断规则没有被其它版本流程引用, 如果有不能被删除
        versionFlowNodeRepository.checkRefByVersionFlow(FlowNodeTypeEnum.RULE, ruleId);

        // 删除规则
        ruleRepository.deleteById(ruleId);

        // 删除规则子项
        ruleItemRepository.deleteByRuleId(ruleId);

        // 删除版本规则及其子项
        Optional<List<VersionRule>> versionRulesOp = versionRuleRepository.findByRuleId(ruleId);
        versionRulesOp.ifPresent(versionRules -> {
            if (CollectionUtils.isNotEmpty(versionRules)) {
                return;
            }

            versionRules.forEach(versionRule -> {
                versionRuleRepository.deleteById(versionRule.getId());
                versionRuleItemRepository.deleteByVersionRuleId(versionRule.getId());
            });
        });

        // 发送规则删除领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.VERSION_RULE_DELETED_EVENT, ruleId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long ruleId, Integer version) {
        // 判断 version 版本规则是否存在, 如果不存在, 则抛出异常
        VersionRule versionRule = versionRuleRepository.findByRuleIdAndVersionWithEx(ruleId, version);

        // 判断当前 version 规则没有被其它规则引用, 如果有不能被删除
        ruleItemRepository.checkRefByRule(RuleItemTypeEnum.RULE, ruleId, version);

        // 判断当前 version 规则没有被其它版本规则引用, 如果有不能被删除
        versionRuleItemRepository.checkRefByVersionRule(RuleItemTypeEnum.RULE, ruleId, version);

        // 判断当前 version 规则没有被其它流程引用, 如果有不能被删除
        flowNodeRepository.checkRefByFlow(FlowNodeTypeEnum.RULE, ruleId, version);

        // 判断当前 version 规则没有被其它版本流程引用, 如果有不能被删除
        versionFlowNodeRepository.checkRefByVersionFlow(FlowNodeTypeEnum.RULE, ruleId, version);

        // 删除指定版本规则
        versionRuleRepository.deleteById(versionRule.getId());

        // 删除指定版本规则子项
        versionRuleItemRepository.deleteByVersionRuleId(versionRule.getId());

        // 发送规则删除领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_RULE_DELETED_EVENT, versionRule.getRuleId(), versionRule.getVersion()
        ));
    }
}
