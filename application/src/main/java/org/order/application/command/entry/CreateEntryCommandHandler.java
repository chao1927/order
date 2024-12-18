package org.order.application.command.entry;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.EntryAggregateService;
import org.order.domain.entity.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创建入口 命令处理器
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Component
public class CreateEntryCommandHandler implements CommandHandler<CreateEntryCommand> {

    @Autowired
    private EntryAggregateService entryAggregateService;

    @Override
    public void handle(CreateEntryCommand command) {
        entryAggregateService.create(new Entry(
                command.getName(),
                command.getDescription(),
                command.getExpression(),
                command.getFlowId(),
                command.getFlowVersion()
        ));
    }
}
