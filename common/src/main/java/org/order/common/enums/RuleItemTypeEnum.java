package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 规则子项类型
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Getter
@ToString
public enum RuleItemTypeEnum {

    PARAM(1, "param"),

    RULE(2, "rule");

    private final Integer code;

    private final String name;

    RuleItemTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static RuleItemTypeEnum getByCode(Integer code) {
        for (RuleItemTypeEnum item : RuleItemTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
