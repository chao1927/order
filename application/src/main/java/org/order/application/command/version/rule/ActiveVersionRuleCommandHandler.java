package org.order.application.command.version.rule;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.RuleAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发布版本规则处理器
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Component
public class ActiveVersionRuleCommandHandler implements CommandHandler<ActiveVersionRuleCommand> {


    @Autowired
    private RuleAggregateService ruleAggregateService;


    @Override
    public void handle(ActiveVersionRuleCommand command) {
        ruleAggregateService.active(command.getRuleId(), command.getVersion());
    }
}
