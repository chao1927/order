package org.order.event;

import org.order.domain.event.DomainEvent;
import org.order.domain.event.DomainEventEnum;
import org.order.domain.event.DomainEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 将领域事件发送到 rocketMQ
 *
 * @author chaobo
 * @date 2024/11/26
 */
@Component
public class RocketMQDomainEventPublisher implements DomainEventPublisher {


    @Override
    public void publish(DomainEvent domainEvent) {
        DomainEventEnum event = domainEvent.domainEvent();

        // 业务事件
        if (DomainEventEnum.BIZ_DATA_ARRIVED_EVENT.equals(event)) {

        } else {
            // 配置事件

        }
    }
}
