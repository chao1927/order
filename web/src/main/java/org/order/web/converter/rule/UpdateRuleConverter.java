package org.order.web.converter.rule;

import org.order.application.command.rule.UpdateRuleItemCommand;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.web.domain.rule.UpdateRuleForm;
import org.order.application.command.rule.UpdateRuleCommand;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 修改规则数据时的表单数据转换
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Component
public class UpdateRuleConverter {

    public UpdateRuleCommand convert(UpdateRuleForm form) {
        List<UpdateRuleItemCommand> ruleItemCommands = form.getItems().stream().map(item -> {
            RuleItemTypeEnum itemType = RuleItemTypeEnum.getByCode(item.getType());
            return new UpdateRuleItemCommand(
                    item.getId(),
                    item.getRuleId(),
                    item.getSort(),
                    item.getName(),
                    itemType,
                    item.getRefId(),
                    item.getRefVersion()
            );
        }).collect(Collectors.toList());

        return new UpdateRuleCommand(
                form.getId(),
                form.getName(),
                form.getDescription(),
                form.getExpression(),
                form.getResultType(),
                ruleItemCommands
        );
    }

}
