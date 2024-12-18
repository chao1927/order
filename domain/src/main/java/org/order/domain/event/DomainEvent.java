package org.order.domain.event;

import com.alibaba.fastjson2.JSON;

/**
 * 领域事件
 *
 * @author chaobo
 * @date 2024/11/21
 */
public interface DomainEvent {

    DomainEventEnum domainEvent();

    default String toJson() {
        return JSON.toJSONString(this);
    }

}
