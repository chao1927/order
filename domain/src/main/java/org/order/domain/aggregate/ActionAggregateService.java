package org.order.domain.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.FlowNodeTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Action;
import org.order.domain.entity.version.VersionAction;
import org.order.domain.event.ConfigChangeDomainEvent;
import org.order.domain.event.DomainEventEnum;
import org.order.domain.event.DomainEventPublisher;
import org.order.domain.event.VersionConfigChangeDomainEvent;
import org.order.domain.repository.ActionRepository;
import org.order.domain.repository.version.VersionActionRepository;
import org.order.domain.repository.wrapper.ActionRepositoryWrapper;
import org.order.domain.repository.wrapper.FlowNodeRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * action 聚合服务
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Slf4j
@Component
public class ActionAggregateService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private VersionActionRepository versionActionRepository;

    @Autowired
    private ActionRepositoryWrapper actionRepositoryWrapper;

    @Autowired
    private FlowNodeRepositoryWrapper flowNodeRepositoryWrapper;

    @Autowired
    private DomainEventPublisher domainEventPublisher;


    @Transactional(rollbackFor = Exception.class)
    public void create(Action action) {
        // 判断是否有重名的action
        actionRepositoryWrapper.checkDuplicateName(action.getName());

        // 初始化版本号
        action.initVersion();

        // 保存action
        actionRepository.save(action);

        // 发送创建Action领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.ACTION_CREATED_EVENT, action.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Action action) {
        // 查询并判断action 是否存在，如果不存在则抛出异常
        Action oldAction = actionRepositoryWrapper.findByIdWithEx(action.getId());

        // 判断是否有重名的action
        actionRepositoryWrapper.checkDuplicateName(action.getName(), action.getId());

        // 更新action
        oldAction.change(action.getName(), action.getDescription());
        actionRepository.save(oldAction);

        // 发送更新Action领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.ACTION_UPDATED_EVENT, action.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long actionId) {
        // 判断action 是否存在，如果不存在则抛出异常
        actionRepositoryWrapper.checkActionExist(actionId);

        // 判断action 是否被流程引用，如果被引用则抛出异常
        flowNodeRepositoryWrapper.checkRefByFlow(FlowNodeTypeEnum.ACTION, actionId);

        // 判断action 是否被版本流程引用，如果被引用则抛出异常
        flowNodeRepositoryWrapper.checkRefByVersionFlow(FlowNodeTypeEnum.ACTION, actionId);

        // 删除版本action
        versionActionRepository.deleteByActionId(actionId);

        // 删除action
        actionRepository.deleteById(actionId);

        // 发送删除Action领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.ACTION_DELETED_EVENT, actionId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long actionId) {
        // 查询并判断action 是否存在，如果不存在则抛出异常
        Action action = actionRepositoryWrapper.findByIdWithEx(actionId);

        // 新增版本action
        VersionAction versionAction = new VersionAction(
                action.getId(),
                action.getName(),
                action.getDescription(),
                action.getVersion()
        );
        // 发布 action
        versionAction.active();
        versionActionRepository.save(versionAction);

        // 更新 action 版本号
        action.versioned();
        actionRepository.save(action);

        // 发送发布版本Action领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_ACTIVED_EVENT, versionAction.getActionId(), versionAction.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long actionId, Integer version) {
        // 查询并判断版本 action 是否存在，如果不存在则抛出异常
        VersionAction versionAction = actionRepositoryWrapper.findByActionIdAndVersionWithEx(actionId, version);

        // 判断版本 action 是否处于发布状态，如果处于发布状态则抛出异常
        if (StatusEnum.isActive(versionAction.getStatus())) {
            log.error("action has been actived. actionId:{}. version:{}", actionId, version);
            throw new CustomBusinessException(ErrorCode.ACTION_ALREADY_ACTIVED);
        }

        // 发布 action
        versionAction.active();
        versionActionRepository.save(versionAction);

        // 发送发布版本Action领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_ACTIVED_EVENT, versionAction.getActionId(), versionAction.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void inactive(Long actionId, Integer version) {
        // 查询并判断版本 action 是否存在，如果不存在则抛出异常
        VersionAction versionAction = actionRepositoryWrapper.findByActionIdAndVersionWithEx(actionId, version);

        // 判断版本 action 是否处于下架状态，如果处于下架状态则抛出异常
        if (StatusEnum.isInactive(versionAction.getStatus())) {
            log.error("action has been inactivated. actionId:{}. version:{}", actionId, version);
            throw new CustomBusinessException(ErrorCode.ACTION_ALREADY_INACTIVATED);
        }

        // 判断版本 action 是否被流程引用，如果被引用则抛出异常
        flowNodeRepositoryWrapper.checkRefByFlow(FlowNodeTypeEnum.ACTION, actionId, version);

        // 判断版本 action 是否被版本流程引用，如果被引用则抛出异常
        flowNodeRepositoryWrapper.checkRefByActiveVersionFlow(FlowNodeTypeEnum.ACTION, actionId, version);

        // 下架版本 action
        versionAction.inactive();
        versionActionRepository.save(versionAction);

        // 发送下架版本Action领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_INACTIVED_EVENT, versionAction.getActionId(), versionAction.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long actionId, Integer version) {
        // 查询并判断版本 action 是否存在，如果不存在则抛出异常
        VersionAction versionAction = actionRepositoryWrapper.findByActionIdAndVersionWithEx(actionId, version);

        // 判断版本 action 是否被流程引用，如果被引用则抛出异常
        flowNodeRepositoryWrapper.checkRefByFlow(FlowNodeTypeEnum.ACTION, actionId, version);

        // 判断版本 action 是否被版本流程引用，如果被引用则抛出异常
        flowNodeRepositoryWrapper.checkRefByVersionFlow(FlowNodeTypeEnum.ACTION, actionId, version);

        // 删除版本 action
        versionActionRepository.deleteById(versionAction.getId());

        // 发送删除版本Action领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_ACTION_DELETED_EVENT, versionAction.getActionId(), versionAction.getVersion()
        ));
    }


}
