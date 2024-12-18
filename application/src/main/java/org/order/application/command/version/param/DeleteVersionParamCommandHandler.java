package org.order.application.command.version.param;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ParamAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author chaobo
 * @date 2024/11/9 17:06
 */
@Component
public class DeleteVersionParamCommandHandler implements CommandHandler<DeleteVersionParamCommand> {


    @Autowired
    private ParamAggregateService paramAggregateService;


    @Override
    public void handle(DeleteVersionParamCommand command) {
        paramAggregateService.delete(command.getParamId(), command.getVersion());
    }
}
