package org.order.executor.cache;

import lombok.extern.slf4j.Slf4j;
import org.order.domain.event.DomainEventEnum;
import org.order.domain.event.DomainEventHandler;
import org.order.domain.event.VersionConfigChangeDomainEvent;
import org.order.executor.cache.config.ConfigLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 版本配置变更事件处理器
 *
 * @author chaobo
 * @date 2024/12/18
 */
@Slf4j
@Component
public class VersionConfigChangeDomainEventHandler implements DomainEventHandler<VersionConfigChangeDomainEvent> {

    @Autowired
    private ConfigLoader configLoader;

    @Override
    public void execute(VersionConfigChangeDomainEvent domainEvent) {

        DomainEventEnum event = domainEvent.domainEvent();
        Long id = domainEvent.getId();
        Integer version = domainEvent.getVersion();

        switch (event) {
            // 版本变量
            case VERSION_PARAM_ACTIVED_EVENT:
                configLoader.loadParamConfig(id, version);
                break;
            case VERSION_PARAM_INACTIVED_EVENT:
                configLoader.removeParamConfig(id, version);
                break;
            // 版本规则
            case VERSION_RULE_ACTIVED_EVENT:
                configLoader.loadRuleConfig(id, version);
                break;
            case VERSION_RULE_INACTIVED_EVENT:
                configLoader.removeRuleConfig(id, version);
                break;
            // 版本action
            case VERSION_ACTION_ACTIVED_EVENT:
                configLoader.loadActionConfig(id, version);
                break;
            case VERSION_ACTION_INACTIVED_EVENT:
                configLoader.removeActionConfig(id, version);
                break;
            // 版本流程
            case VERSION_FLOW_ACTIVED_EVENT:
                configLoader.loadFlowConfig(id, version);
                break;
            case VERSION_FLOW_INACTIVED_EVENT:
                configLoader.removeFlowConfig(id, version);
                break;
            // 版本入口
            case VERSION_ENTRY_ACTIVED_EVENT:
                configLoader.loadEntryConfig(id, version);
                break;
           case VERSION_ENTRY_INACTIVED_EVENT:
               configLoader.removeEntryConfig(id, version);
               break;
        }
    }

}
