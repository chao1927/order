package org.order.application.command.version.action;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ActionAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 下架版本 action command handler
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class InactiveVersionActionCommandHandler implements CommandHandler<InactiveVersionActionCommand> {


    @Autowired
    private ActionAggregateService actionAggregateService;

    @Override
    public void handle(InactiveVersionActionCommand command) {
        actionAggregateService.inactive(command.getActionId(), command.getVersion());
    }
}
