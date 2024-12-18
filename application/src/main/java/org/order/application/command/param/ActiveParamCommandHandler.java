package org.order.application.command.param;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ParamAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chaobo
 * @date 2024/11/9 16:45
 */
@Component
public class ActiveParamCommandHandler implements CommandHandler<ActiveParamCommand> {

    @Autowired
    private ParamAggregateService paramAggregateService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handle(ActiveParamCommand command) {
        paramAggregateService.active(command.getId());
    }
}
