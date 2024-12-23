package org.order.executor.cache.data;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.order.common.enums.ResultTypeEnum;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存每一个任务的所有业务数据
 *
 * @author chaobo
 * @date 2024/12/18
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DataCache {

    private ReentrantLock lock = new ReentrantLock();

    private volatile String taskId;

    private volatile JSONObject data;


    public DataCache(String taskId, String data) {
        this.taskId = taskId;
        this.data = JSON.parseObject(data);
    }

    public DataCache(String taskId, Object data) {
        this.taskId = taskId;
        this.data = JSON.parseObject(JSON.toJSONString(data));
    }

    public String taskId() {
        return this.taskId;
    }

    public String data() {
        return JSON.toJSONString(data);
    }

    public void write(DataCacheUpdater updater) {
        lock.lock();
        try {
            updater.update(data);
        } finally {
            lock.unlock();
        }
    }

    // 获取数据
    public Object read(String path) {
        try {
            lock.lock();
            return JsonPath.read(JSON.toJSONString(data), path);
        } catch (PathNotFoundException e) {
            log.error("No results for path:{}", path, e);
        } finally {
            lock.unlock();
        }
        return null;
    }

    public Integer readInt(String path) {
        Object value = read(path);
        if (null == value) {
            return null;
        }

        if (value instanceof Integer) {
            return (Integer) value;
        }
        return null;
    }

    public Long readLong(String path) {
        Object value = read(path);
        if (null == value) {
            return null;
        }

        if (value instanceof Long) {
            return (Long) value;
        }
        return null;
    }

    public Double readDouble(String path) {
        Object value = read(path);
        if (null == value) {
            return null;
        }

        if (value instanceof Double) {
            return (Double) value;
        }
        return null;
    }

    /**
     * 从指定路径读取布尔值
     *
     * @param path 值的路径
     * @return 如果路径对应的值为Boolean类型，则返回该值，否则返回null
     */
    public Boolean readBoolean(String path) {
        // 读取指定路径的值
        Object value = read(path);

        // 检查读取的值是否为null
        if (null == value) {
            return null;
        }

        // 如果值是Boolean类型，直接返回
        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        // 如果值不是Boolean类型，返回null
        return null;
    }

    public String readString(String path) {
        Object value = read(path);
        if (null == value) {
            return null;
        }

        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    public <T> T read(String path, ResultTypeEnum type) {
        Object value = read(path);
        if (null == value) {
            return null;
        }

        if (type.getClazz().isAssignableFrom(value.getClass())) {
            return (T) type.getClazz().cast(value);
        }
        return null;
    }

}
