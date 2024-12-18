package org.order.application.command.flow;

import org.order.application.command.CommandHandler;
import org.order.application.converter.UpdateFlowCommandConverter;
import org.order.domain.aggregate.FlowAggregate;
import org.order.domain.aggregate.FlowAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改流程命令处理器
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class UpdateFlowCommandHandler implements CommandHandler<UpdateFlowCommand> {


    @Autowired
    private FlowAggregateService flowAggregateService;

    @Autowired
    private UpdateFlowCommandConverter updateFlowCommandConverter;


    @Override
    public void handle(UpdateFlowCommand command) {
        FlowAggregate flowAggregate = updateFlowCommandConverter.convert(command);
        flowAggregateService.update(flowAggregate);
    }
}
