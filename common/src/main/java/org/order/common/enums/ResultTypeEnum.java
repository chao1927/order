package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 结果类型
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Getter
@ToString
public enum ResultTypeEnum {

    NUMBER(1, "number"),

    BOOLEAN(2, "boolean"),

    FLOAT(3, "float"),

    STRING(4, "string"),

    ARRAY(5, "array"),

    OBJECT(6, "object"),

    ;


    private final Integer code;

    private final String name;

    ResultTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
