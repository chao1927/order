package org.order.executor.cache.data;

import com.alibaba.fastjson2.JSONObject;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/12/18
 */
public interface DataCacheUpdater {

    void update(JSONObject data);

}
