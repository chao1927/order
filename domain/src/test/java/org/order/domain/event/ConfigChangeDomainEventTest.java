package org.order.domain.event;

import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/11/26
 */
public class ConfigChangeDomainEventTest {

    @Test
    public void TestSerializeDomainEventType() {
        ConfigChangeDomainEvent configChangeDomainEvent = new ConfigChangeDomainEvent(DomainEventEnum.ACTION_CREATED_EVENT, 1000L);
        System.out.println(configChangeDomainEvent.toJson());
    }

}
