package org.order.application.command.rule;

import org.order.application.command.CommandHandler;
import org.order.application.converter.UpdateRuleCommandConverter;
import org.order.domain.aggregate.RuleAggregate;
import org.order.domain.aggregate.RuleAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改规则命令处理器
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Component
public class UpdateRuleCommandHandler implements CommandHandler<UpdateRuleCommand> {

    @Autowired
    private RuleAggregateService ruleAggregateService;

    @Autowired
    private UpdateRuleCommandConverter updateRuleCommandConverter;


    @Override
    public void handle(UpdateRuleCommand command) {
        RuleAggregate ruleAggregate = updateRuleCommandConverter.convert(command);
        ruleAggregateService.update(ruleAggregate);
    }
}
