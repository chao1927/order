package org.order.application.command.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建流程边 command
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlowLineCommand {

    private String preNodeName;

    private String nextNodeName;

    private String content;

}
