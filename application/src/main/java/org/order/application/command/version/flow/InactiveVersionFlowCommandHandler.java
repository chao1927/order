package org.order.application.command.version.flow;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.FlowAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 下架流程 命令处理器
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class InactiveVersionFlowCommandHandler implements CommandHandler<InactiveVersionFlowCommand> {

    @Autowired
    private FlowAggregateService flowAggregateService;


    @Override
    public void handle(InactiveVersionFlowCommand command) {
        flowAggregateService.inactive(command.getFlowId(), command.getVersion());
    }
}
