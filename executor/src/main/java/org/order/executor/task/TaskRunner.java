package org.order.executor.task;

/**
 * 任务执行器
 *
 * @author chaobo
 * @date 2024/12/25
 */
public interface TaskRunner<T extends TaskUnit> {

    void run(T taskUnit);

    void callback(T taskUnit);

}
