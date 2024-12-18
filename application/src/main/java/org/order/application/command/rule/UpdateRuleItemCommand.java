package org.order.application.command.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.RuleItemTypeEnum;

/**
 * 修改规则时， 规则子项
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRuleItemCommand {

    private Long id;

    private Long ruleId;

    private Integer sort;

    private String name;

    /**
     * 规则子项类型（变量/规则）
     */
    private RuleItemTypeEnum type;

    /**
     * 规则子项id （变量id/规则id）
     */
    private Long refId;

    /**
     * 规则子项版本号
     */
    private Integer refVersion;


}
