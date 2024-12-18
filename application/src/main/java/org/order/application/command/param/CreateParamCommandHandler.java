package org.order.application.command.param;

import lombok.extern.slf4j.Slf4j;
import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.ParamAggregateService;
import org.order.domain.entity.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chaobo
 * @date 2024/11/9 15:24
 */
@Slf4j
@Component
public class CreateParamCommandHandler implements CommandHandler<CreateParamCommand> {


    @Autowired
    private ParamAggregateService paramAggregateService;

    @Override
    public void handle(CreateParamCommand command) {
        paramAggregateService.create(new Param(
                command.getName(),
                command.getDescription(),
                command.getExpression(),
                command.getResultType()
        ));
    }
}
