package org.order.application.command.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建入口
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEntryCommand {

    private String name;

    private String description;

    private String expression;

    private Long flowId;

    private Integer flowVersion;

}
