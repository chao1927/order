package org.order.executor.task;

import lombok.extern.slf4j.Slf4j;
import org.order.executor.task.action.ActionTaskRunner;
import org.order.executor.task.action.ActionTaskUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 任务执行器
 *
 * @author chaobo
 * @date 2024/12/25
 */
@Slf4j
@Component
public class ActionTaskExecutor {

    private static final int CORE_POOL_SIZE = 100;

    private static final int MAXIMUM_POOL_SIZE = 100;

    private static final int KEEP_ALIVE_TIME = 0;

    private static final int QUEUE_CAPACITY = 100000;

    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, workQueue);

    @Autowired
    private ActionTaskRunner taskRunner;

    public void submitTask(ActionTaskUnit taskUnit) {
        executor.submit(() -> {
            taskRunner.run(taskUnit); // 执行任务逻辑
            taskRunner.callback(taskUnit);  // 执行回调逻辑
        });
    }

    public void shutdown() {
        executor.shutdown();
    }



}
