package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 任务类型
 *
 * @author chaobo
 * @date 2024/12/25
 */
@Getter
@ToString
public enum TaskTypeEnum {

    Entry(0, "ENTRY"),

    FLOW(1, "FLOW"),

    RULE(2, "RULE"),

    ACTION(3, "ACTION"),

    PARAM(4, "PARAM"),;


    private final Integer code;

    private final String prefix;

    TaskTypeEnum(Integer code, String prefix) {
        this.code = code;
        this.prefix = prefix;
    }
}
