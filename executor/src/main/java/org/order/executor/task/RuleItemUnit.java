package org.order.executor.task;

import java.util.List;

/**
 * 规则子项任务单元
 *
 * @author chaobo
 * @date 2024/12/23
 */
public class RuleItemUnit implements TaskUnit {
    @Override
    public void doExecute() {

    }

    @Override
    public List<TaskUnit> subTaskUnits() {
        return List.of();
    }

    @Override
    public List<TaskUnit> nextTaskUnits() {
        return List.of();
    }
}
