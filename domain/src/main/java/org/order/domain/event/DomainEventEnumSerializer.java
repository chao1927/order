package org.order.domain.event;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;

/**
 * 领域事件类型序列化器
 *
 * @author chaobo
 * @date 2024/11/26
 */
public class DomainEventEnumSerializer implements ObjectWriter<DomainEventEnum> {
    @Override
    public void write(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType, long features) {
        if (null == object) {
            jsonWriter.writeNull();
            return;
        }

        if (object instanceof DomainEventEnum domainEvent) {
            jsonWriter.writeInt32(domainEvent.getCode());
        }
    }
}
