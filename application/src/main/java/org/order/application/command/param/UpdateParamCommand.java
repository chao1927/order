package org.order.application.command.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chaobo
 * @date 2024/11/9 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParamCommand {

    private Long id;

    private String name;

    private String description;

    private String expression;

    private Integer resultType;

}
