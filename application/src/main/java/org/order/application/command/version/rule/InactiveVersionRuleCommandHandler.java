package org.order.application.command.version.rule;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.RuleAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 下架版本规则处理器
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Component
public class InactiveVersionRuleCommandHandler implements CommandHandler<InactiveVersionRuleCommand> {


    @Autowired
    private RuleAggregateService ruleAggregateService;


    @Override
    public void handle(InactiveVersionRuleCommand command) {
        ruleAggregateService.inactive(command.getRuleId(), command.getVersion());
    }

}
