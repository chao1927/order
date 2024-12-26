package org.order.executor.task.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.executor.task.TaskUnit;


/**
 * 流程任务单元
 *
 * @author chaobo
 * @date 2024/12/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowTaskUnit implements TaskUnit {

    private String bizId;

    private Long flowId;

    private Integer flowVersion;

    private String entryTaskNo;



    @Override
    public String bizId() {
        return bizId;
    }

}
