package org.order.application.command.action;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ActionAggregateService;
import org.order.domain.entity.Action;
import org.order.domain.event.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 新增 action command handler
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class CreateActionCommandHandler implements CommandHandler<CreateActionCommand> {


    @Autowired
    private ActionAggregateService actionAggregateService;

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    @Override
    public void handle(CreateActionCommand command) {
        actionAggregateService.create(new Action(command.getName(), command.getDescription()));
    }
}
