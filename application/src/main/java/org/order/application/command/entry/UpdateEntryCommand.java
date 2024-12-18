package org.order.application.command.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改 入口
 * @author chaobo
 * @date 2024/11/16 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEntryCommand {

    private Long id;

    private String name;

    private String description;

    private String expression;

    private Long flowId;

    private Integer flowVersion;

}
