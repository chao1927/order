package org.order.application.converter;

import org.order.application.command.rule.CreateRuleCommand;
import org.order.domain.aggregate.RuleAggregate;
import org.order.domain.entity.RuleItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建规则转换成规则聚合
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Component
public class CreateRuleCommandConverter {

    public RuleAggregate convert(CreateRuleCommand command) {
        List<RuleItem> items = command.getItems().stream().map(item -> new RuleItem(
                item.getSort(),
                item.getName(),
                item.getType().getCode(),
                item.getRefId(),
                item.getRefVersion()
        )).collect(Collectors.toList());

        return new RuleAggregate(
                command.getName(),
                command.getDescription(),
                command.getExpression(),
                command.getResultType(),
                items
        );
    }

}
