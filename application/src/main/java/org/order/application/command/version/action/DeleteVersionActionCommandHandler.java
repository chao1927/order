package org.order.application.command.version.action;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ActionAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除版本action command handler
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class DeleteVersionActionCommandHandler implements CommandHandler<DeleteVersionActionCommand> {


    @Autowired
    private ActionAggregateService actionAggregateService;

    @Override
    public void handle(DeleteVersionActionCommand command) {
        actionAggregateService.delete(command.getActionId(), command.getVersion());
    }
}
