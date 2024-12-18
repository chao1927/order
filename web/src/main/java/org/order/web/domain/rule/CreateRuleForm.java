package org.order.web.domain.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建规则表单数据
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRuleForm {

    private String name;

    private String description;

    private String expression;

    private Integer resultType;

    private List<CreateRuleItemForm> items;

}
