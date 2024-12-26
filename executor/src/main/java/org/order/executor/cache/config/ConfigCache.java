package org.order.executor.cache.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 入口配置缓存
 *
 * @author chaobo
 * @date 2024/12/18
 */
public class ConfigCache<T extends IdAndVersion> {


    private final ConcurrentMap<Long, ConcurrentMap<Integer, T>> cache;

    public ConfigCache() {
        cache = new ConcurrentHashMap<>();
    }

    public void add(T config) {
        if (null == config) {
            return;
        }

        Map<Integer, T> versionCache = cache.computeIfAbsent(config.id(), k -> new ConcurrentHashMap<>());
        versionCache.put(config.version(), config);
    }

    public void remove(Long id, Integer version) {
        Map<Integer, T> versionCache = cache.get(id);
        if (null == versionCache) {
            return;
        }

        versionCache.remove(version);
        if (versionCache.isEmpty()) {
            cache.remove(id);
        }
    }

    public T get(Long id, Integer version) {
        Map<Integer, T> versionCache = cache.get(id);
        if (null == versionCache) {
            return null;
        }

        return versionCache.get(version);
    }

    public List<T> getAllList() {
        List<T> result = new ArrayList<>();
        for (Map<Integer, T> versionCache : cache.values()) {
            result.addAll(versionCache.values());
        }
        return result;
    }

}
