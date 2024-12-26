package org.order.executor.task.flow;

import org.order.executor.task.TaskRunner;
import org.springframework.stereotype.Component;

/**
 * 流程节点任务运行器
 *
 * @author chaobo
 * @date 2024/12/26
 */
@Component
public class FlowNodeTaskRunner implements TaskRunner<FlowNodeTaskUnit> {



    @Override
    public void run(FlowNodeTaskUnit taskUnit) {

    }

    @Override
    public void callback(FlowNodeTaskUnit taskUnit) {

    }
}
