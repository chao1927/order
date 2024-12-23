package org.order.executor.task;

import java.util.List;

/**
 * 入口任务执行单元
 *
 * @author chaobo
 * @date 2024/12/23
 */
public class EntryTaskUnit implements TaskUnit {

    private String bizId;

    private List<TaskUnit> subFlowTaskUnits;

    @Override
    public void before() {


    }

    @Override
    public void doExecute() {

    }



    @Override
    public List<TaskUnit> subTaskUnits() {
        return subFlowTaskUnits;
    }

    @Override
    public List<TaskUnit> nextTaskUnits() {
        return null;
    }
}
