package org.order.executor.task.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.order.executor.cache.domain.VersionFlowNodeConfig;
import org.order.executor.task.TaskUnit;

/**
 * 流程节点任务
 *
 * @author chaobo
 * @date 2024/12/26
 */
@Data
@AllArgsConstructor
public class FlowNodeTaskUnit implements TaskUnit {

    private String bizId;

    private String flowTaskNo;

    private VersionFlowNodeConfig flowNodeConfig;


    @Override
    public String bizId() {
        return bizId;
    }

}
