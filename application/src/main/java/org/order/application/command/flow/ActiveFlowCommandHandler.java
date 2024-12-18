package org.order.application.command.flow;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.FlowAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发布流程 命令处理器
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class ActiveFlowCommandHandler implements CommandHandler<ActiveFlowCommand> {

    @Autowired
    private FlowAggregateService flowAggregateService;

    @Override
    public void handle(ActiveFlowCommand command) {
        flowAggregateService.active(command.getId());
    }
}
