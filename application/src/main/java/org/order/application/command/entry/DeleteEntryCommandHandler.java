package org.order.application.command.entry;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.EntryAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteEntryCommandHandler implements CommandHandler<DeleteEntryCommand> {

    @Autowired
    private EntryAggregateService entryAggregateService;

    @Override
    public void handle(DeleteEntryCommand command) {
        entryAggregateService.delete(command.getId());
    }
}