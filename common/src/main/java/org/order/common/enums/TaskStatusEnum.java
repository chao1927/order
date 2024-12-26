package org.order.common.enums;

import lombok.Getter;

/**
 * 任务状态
 *
 * @author chaobo
 * @date 2024/12/23
 */
@Getter
public enum TaskStatusEnum {

    // 执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时
    WAIT_EXECUTE((byte) 1, "待执行"),

    EXECUTING((byte) 2, "执行中"),

    EXECUTE_SUCCESS((byte) 3, "执行成功"),

    EXECUTE_FAILED((byte) 4, "执行失败"),

    TASK_TIMEOUT((byte) 5, "任务超时"),


    ;

    private Byte code;

    private String name;

    TaskStatusEnum(Byte code, String name) {
        this.code = code;
        this.name = name;
    }

}
