package org.order.application.command.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改流程节点 command
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowNodeCommand {

    private Long id;

    private Long flowId;

    private String name;

    private String description;

    private Integer type;

    private String content;

    private Long refId;

    private Integer refVersion;

}
