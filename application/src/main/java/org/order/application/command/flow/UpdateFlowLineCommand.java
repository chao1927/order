package org.order.application.command.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改流程边 command
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowLineCommand {

    private Long id;

    private Long flowId;

    private Long preNodeId;

    private String preNodeName;

    private Long nextNodeId;

    private String nextNodeName;

    private String content;

}
