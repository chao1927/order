package org.order.application.command.version.param;

import lombok.extern.slf4j.Slf4j;
import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ParamAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author chaobo
 * @date 2024/11/9 16:45
 */
@Slf4j
@Component
public class ActiveVersionParamCommandHandler implements CommandHandler<ActiveVersionParamCommand> {

    @Autowired
    private ParamAggregateService paramAggregateService;


    @Override
    public void handle(ActiveVersionParamCommand command) {
        paramAggregateService.active(command.getParamId(), command.getVersion());
    }
}
