package org.order.web.domain.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改规则时提交的数据
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRuleItemForm {

    private Long id;

    private Long ruleId;

    private Integer sort;

    private String name;

    /**
     * 规则子项类型（变量/规则）
     */
    private Integer type;

    /**
     * 规则子项id （变量id/规则id）
     */
    private Long refId;


    /**
     * 规则子项版本号
     */
    private Integer refVersion;


}
