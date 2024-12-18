package org.order.domain.event;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置变更领域事件
 *
 * @author chaobo
 * @date 2024/11/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigChangeDomainEvent implements DomainEvent {

    @JSONField(serializeUsing = DomainEventEnumSerializer.class)
    private DomainEventEnum eventType;

    private Long id;

    @Override
    public DomainEventEnum domainEvent() {
        return this.eventType;
    }
}
