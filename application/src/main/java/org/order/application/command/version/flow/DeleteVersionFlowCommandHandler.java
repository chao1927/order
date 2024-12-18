package org.order.application.command.version.flow;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.FlowAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除版本流程 命令处理器
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class DeleteVersionFlowCommandHandler implements CommandHandler<DeleteVersionFlowCommand> {

    @Autowired
    private FlowAggregateService flowAggregateService;

    @Override
    public void handle(DeleteVersionFlowCommand command) {
        flowAggregateService.delete(command.getFlowId(), command.getVersion());
    }
}
