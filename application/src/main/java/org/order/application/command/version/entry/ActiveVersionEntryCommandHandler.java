package org.order.application.command.version.entry;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.EntryAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发布版本入口 命令处理器
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class ActiveVersionEntryCommandHandler implements CommandHandler<ActiveVersionEntryCommand> {

    @Autowired
    private EntryAggregateService entryAggregateService;

    @Override
    public void handle(ActiveVersionEntryCommand command) {
        entryAggregateService.active(command.getEntryId(), command.getVersion());
    }
}
