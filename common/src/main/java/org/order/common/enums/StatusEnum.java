package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 版本变量状态
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Getter
@ToString
public enum StatusEnum {
    /**
     * 1. 已发布，2.已下架，3.已删除
     */
    ACTIVATED(1, "已发布"),

    INACTIVATED(2, "已下架"),

    ;

    private Integer code;

    private String name;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StatusEnum getStatusEnumByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.name;
            }
        }
        return null;
    }



    public static boolean isActive(Integer status) {
        return ACTIVATED.getCode().equals(status);
    }

    public static boolean isInactive(Integer status) {
        return INACTIVATED.getCode().equals(status);
    }

}
