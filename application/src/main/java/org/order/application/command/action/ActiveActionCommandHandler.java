package org.order.application.command.action;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ActionAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发布 Action command handler
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class ActiveActionCommandHandler implements CommandHandler<ActiveActionCommand> {

    @Autowired
    private ActionAggregateService actionAggregateService;

    @Override
    public void handle(ActiveActionCommand command) {
        actionAggregateService.active(command.getId());
    }
}
