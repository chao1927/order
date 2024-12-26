package org.order.domain.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.ResultTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Entry;
import org.order.domain.entity.version.VersionEntry;
import org.order.domain.event.ConfigChangeDomainEvent;
import org.order.domain.event.DomainEventEnum;
import org.order.domain.event.DomainEventPublisher;
import org.order.domain.event.VersionConfigChangeDomainEvent;
import org.order.domain.repository.EntryRepository;
import org.order.domain.repository.version.VersionEntryRepository;
import org.order.domain.repository.version.VersionFlowRepository;
import org.order.domain.validator.ExpressionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 入口聚合服务
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Slf4j
@Component
public class EntryAggregateService {


    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private VersionEntryRepository versionEntryRepository;

    @Autowired
    private VersionFlowRepository versionFlowRepository;

    @Autowired
    private ExpressionValidator expressionValidator;

    @Autowired
    private DomainEventPublisher domainEventPublisher;


    @Transactional(rollbackFor = Exception.class)
    public void create(Entry entry) {
        // 校验是否已有同名入口
        entryRepository.checkDuplicateName(entry.getName());

        // 初始化版本号
        entry.initVersion();
        entryRepository.save(entry);

        // 发布创建入口领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.ENTRY_CREATED_EVENT, entry.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Entry entry) {
        // 查询并判断入口是否存在
        Entry oldEntry = entryRepository.findByIdWithEx(entry.getId());

        // 校验是否已有同名入口
        entryRepository.checkDuplicateName(entry.getName(), entry.getId());

        // 更新入口信息
        oldEntry.change(
                entry.getName(),
                entry.getDescription(),
                entry.getExpression(),
                entry.getFlowId(),
                entry.getVersion()
        );

        entryRepository.save(entry);

        // 发布更新入口领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.ENTRY_UPDATED_EVENT, entry.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long entryId) {
        // 校验入口是否存在
        entryRepository.checkEntryExist(entryId);

        // 删除入口
        versionEntryRepository.deleteByEntryId(entryId);

        entryRepository.deleteById(entryId);

        // 发布删除入口领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.ENTRY_DELETED_EVENT, entryId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long entryId) {
        // 校验入口是否存在
        Entry entry = entryRepository.findByIdWithEx(entryId);

        // 校验表达式是否可以执行
        expressionValidator.validate(entry.getExpression(), ResultTypeEnum.BOOLEAN.getCode());

        // 校验流程是否已经发布
        versionFlowRepository.checkFlowActive(entry.getFlowId(), entry.getVersion());

        // 新增发布版本入口
        VersionEntry versionEntry = new VersionEntry(
                entry.getId(),
                entry.getName(),
                entry.getDescription(),
                entry.getExpression(),
                entry.getFlowId(),
                entry.getFlowVersion(),
                entry.getVersion()
        );
        // 激活发布版本入口
        versionEntry.active();
        versionEntryRepository.save(versionEntry);

        // 更新入口版本号
        entry.versioned();
        entryRepository.save(entry);

        // 发送发布版本入口领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_ACTIVED_EVENT, versionEntry.getEntryId(), versionEntry.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long entryId, Integer version) {
        // 查询并校验 version 版本的入口是否存在
        VersionEntry versionEntry = versionEntryRepository.findByEntryIdAndVersionWithEx(entryId, version);

        // 删除入口
        versionEntryRepository.deleteById(versionEntry.getId());

        // 发送删除版本入口领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_DELETED_EVENT, versionEntry.getEntryId(), versionEntry.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long entryId, Integer version) {
        // 查询并校验 version 版本的入口是否存在
        VersionEntry versionEntry = versionEntryRepository.findByEntryIdAndVersionWithEx(entryId, version);
        if (StatusEnum.isActive(versionEntry.getStatus())) {
            log.error("version entry has been actived. entryId:{}. version:{}", entryId, version);
            throw new CustomBusinessException(ErrorCode.ENTRY_ALREADY_ACTIVED);
        }

        // 校验表达式是否可以执行
        expressionValidator.validate(versionEntry.getExpression(), ResultTypeEnum.BOOLEAN.getCode());

        // 校验流程是否已经发布
        versionFlowRepository.checkFlowActive(versionEntry.getFlowId(), versionEntry.getVersion());

        versionEntry.active();
        versionEntryRepository.save(versionEntry);

        // 发送发布版本入口领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_ACTIVED_EVENT, versionEntry.getEntryId(), versionEntry.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void inactive(Long entryId, Integer version) {
        // 查询并校验 version 版本的入口是否存在
        VersionEntry versionEntry = versionEntryRepository.findByEntryIdAndVersionWithEx(entryId, version);

        if (StatusEnum.isInactive(versionEntry.getStatus())) {
            log.error("version entry has been inactived. entryId:{}. version:{}", entryId, version);
            throw new CustomBusinessException(ErrorCode.ENTRY_ALREADY_INACTIVED);
        }

        // 下架入口
        versionEntry.inactive();
        versionEntryRepository.save(versionEntry);

        // 发送下架版本入口领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_INACTIVED_EVENT, versionEntry.getEntryId(), versionEntry.getVersion()
        ));
    }

}
