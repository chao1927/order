package org.order.application.command.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 修改流程 命令
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowCommand {

    private Long id;

    private String name;

    private String description;

    private List<UpdateFlowNodeCommand> nodes;

    private List<UpdateFlowLineCommand> lines;

}
