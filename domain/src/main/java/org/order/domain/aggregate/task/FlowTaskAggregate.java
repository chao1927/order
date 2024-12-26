package org.order.domain.aggregate.task;

import org.order.domain.entity.task.FlowLineTask;
import org.order.domain.entity.task.FlowNodeTask;
import org.order.domain.entity.task.FlowTask;

/**
 * 流程任务聚合
 *
 * @author chaobo
 * @date 2024/12/23
 */
public class FlowTaskAggregate {
    public void save(FlowTask flowTask) {
        // todo 保存流程任务
    }

    public void saveFlowLineTask(FlowLineTask flowLineTask) {
        // todo 保存流程边任务
    }

    public void saveFlowNodeTask(FlowNodeTask flowLineTask) {
        // todo 保存流程边任务
    }
}
