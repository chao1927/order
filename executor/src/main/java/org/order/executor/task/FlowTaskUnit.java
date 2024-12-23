package org.order.executor.task;

import org.order.executor.ExpressionUtil;

import java.util.List;

/**
 * 流程任务单元
 *
 * 流程任务单元执行前先判断表达式，通过表达式判断是否执行，通过表达式判断是否继续执行后续任务单元
 * 执行时，先创建流程任务，然后执行子任务单元，最后执行后续任务单元
 * 所有任务执行完成后，执行 after 方法，更新当前任务状态。
 *
 * @author chaobo
 * @date 2024/12/23
 */
public class FlowTaskUnit implements TaskUnit {



    private String expression;

    private TaskUnit startNodeTaskUnit;

    @Override
    public boolean check() {
        return ExpressionUtil.eval(this.expression);
    }

    @Override
    public void doExecute() {
        //

    }

    @Override
    public List<TaskUnit> subTaskUnits() {
        return List.of(startNodeTaskUnit);
    }

    @Override
    public List<TaskUnit> nextTaskUnits() {
        return null;
    }
}
