package org.order.web.converter.rule;

import org.order.application.command.rule.CreateRuleItemCommand;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.web.domain.rule.CreateRuleForm;
import org.order.application.command.rule.CreateRuleCommand;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建规则数据时的表单转换
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Component
public class CreateRuleConverter {

    public CreateRuleCommand convert(CreateRuleForm form) {
        List<CreateRuleItemCommand> ruleItemCommands = form.getItems().stream().map(item -> {
            RuleItemTypeEnum itemType = RuleItemTypeEnum.getByCode(item.getType());
            return new CreateRuleItemCommand(
                    item.getSort(),
                    item.getName(),
                    itemType,
                    item.getRefId(),
                    item.getRefVersion()
            );
        }).collect(Collectors.toList());

        return new CreateRuleCommand(
                form.getName(),
                form.getDescription(),
                form.getExpression(),
                form.getResultType(),
                ruleItemCommands
        );
    }

}
