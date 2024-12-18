package org.order.domain.aggregate;

import lombok.*;
import org.order.domain.entity.Rule;
import org.order.domain.entity.RuleItem;

import java.util.List;

/**
 * 规则聚合
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RuleAggregate extends Rule {

    private List<RuleItem> items;

    public RuleAggregate(String name, String description, String expression, Integer resultType, List<RuleItem> items) {
        super(name, description, expression, resultType);
        this.items = items;
    }

    public RuleAggregate(Long id, String name, String description, String expression, Integer resultType, List<RuleItem> items) {
        super(id, name, description, expression, resultType);
        this.items = items;
    }

}
