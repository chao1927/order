package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * 结果类型
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Getter
@ToString
public enum ResultTypeEnum {

    INTEGER(1, "integer", Integer.class),

    LONG(2, "long", Long.class),

    BOOLEAN(3, "boolean", Boolean.class),

    FLOAT(4, "float", Double.class),

    STRING(5, "string", String.class),

    ARRAY(6, "array", ArrayList.class),

    OBJECT(7, "object", Object.class),

    ;


    private final Integer code;

    private final String name;

    private final Class<?> clazz;

    ResultTypeEnum(Integer code, String name, Class<?> clazz) {
        this.code = code;
        this.name = name;
        this.clazz = clazz;
    }

    public static ResultTypeEnum getByCode(Integer resultType) {
        if (null == resultType) {
            return null;
        }

        for (ResultTypeEnum typeEnum : ResultTypeEnum.values()) {
            if (typeEnum.getCode().equals(resultType)) {
                return typeEnum;
            }
        }
        return null;
    }
}
