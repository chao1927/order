package org.order.application.command.version.param;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ParamAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chaobo
 * @date 2024/11/9 17:02
 */
@Component
public class InactiveVersionParamCommandHandler implements CommandHandler<InactiveVersionParamCommand> {


    @Autowired
    private ParamAggregateService paramAggregateService;


    @Override
    public void handle(InactiveVersionParamCommand command) {
        paramAggregateService.inactive(command.getParamId(), command.getVersion());
    }
}
