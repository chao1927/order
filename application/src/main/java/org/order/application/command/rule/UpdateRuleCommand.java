package org.order.application.command.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 修改规则
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRuleCommand {

    private Long id;

    private String name;

    private String description;

    private String expression;

    private Integer resultType;

    private List<UpdateRuleItemCommand> items;


}
