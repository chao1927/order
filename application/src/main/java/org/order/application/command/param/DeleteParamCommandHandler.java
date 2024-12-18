package org.order.application.command.param;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ParamAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author chaobo
 * @date 2024/11/9 17:06
 */
@Component
public class DeleteParamCommandHandler implements CommandHandler<DeleteParamCommand> {


    @Autowired
    private ParamAggregateService paramAggregateService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handle(DeleteParamCommand command) {
        paramAggregateService.delete(command.getId());
    }
}
