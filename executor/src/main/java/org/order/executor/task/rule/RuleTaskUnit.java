package org.order.executor.task.rule;

import org.order.executor.task.TaskUnit;

import java.util.List;

/**
 * 规则任务单元
 *
 * @author chaobo
 * @date 2024/12/26
 */
public class RuleTaskUnit implements TaskUnit {

    private String bizId;

    @Override
    public String bizId() {
        return bizId;
    }

}
