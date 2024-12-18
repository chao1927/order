package org.order.application.command.rule;

import lombok.extern.slf4j.Slf4j;
import org.order.application.command.CommandHandler;
import org.order.application.converter.CreateRuleCommandConverter;

import org.order.domain.aggregate.RuleAggregate;
import org.order.domain.aggregate.RuleAggregateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 创建规则命令处理器
 *
 * @author chaobo
 * @date 2024/11/10
 */
@Slf4j
@Component
public class CreateRuleCommandHandler implements CommandHandler<CreateRuleCommand> {


    @Autowired
    private CreateRuleCommandConverter createRuleCommandConverter;

    @Autowired
    private RuleAggregateService ruleAggregateService;


    @Override
    public void handle(CreateRuleCommand command) {
        RuleAggregate ruleAggregate = createRuleCommandConverter.convert(command);
        ruleAggregateService.create(ruleAggregate);
    }

}
