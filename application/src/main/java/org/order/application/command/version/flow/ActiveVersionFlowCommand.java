package org.order.application.command.version.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发布流程 命令
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveVersionFlowCommand {

    private Long flowId;

    private Integer version;

}
