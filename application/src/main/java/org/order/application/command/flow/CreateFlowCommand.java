package org.order.application.command.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建流程
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlowCommand {

    private String name;

    private String description;

    private List<CreateFlowNodeCommand> nodes;

    private List<CreateFlowLineCommand> lines;

}
