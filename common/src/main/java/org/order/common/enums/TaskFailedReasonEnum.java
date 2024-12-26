package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 任务失败原因
 *
 * @author chaobo
 * @date 2024/12/26
 */
@Getter
@ToString
public enum TaskFailedReasonEnum {

    FLOW_START_NODE_NOT_EXIST(1, "流程开始节点不存在");

    private final int code;

    private final String value;

    TaskFailedReasonEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
