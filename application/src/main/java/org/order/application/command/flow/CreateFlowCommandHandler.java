package org.order.application.command.flow;

import org.order.application.command.CommandHandler;
import org.order.application.converter.CreateFlowCommandConverter;
import org.order.domain.aggregate.FlowAggregate;
import org.order.domain.aggregate.FlowAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创建流程 command handler
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class CreateFlowCommandHandler implements CommandHandler<CreateFlowCommand> {

    @Autowired
    private FlowAggregateService flowAggregateService;

    @Autowired
    private CreateFlowCommandConverter createFlowCommandConverter;

    @Override
    public void handle(CreateFlowCommand command) {
        FlowAggregate flowAggregate = createFlowCommandConverter.convert(command);
        flowAggregateService.create(flowAggregate);
    }
}
