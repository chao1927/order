package org.order.executor.task;

import org.order.domain.event.BizDataArrivedDomainEvent;
import org.order.domain.event.DomainEventHandler;
import org.order.executor.task.entry.EntryTaskUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 业务数据到达领域事件
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Component
public class BizDataArrivedDomainEventHandler implements DomainEventHandler<BizDataArrivedDomainEvent> {


    @Autowired
    private EntryTaskExecutor entryTaskExecutor;

    @Override
    public void execute(BizDataArrivedDomainEvent domainEvent) {
        entryTaskExecutor.submitTask(new EntryTaskUnit(domainEvent.getOrderNo()));
    }

}
