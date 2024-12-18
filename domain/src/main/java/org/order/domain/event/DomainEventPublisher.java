package org.order.domain.event;

/**
 * 领域事件推送
 *
 * @author chaobo
 * @date 2024/11/26
 */
public interface DomainEventPublisher {

    void publish(DomainEvent domainEvent);

}
