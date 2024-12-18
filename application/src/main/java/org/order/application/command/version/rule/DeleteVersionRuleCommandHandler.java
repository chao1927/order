package org.order.application.command.version.rule;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.RuleAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除版本规则处理器
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Component
public class DeleteVersionRuleCommandHandler implements CommandHandler<DeleteVersionRuleCommand> {


    @Autowired
    private RuleAggregateService ruleAggregateService;


    @Override
    public void handle(DeleteVersionRuleCommand command) {
        ruleAggregateService.delete(command.getRuleId(), command.getVersion());
    }
}
