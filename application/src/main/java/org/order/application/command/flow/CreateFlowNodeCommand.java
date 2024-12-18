package org.order.application.command.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建流程节点 command
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlowNodeCommand {

    private String name;

    private String description;

    private Integer type;

    private String content;

    private Long refId;

    private Integer refVersion;

}
