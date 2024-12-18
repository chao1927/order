package org.order.application.command.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除规则
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRuleCommand {

    private Long id;

}
