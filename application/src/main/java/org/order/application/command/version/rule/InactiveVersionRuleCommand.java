package org.order.application.command.version.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下架版本规则
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactiveVersionRuleCommand {

    private Long ruleId;

    private Integer version;

}