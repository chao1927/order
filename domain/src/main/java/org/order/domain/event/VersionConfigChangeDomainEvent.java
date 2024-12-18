package org.order.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 版本配置变更领域事件
 *
 * @author chaobo
 * @date 2024/11/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionConfigChangeDomainEvent implements DomainEvent {

    private DomainEventEnum type;

    private Long id;

    private Integer version;

    @Override
    public DomainEventEnum domainEvent() {
        return this.type;
    }
}
