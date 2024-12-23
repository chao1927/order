package org.order.domain.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.FlowNodeTypeEnum;
import org.order.common.enums.ResultTypeEnum;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.*;
import org.order.domain.repository.version.*;
import org.order.executor.cache.config.ConfigCacheManager;
import org.order.executor.cache.domain.*;
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
    private VersionParamRepository versionParamRepository;

    @Autowired
    private VersionRuleRepository versionRuleRepository;

    @Autowired
    private VersionRuleItemRepository versionRuleItemRepository;

    @Autowired
    private VersionActionRepository versionActionRepository;

    @Autowired
    private VersionFlowRepository versionFlowRepository;

    @Autowired
    private VersionFlowLineRepository versionFlowLineRepository;

    @Autowired
    private VersionFlowNodeRepository versionFlowNodeRepository;

    @Autowired
    private VersionEntryRepository versionEntryRepository;


    @Autowired
    private ConfigCacheManager configCacheManager;


    @Override
    public void execute(VersionConfigChangeDomainEvent domainEvent) {

        DomainEventEnum event = domainEvent.domainEvent();
        Long id = domainEvent.getId();
        Integer version = domainEvent.getVersion();

        switch (event) {
            // 版本变量
            case VERSION_PARAM_ACTIVED_EVENT:
                addParamConfig(id, version);
                break;
            case VERSION_PARAM_INACTIVED_EVENT:
                removeParamConfig(id, version);
                break;
            // 版本规则
            case VERSION_RULE_ACTIVED_EVENT:
                addRuleConfig(id, version);
                break;
            case VERSION_RULE_INACTIVED_EVENT:
                removeRuleConfig(id, version);
                break;
            // 版本action
            case VERSION_ACTION_ACTIVED_EVENT:
                addActionConfig(id, version);
                break;
            case VERSION_ACTION_INACTIVED_EVENT:
                removeActionConfig(id, version);
                break;
            // 版本流程
            case VERSION_FLOW_ACTIVED_EVENT:
                addFlowConfig(id, version);
                break;
            case VERSION_FLOW_INACTIVED_EVENT:
                removeFlowConfig(id, version);
                break;
            // 版本入口
            case VERSION_ENTRY_ACTIVED_EVENT:
                addEntryConfig(id, version);
                break;
           case VERSION_ENTRY_INACTIVED_EVENT:
               removeEntryConfig(id, version);
               break;
        }
    }

    private void addParamConfig(Long id, Integer version) {
        versionParamRepository.findByParamIdAndVersion(id, version).ifPresent(versionParam -> {
            if (!versionParam.isActive()) {
                return;
            }

            configCacheManager.addParamConfig(new VersionParamConfig(
                    versionParam.getId(),
                    versionParam.getParamId(),
                    versionParam.getName(),
                    versionParam.getDescription(),
                    versionParam.getExpression(),
                    ResultTypeEnum.getByCode(versionParam.getResultType()),
                    versionParam.getVersion()
            ));
        });
    }

    private void removeParamConfig(Long id, Integer version) {
        versionParamRepository.findByParamIdAndVersion(id, version).ifPresent(versionParam -> {
            if (versionParam.isActive()) {
                return;
            }
            configCacheManager.removeParamConfig(id, version);
        });
    }

    private void addRuleConfig(Long id, Integer version) {
        versionRuleRepository.findByRuleIdAndVersion(id, version).ifPresent(versionRule -> {
            if (!versionRule.isActive()) {
                return;
            }

            versionRuleItemRepository.findByVersionRuleId(versionRule.getId()).ifPresent(versionRuleItems -> {
                if (CollectionUtils.isEmpty(versionRuleItems)) {
                    return;
                }

                List<VersionRuleItemConfig> versionRuleItemConfigs = new ArrayList<>();
                for (VersionRuleItem item : versionRuleItems) {
                    versionRuleItemConfigs.add(new VersionRuleItemConfig(
                            item.getId(),
                            item.getVersionRuleId(),
                            item.getSort(),
                            item.getName(),
                            RuleItemTypeEnum.getByCode(item.getType()),
                            item.getRefId(),
                            item.getRefVersion()
                    ));
                }
                versionRuleItemConfigs.sort(Comparator.comparingInt(VersionRuleItemConfig::getSort));
                configCacheManager.addRuleConfig(new VersionRuleConfig(
                        versionRule.getId(),
                        versionRule.getRuleId(),
                        versionRule.getName(),
                        versionRule.getDescription(),
                        versionRule.getExpression(),
                        ResultTypeEnum.getByCode(versionRule.getResultType()),
                        versionRule.getVersion(),
                        versionRuleItemConfigs
                ));
            });
        });
    }

    private void removeRuleConfig(Long id, Integer version) {
        versionRuleRepository.findByRuleIdAndVersion(id, version).ifPresent(versionRule -> {
            if (versionRule.isActive()) {
                return;
            }
            configCacheManager.removeRuleConfig(id, version);
        });
    }

    private void addActionConfig(Long id, Integer version) {
        versionActionRepository.findByActionIdAndVersion(id, version).ifPresent(versionAction -> {
            if (!versionAction.isActive()) {
                return;
            }

            configCacheManager.addActionConfig(new VersionActionConfig(
                    versionAction.getId(),
                    versionAction.getActionId(),
                    versionAction.getName(),
                    versionAction.getDescription(),
                    versionAction.getVersion()
            ));
        });
    }

    private void removeActionConfig(Long id, Integer version) {
        versionActionRepository.findByActionIdAndVersion(id, version).ifPresent(versionAction -> {
            if (versionAction.isActive()) {
                return;
            }
            configCacheManager.removeActionConfig(id, version);
        });
    }

    private void addFlowConfig(Long id, Integer version) {
        versionFlowRepository.findByFlowIdAndVersion(id, version).ifPresent(versionFlow -> {
            if (!versionFlow.isActive()) {
                return;
            }

            Long versionFlowId = versionFlow.getId();
            Optional<List<VersionFlowLineConfig>> versionFlowLineConfigsOp = getVersionFlowLineConfigs(versionFlowId);
            Optional<List<VersionFlowNodeConfig>> versionFlowNodeConfigsOp = getVersionFlowNodeConfigs(versionFlowId);

            if (versionFlowLineConfigsOp.isEmpty() || versionFlowNodeConfigsOp.isEmpty()) {
                return;
            }

            List<VersionFlowLineConfig> versionFlowLineConfigs = versionFlowLineConfigsOp.get();
            List<VersionFlowNodeConfig> versionFlowNodeConfigs = versionFlowNodeConfigsOp.get();
            if (CollectionUtils.isEmpty(versionFlowLineConfigs) || CollectionUtils.isEmpty(versionFlowNodeConfigs)) {
                return;
            }

            configCacheManager.addFlowConfig(getVersionFlowConfig(versionFlow, versionFlowNodeConfigs, versionFlowLineConfigs));
        });
    }

    private void removeFlowConfig(Long id, Integer version) {
        versionFlowRepository.findByFlowIdAndVersion(id, version).ifPresent(versionFlow -> {
            if (versionFlow.isActive()) {
                return;
            }

            configCacheManager.removeFlowConfig(id, version);
        });
    }

    private VersionFlowConfig getVersionFlowConfig(VersionFlow versionFlow,
                                                          List<VersionFlowNodeConfig> versionFlowNodeConfigs,
                                                          List<VersionFlowLineConfig> versionFlowLineConfigs) {
        VersionFlowNodeConfig startNode = null;
        Map<Long, VersionFlowNodeConfig> versionNodeId2NodeMap = new HashMap<>();
        for (VersionFlowNodeConfig node : versionFlowNodeConfigs) {
            if (node.isStartNode()) {
                startNode = node;
            }
            versionNodeId2NodeMap.put(node.getId(), node);
        }

        for (VersionFlowLineConfig line : versionFlowLineConfigs) {
            VersionFlowNodeConfig preNode = versionNodeId2NodeMap.get(line.getPreNodeId());
            if (null == preNode) {
                log.error("flow line pre node is not exist, versionFlow:{}, line:{}", versionFlow, line);
                throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
            }

            VersionFlowNodeConfig nextNode = versionNodeId2NodeMap.get(line.getNextNodeId());
            if (null == nextNode) {
                log.error("flow line next node is not exist, versionFlow:{}, line:{}", versionFlow, line);
                throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
            }

            line.setPreNode(preNode);
            line.setNextNode(nextNode);

            preNode.addFlowLine(line);
            nextNode.setPreLine(line);
        }

        return new VersionFlowConfig(
                versionFlow.getId(),
                versionFlow.getFlowId(),
                versionFlow.getVersion(),
                versionFlow.getName(),
                versionFlow.getDescription(),
                versionFlowLineConfigs,
                versionFlowNodeConfigs,
                startNode
        );
    }

    private Optional<List<VersionFlowNodeConfig>> getVersionFlowNodeConfigs(Long versionFlowId) {
        return versionFlowNodeRepository.findByVersionFlowId(versionFlowId).map(versionFlowNodes -> {
            if (CollectionUtils.isEmpty(versionFlowNodes)) {
                return null;
            }

            return versionFlowNodes.stream()
                    .map(node -> new VersionFlowNodeConfig(
                            node.getId(),
                            node.getVersionFlowId(),
                            node.getName(),
                            node.getDescription(),
                            FlowNodeTypeEnum.getByCode(node.getType()),
                            node.getContent(),
                            node.getRefId(),
                            node.getRefVersion()
                    )).toList();
        });
    }

    private Optional<List<VersionFlowLineConfig>> getVersionFlowLineConfigs(Long versionFlowId) {
        return versionFlowLineRepository.findByVersionFlowId(versionFlowId).map(versionFlowLines -> {
            if (CollectionUtils.isEmpty(versionFlowLines)) {
                return null;
            }

            return versionFlowLines.stream()
                    .map(line -> new VersionFlowLineConfig(
                            line.getId(),
                            line.getVersionFlowId(),
                            line.getPreNodeId(),
                            line.getPreNodeName(),
                            line.getNextNodeId(),
                            line.getNextNodeName(),
                            line.getContent()
                    )).toList();
        });
    }

    private void addEntryConfig(Long id, Integer version) {
        versionEntryRepository.findByEntryIdAndVersion(id, version).ifPresent(versionEntry -> {
            if (!versionEntry.isActive()) {
                return;
            }

            configCacheManager.addEntryConfig(new VersionEntryConfig(
                    versionEntry.getId(),
                    versionEntry.getEntryId(),
                    version,
                    versionEntry.getName(),
                    versionEntry.getDescription(),
                    versionEntry.getExpression(),
                    versionEntry.getFlowId(),
                    versionEntry.getFlowVersion())
            );
        });
    }


    private void removeEntryConfig(Long id, Integer version) {
        versionEntryRepository.findByEntryIdAndVersion(id, version).ifPresent(versionEntry -> {
            if (versionEntry.isActive()) {
                return;
            }
            configCacheManager.removeEntryConfig(id, version);
        });
    }

}
