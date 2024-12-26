package org.order.executor.cache.config;

import org.apache.commons.collections4.CollectionUtils;
import org.order.domain.entity.version.VersionParam;
import org.order.domain.entity.version.VersionRuleItem;
import org.order.domain.repository.version.*;
import org.order.executor.cache.domain.*;
import org.order.executor.converter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 配置加载
 *
 * @author chaobo
 * @date 2024/12/26
 */
@Component
public class ConfigLoader {

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

    @PostConstruct
    public void init() {
        // 加载所有激活的变量
        Optional<List<VersionParam>> versionParamsOp = versionParamRepository.findActive();
        versionParamsOp.ifPresent(versionParams ->
                versionParams.forEach(versionParam ->
                        configCacheManager.addParamConfig(ParamConfigConverter.convertVersionParamConfig(versionParam))
                )
        );

        // todo 加载所有激活的规则
        // todo 加载所有激活的 action
        // todo 加载所有激活的流程
        // todo 加载所有激活的入口
    }


    public Optional<VersionParamConfig> loadParamConfig(Long id, Integer version) {
        VersionParamConfig paramConfig = configCacheManager.getParamConfig(id, version);
        if (null != paramConfig) {
            return Optional.of(paramConfig);
        }

        return versionParamRepository.findByParamIdAndVersion(id, version).map(versionParam -> {
            if (!versionParam.isActive()) {
                return null;
            }

            VersionParamConfig versionParamConfig = ParamConfigConverter.convertVersionParamConfig(versionParam);
            configCacheManager.addParamConfig(versionParamConfig);
            return versionParamConfig;
        });
    }

    public Optional<VersionRuleConfig> loadRuleConfig(Long id, Integer version) {
        VersionRuleConfig ruleConfig = configCacheManager.getRuleConfig(id, version);
        if (null != ruleConfig) {
            return Optional.of(ruleConfig);
        }

        return versionRuleRepository.findByRuleIdAndVersion(id, version).map(versionRule -> {
            if (!versionRule.isActive()) {
                return null;
            }

            List<VersionRuleItemConfig> versionRuleItemConfigs = new ArrayList<>();
            VersionRuleConfig versionRuleConfig = RuleConfigConverter.convertVersionRuleConfig(versionRule, versionRuleItemConfigs);
            versionRuleItemRepository.findByVersionRuleId(versionRule.getId()).ifPresent(versionRuleItems -> {
                if (CollectionUtils.isEmpty(versionRuleItems)) {
                    return;
                }

                for (VersionRuleItem item : versionRuleItems) {
                    VersionRuleItemConfig versionRuleItemConfig = RuleConfigConverter.convertVersionRuleItemConfig(item);
                    loadParamConfig(item.getRefId(), item.getRefVersion()).ifPresent(versionRuleItemConfig::setParamConfig);
                    versionRuleItemConfigs.add(versionRuleItemConfig);
                }
                versionRuleItemConfigs.sort(Comparator.comparingInt(VersionRuleItemConfig::getSort));
            });

            configCacheManager.addRuleConfig(versionRuleConfig);
            return versionRuleConfig;
        });
    }

    public Optional<VersionActionConfig> loadActionConfig(Long id, Integer version) {
        VersionActionConfig actionConfig = configCacheManager.getActionConfig(id, version);
        if (null != actionConfig) {
            return Optional.of(actionConfig);
        }

        return versionActionRepository.findByActionIdAndVersion(id, version).map(versionAction -> {
            if (!versionAction.isActive()) {
                return null;
            }

            VersionActionConfig versionActionConfig = ActionConfigConverter.convertVersionActionConfig(versionAction);
            configCacheManager.addActionConfig(versionActionConfig);
            return versionActionConfig;
        });
    }

    public Optional<VersionFlowConfig> loadFlowConfig(Long id, Integer version) {
        VersionFlowConfig flowConfig = configCacheManager.getFlowConfig(id, version);
        if (null != flowConfig) {
            return Optional.of(flowConfig);
        }

        return versionFlowRepository.findByFlowIdAndVersion(id, version).map(versionFlow -> {
            if (!versionFlow.isActive()) {
                return null;
            }
            List<VersionFlowLineConfig> versionFlowLineConfigs = getVersionFlowLineConfigs(versionFlow.getId());
            List<VersionFlowNodeConfig> versionFlowNodeConfigs = getVersionFlowNodeConfigs(versionFlow.getId());

            VersionFlowConfig versionFlowConfig = FlowConfigConverter.convertVersionFlowConfig(versionFlow, versionFlowNodeConfigs, versionFlowLineConfigs);
            configCacheManager.addFlowConfig(versionFlowConfig);
            return versionFlowConfig;
        });
    }

    private List<VersionFlowNodeConfig> getVersionFlowNodeConfigs(Long versionFlowId) {
        List<VersionFlowNodeConfig> versionFlowNodeConfigs = new ArrayList<>();
        versionFlowNodeRepository.findByVersionFlowId(versionFlowId).ifPresent(versionFlowNodes -> {
            if (CollectionUtils.isEmpty(versionFlowNodes)) {
                return;
            }

            versionFlowNodes.forEach(node -> {
                VersionFlowNodeConfig versionFlowNodeConfig = FlowConfigConverter.convertVersionFlowNodeConfig(node);
                switch (versionFlowNodeConfig.getType()) {
                    case RULE:
                        loadRuleConfig(node.getRefId(), node.getRefVersion())
                                .ifPresent(versionFlowNodeConfig::setRuleConfig);
                        break;
                    case ACTION:
                        loadActionConfig(node.getRefId(), node.getRefVersion())
                                .ifPresent(versionFlowNodeConfig::setActionConfig);
                        break;
                    default:
                }
                versionFlowNodeConfigs.add(versionFlowNodeConfig);
            });
        });

        return versionFlowNodeConfigs;
    }

    private List<VersionFlowLineConfig> getVersionFlowLineConfigs(Long versionFlowId) {
        List<VersionFlowLineConfig> versionFlowLineConfigs = new ArrayList<>();
        versionFlowLineRepository.findByVersionFlowId(versionFlowId).ifPresent(versionFlowLines -> {
            if (CollectionUtils.isEmpty(versionFlowLines)) {
                return;
            }

            versionFlowLines.forEach(line -> versionFlowLineConfigs.add(FlowConfigConverter.convertVersionFlowLineConfig(line)));
        });
        return versionFlowLineConfigs;
    }

    public Optional<VersionEntryConfig> loadEntryConfig(Long id, Integer version) {
        VersionEntryConfig entryConfig = configCacheManager.getEntryConfig(id, version);
        if (null != entryConfig) {
            return Optional.of(entryConfig);
        }

        return versionEntryRepository.findByEntryIdAndVersion(id, version).map(versionEntry -> {
            if (!versionEntry.isActive()) {
                return null;
            }

            VersionEntryConfig versionEntryConfig = EntryConfigConverter.convertVersionEntryConfig(versionEntry);
            loadFlowConfig(versionEntry.getFlowId(), versionEntry.getFlowVersion())
                    .ifPresent(versionEntryConfig::setFlowConfig);

            configCacheManager.addEntryConfig(versionEntryConfig);
            return versionEntryConfig;
        });
    }

    public void removeParamConfig(Long id, Integer version) {
        versionParamRepository.findByParamIdAndVersion(id, version).ifPresent(versionParam -> {
            if (versionParam.isActive()) {
                return;
            }
            configCacheManager.removeParamConfig(id, version);
        });
    }

    public void removeRuleConfig(Long id, Integer version) {
        versionRuleRepository.findByRuleIdAndVersion(id, version).ifPresent(versionRule -> {
            if (versionRule.isActive()) {
                return;
            }
            configCacheManager.removeRuleConfig(id, version);
        });
    }

    public void removeActionConfig(Long id, Integer version) {
        versionActionRepository.findByActionIdAndVersion(id, version).ifPresent(versionAction -> {
            if (versionAction.isActive()) {
                return;
            }
            configCacheManager.removeActionConfig(id, version);
        });
    }

    public void removeFlowConfig(Long id, Integer version) {
        versionFlowRepository.findByFlowIdAndVersion(id, version).ifPresent(versionFlow -> {
            if (versionFlow.isActive()) {
                return;
            }

            configCacheManager.removeFlowConfig(id, version);
        });
    }

    public void removeEntryConfig(Long id, Integer version) {
        versionEntryRepository.findByEntryIdAndVersion(id, version).ifPresent(versionEntry -> {
            if (versionEntry.isActive()) {
                return;
            }
            configCacheManager.removeEntryConfig(id, version);
        });
    }

}
