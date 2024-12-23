package org.order.executor.cache.config;

import org.order.executor.cache.domain.*;
import org.springframework.stereotype.Component;

/**
 * 配置缓存管理器
 *
 * @author chaobo
 * @date 2024/12/18
 */
@Component
public class ConfigCacheManager {

    private final ConfigCache<VersionEntryConfig> entryConfigCache;

    private final ConfigCache<VersionFlowConfig> flowConfigCache;

    private final ConfigCache<VersionActionConfig> actionConfigCache;

    private final ConfigCache<VersionRuleConfig> ruleConfigCache;

    private final ConfigCache<VersionParamConfig> paramConfigCache;


    public ConfigCacheManager() {
        this.entryConfigCache = new ConfigCache<>();
        this.flowConfigCache = new ConfigCache<>();
        this.actionConfigCache = new ConfigCache<>();
        this.ruleConfigCache = new ConfigCache<>();
        this.paramConfigCache = new ConfigCache<>();
    }

    /************************************ entry *************************************/

    public void addEntryConfig(VersionEntryConfig entryConfig) {
        entryConfigCache.add(entryConfig);
    }

    public void removeEntryConfig(Long id, Integer version) {
        entryConfigCache.remove(id, version);
    }

    public VersionEntryConfig getEntryConfig(Long id, Integer version) {
        return entryConfigCache.get(id, version);
    }

    /********************************** flow *************************************/

    public void addFlowConfig(VersionFlowConfig flowConfig) {
        flowConfigCache.add(flowConfig);
    }

    public void removeFlowConfig(Long id, Integer version) {
        flowConfigCache.remove(id, version);
    }

    public VersionFlowConfig getFlowConfig(Long id, Integer version) {
        return flowConfigCache.get(id, version);
    }

    /********************************** action *************************************/

    public void addActionConfig(VersionActionConfig actionConfig) {
        actionConfigCache.add(actionConfig);
    }

    public void removeActionConfig(Long id, Integer version) {
        actionConfigCache.remove(id, version);
    }

    public VersionActionConfig getActionConfig(Long id, Integer version) {
        return actionConfigCache.get(id, version);
    }

    /********************************** rule *************************************/

    public void addRuleConfig(VersionRuleConfig ruleConfig) {
        ruleConfigCache.add(ruleConfig);
    }

    public void removeRuleConfig(Long id, Integer version) {
        ruleConfigCache.remove(id, version);
    }

    public VersionRuleConfig getRuleConfig(Long id, Integer version) {
        return ruleConfigCache.get(id, version);
    }

    /********************************** param *************************************/

    public void addParamConfig(VersionParamConfig paramConfig) {
        paramConfigCache.add(paramConfig);
    }

    public void removeParamConfig(Long id, Integer version) {
        paramConfigCache.remove(id, version);
    }

    public VersionParamConfig getParamConfig(Long id, Integer version) {
        return paramConfigCache.get(id, version);
    }


}
