package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 业务来源
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Getter
@ToString
public enum BizSource {

    A(1, "A"),

    B(2, "B"),

    C(3, "C"),


    ;

    private Integer code;

    private String name;

    BizSource(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BizSource getByCode(Integer code) {
        for (BizSource bizSource : BizSource.values()) {
            if (bizSource.code == code) {
                return bizSource;
            }
        }
        return null;
    }

}
