package org.order.domain.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Param;
import org.order.domain.entity.version.VersionParam;
import org.order.domain.event.ConfigChangeDomainEvent;
import org.order.domain.event.DomainEventEnum;
import org.order.domain.event.DomainEventPublisher;
import org.order.domain.event.VersionConfigChangeDomainEvent;
import org.order.domain.repository.ParamRepository;
import org.order.domain.repository.RuleItemRepository;
import org.order.domain.repository.version.VersionParamRepository;
import org.order.domain.repository.version.VersionRuleItemRepository;
import org.order.domain.validator.ExpressionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * 变量聚合服务
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Slf4j
@Component
public class ParamAggregateService {


    @Autowired
    private ParamRepository paramRepository;

    @Autowired
    private VersionParamRepository versionParamRepository;

    @Autowired
    private RuleItemRepository ruleItemRepository;

    @Autowired
    private VersionRuleItemRepository versionRuleItemRepository;

    @Autowired
    private ExpressionValidator paramExpressionValidator;

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public void create(Param param) {
        // 没有同名变量
        paramRepository.checkDuplicateName(param.getName());

        // 初始化变量版本号
        param.initVersion();

        // 保存数据
        paramRepository.save(param);

        // 发送变量发布领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.PARAM_CREATED_EVENT, param.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Param param) {
        // 变量存在
        Param oldParam = paramRepository.findByIdWithEx(param.getId());

        // 要修改的变量名称不存在
        paramRepository.checkDuplicateName(param.getName(), param.getId());

        // 保存数据
        oldParam.change(param.getName(), param.getDescription(), param.getExpression(), param.getResultType());
        paramRepository.save(oldParam);

        // 发送变量更新领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.PARAM_UPDATED_EVENT, param.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long paramId) {
        paramRepository.checkParamExist(paramId);

        // 判断变量是否被规则引用,如果被引用,不能删除
        ruleItemRepository.checkRefByRule(RuleItemTypeEnum.PARAM, paramId);

        // 判断变量是否被版本规则引用,如果被引用,不能删除
        versionRuleItemRepository.checkRefByVersionRule(RuleItemTypeEnum.PARAM, paramId);

        // 删除版本变量
        versionParamRepository.deleteByParamId(paramId);

        // 删除变量
        paramRepository.deleteById(paramId);

        // 发送变量删除领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.PARAM_DELETED_EVENT, paramId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long paramId) {
        // 查询并判断变量是否存在,如果不存在,抛出异常
        Param param = paramRepository.findByIdWithEx(paramId);

        // 校验表达式, 校验表达式是否可以执行, 已发布的表达式必须是可执行的.
        paramExpressionValidator.validate(param.getExpression(), param.getResultType());

        // 新增版本变量
        VersionParam versionParam = new VersionParam(
                param.getId(),
                param.getName(),
                param.getDescription(),
                param.getExpression(),
                param.getResultType(),
                param.getVersion()
        );
        // 发布版本变量
        versionParam.active();
        versionParamRepository.save(versionParam);

        // 更新变量版本， 版本号+1
        param.versioned();
        paramRepository.save(param);

        // 发送版本变量发布领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_PARAM_ACTIVED_EVENT, versionParam.getParamId(), versionParam.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long paramId, Integer version) {
        // 查询并校验 version 版本的版本变量是否存在
        VersionParam versionParam = versionParamRepository.findByParamIdAndVersionWithEx(paramId, version);

        // 判断 version 版本变量是否被规则引用,如果被引用,则抛出异常
        ruleItemRepository.checkRefByRule(RuleItemTypeEnum.PARAM, paramId, version);

        // 判断 version 版本变量是否被版本规则引用,如果被引用,则抛出异常
        versionRuleItemRepository.checkRefByVersionRule(RuleItemTypeEnum.PARAM, paramId, version);

        // 删除版本变量
        versionParamRepository.deleteById(versionParam.getId());

        // 发送版本变量删除领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_PARAM_DELETED_EVENT, versionParam.getParamId(), versionParam.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long paramId, Integer version) {
        // 查询并校验 version 版本的版本变量是否存在
        VersionParam versionParam = versionParamRepository.findByParamIdAndVersionWithEx(paramId, version);

        // 判断 version 版本变量是否已发布, 已发布的版本变量, 不需要再次发布
        if (StatusEnum.isActive(versionParam.getStatus())) {
            log.error("version param has been actived. paramId:{}. version:{}", paramId, version);
            throw new CustomBusinessException(ErrorCode.VERSION_PARAM_ALREADY_ACTIVED);
        }

        // 校验表达式
        paramExpressionValidator.validate(versionParam.getExpression(), versionParam.getResultType());

        // 发布版本变量
        versionParam.active();
        versionParamRepository.save(versionParam);

        // 发送版本变量发布领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_PARAM_ACTIVED_EVENT, versionParam.getParamId(), versionParam.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void inactive(Long paramId, Integer version) {
        // 查询并校验 version 版本的版本变量是否存在
        VersionParam versionParam = versionParamRepository.findByParamIdAndVersionWithEx(paramId, version);

        // 判断 version 版本变量是否已下架, 已下架的版本变量, 不需要再次下架
        if (StatusEnum.isInactive(versionParam.getStatus())) {
            log.error("version param has been inactived. paramId:{}. version:{}", paramId, version);
            throw new CustomBusinessException(ErrorCode.VERSION_PARAM_ALREADY_INACTIVED);
        }

        // 判断version 版本变量是否被规则引用
        ruleItemRepository.checkRefByRule(RuleItemTypeEnum.PARAM, paramId, version);

        // 判断version 版本变量是否被已发布的版本规则引用
        versionRuleItemRepository.checkRefByActiveVersionRule(RuleItemTypeEnum.PARAM, paramId, version);

        versionParam.inactive();
        versionParamRepository.save(versionParam);

        // 发送版本变量下架领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_PARAM_INACTIVED_EVENT, versionParam.getParamId(), versionParam.getVersion()
        ));
    }


}
