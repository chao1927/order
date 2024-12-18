package org.order.web.domain.version.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除版本规则
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteVersionRuleForm {

    private Long ruleId;

    private Integer version;

}
