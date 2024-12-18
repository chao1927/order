package org.order.application.command.rule;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.RuleAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除规则处理器
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Component
public class DeleteRuleCommandHandler implements CommandHandler<DeleteRuleCommand> {


    @Autowired
    private RuleAggregateService ruleAggregateService;


    @Override
    public void handle(DeleteRuleCommand command) {
        ruleAggregateService.delete(command.getId());
    }
}
