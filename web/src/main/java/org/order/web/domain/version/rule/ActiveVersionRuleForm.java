package org.order.web.domain.version.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发布版本规则
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveVersionRuleForm {

    private Long ruleId;

    private Integer version;

}
