package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 节点类型
 *
 * @author chaobo
 * @date 2024/11/21
 */
@Getter
@ToString
public enum FlowNodeTypeEnum {

    // 节点类型：1.start，2.end，3.rule，4.action，5.judge;
    START(1, "开始节点"),

    END(2, "结束节点"),

    RULE(3, "规则节点"),

    ACTION(4, "动作节点"),

    JUDGE(5, "判断节点");

    private final Integer code;

    private final String name;

    FlowNodeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static FlowNodeTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (FlowNodeTypeEnum flowNodeTypeEnum : FlowNodeTypeEnum.values()) {
            if (flowNodeTypeEnum.code.equals(code)) {
                return flowNodeTypeEnum;
            }
        }
        return null;
    }
}
