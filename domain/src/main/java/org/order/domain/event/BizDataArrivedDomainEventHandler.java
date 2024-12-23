package org.order.domain.event;

import org.springframework.stereotype.Component;

/**
 * 业务数据到达领域事件
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Component
public class BizDataArrivedDomainEventHandler implements DomainEventHandler<BizDataArrivedDomainEvent> {




    @Override
    public void execute(BizDataArrivedDomainEvent domainEvent) {
        domainEvent.getOrderNo();
    }

}
