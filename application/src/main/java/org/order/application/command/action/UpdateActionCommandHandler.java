package org.order.application.command.action;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ActionAggregateService;
import org.order.domain.entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改 action command handler
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class UpdateActionCommandHandler implements CommandHandler<UpdateActionCommand> {


    @Autowired
    private ActionAggregateService actionAggregateService;

    @Override
    public void handle(UpdateActionCommand command) {
        actionAggregateService.update(new Action(command.getId(), command.getName(), command.getDescription()));
    }
}
