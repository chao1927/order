package org.order.common.enums;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/12/23
 */
public enum TaskStatusEnum {

    // 执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时
    WAIT_EXECUTE(1, "待执行"),

    EXECUTING(2, "执行中"),

    EXECUTE_SUCCESS(3, "执行成功"),

    EXECUTE_FAILED(4, "执行失败"),

    TASK_TIMEOUT(5, "任务超时"),


    ;

    private Integer code;

    private String name;

    TaskStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
