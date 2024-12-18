package org.order.application.command.action;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ActionAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除 action command handler
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class DeleteActionCommandHandler implements CommandHandler<DeleteActionCommand> {

    @Autowired
    private ActionAggregateService actionAggregateService;


    @Override
    public void handle(DeleteActionCommand command) {
        actionAggregateService.delete(command.getId());
    }
}
