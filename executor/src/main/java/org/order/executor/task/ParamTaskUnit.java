package org.order.executor.task;

import java.util.List;

/**
 * 变量任务单元
 *
 * @author chaobo
 * @date 2024/12/23
 */
public class ParamTaskUnit implements TaskUnit {



    @Override
    public void doExecute() {

    }

    @Override
    public List<TaskUnit> subTaskUnits() {
        return null;
    }

    @Override
    public List<TaskUnit> nextTaskUnits() {
        return null;
    }
}
