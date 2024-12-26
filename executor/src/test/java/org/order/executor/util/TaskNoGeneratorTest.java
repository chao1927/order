package org.order.executor.util;

import org.junit.jupiter.api.Test;
import org.order.common.enums.TaskTypeEnum;
import org.order.executor.utils.TaskNoGenerator;

/**
 * 任务编号生成器测试
 *
 * @author chaobo
 * @date 2024/12/25
 */
public class TaskNoGeneratorTest {


    @Test
    public void testGenerate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            TaskNoGenerator.generate(TaskTypeEnum.FLOW);
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("Using ThreadLocal took: " + duration + " ms");
    }

}
