package org.order.executor.cache.data;

import org.order.domain.aggregate.BizInfoAggregate;
import org.order.domain.aggregate.BizInfoAggregateService;

import java.util.concurrent.ConcurrentMap;

/**
 * 全局缓存
 *
 * @author chaobo
 * @date 2024/12/18
 */
public class GlobalCache {

    /**
     * 当前正在执行的所有任务的数据缓存
     * key: 任务ID
     * value: 数据缓存
     */
    private ConcurrentMap<String, DataCache> dataCaches;

    private BizInfoAggregateService bizInfoAggregateService;


    public void addDataCache(DataCache dataCache) {
        dataCaches.put(dataCache.bizId(), dataCache);
    }

    public DataCache removeDataCache(String taskId) {
        return dataCaches.remove(taskId);
    }

    public void loadDataCache(String bizId) {
        BizInfoAggregate bizInfoAgg = bizInfoAggregateService.load(bizId);
        addDataCache(new DataCache(bizId, bizInfoAgg.toData()));
    }


    public DataCache getDataCache(String bizId) {
        return dataCaches.get(bizId);
    }
}
