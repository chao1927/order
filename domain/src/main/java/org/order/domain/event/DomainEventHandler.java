package org.order.domain.event;

/**
 * 领域事件处理器
 *
 * @author chaobo
 * @date 2024/12/18
 */
public interface DomainEventHandler<T extends DomainEvent> {

    void execute(T domainEvent);

}
