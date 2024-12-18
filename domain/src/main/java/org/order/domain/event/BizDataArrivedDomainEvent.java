package org.order.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务数据到达
 *
 * @author chaobo
 * @date 2024/11/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizDataArrivedDomainEvent implements DomainEvent {

    /**
     * 业务数据的编号
     */
    private String orderNo;

    @Override
    public DomainEventEnum domainEvent() {
        return DomainEventEnum.BIZ_DATA_ARRIVED_EVENT;
    }

    public Integer getEventType() {
        return domainEvent().getCode();
    }

}
