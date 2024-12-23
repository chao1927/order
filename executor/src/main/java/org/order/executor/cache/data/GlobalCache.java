package org.order.executor.cache.data;

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


    public void addDataCache(DataCache dataCache) {
        dataCaches.put(dataCache.taskId(), dataCache);
    }

    public DataCache removeDataCache(String taskId) {
        return dataCaches.remove(taskId);
    }



}
