package org.order.application.converter;

import org.order.application.command.rule.UpdateRuleCommand;
import org.order.domain.aggregate.RuleAggregate;
import org.order.domain.entity.RuleItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 更新规则命令转换器
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Component
public class UpdateRuleCommandConverter {

    public RuleAggregate convert(UpdateRuleCommand command) {
        List<RuleItem> items = command.getItems().stream().map(item -> new RuleItem(
                item.getId(),
                item.getRuleId(),
                item.getSort(),
                item.getName(),
                item.getType().getCode(),
                item.getRefId(),
                item.getRefVersion()
        )).collect(Collectors.toList());

        return new RuleAggregate(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getExpression(),
                command.getResultType(),
                items
        );
    }

}
