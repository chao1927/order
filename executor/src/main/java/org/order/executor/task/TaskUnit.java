package org.order.executor.task;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 将任务统一抽象为任务单元
 *
 * @author chaobo
 * @date 2024/12/23
 */
public interface TaskUnit {

    // 检查任务是否可以执行
    default boolean check() {
        return true;
    }

    // 执行任务前做到预处理操作
    default void before() {}

    /**
     *  任务单元执行前先判断表达式，通过表达式判断是否执行，通过表达式判断是否继续执行后续任务单元
     *  执行时，先创建流程任务，然后执行子任务单元，再执行后续任务单元，最后执行当前任务单元的 doExecute() 方法
     *  所有任务执行完成后，执行任务单元的 after() 方法，更新当前任务状态。
     */
    default void execute() {
        List<TaskUnit> subTasks = subTaskUnits();
        if (CollectionUtils.isNotEmpty(subTasks)) {
            for (TaskUnit task : subTasks) {
                if (task.check()) {
                    task.before();

                    task.execute();

                    task.after();
                }
            }
        }


        List<TaskUnit> nextTasks = nextTaskUnits();
        if (CollectionUtils.isNotEmpty(nextTasks)) {
            for (TaskUnit task : nextTasks) {
                if (task.check()) {
                    task.before();

                    task.execute();

                    task.after();
                }
            }
        }

        doExecute();
    }

    void doExecute();

    // 执行任务后做到收尾操作
    default void after() {}

    // 子任务单元列表
    List<TaskUnit> subTaskUnits();

    // 下一个任务单元列表
    List<TaskUnit> nextTaskUnits();

}
