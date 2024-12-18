package org.order.application.command.entry;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.EntryAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveEntryCommandHandler implements CommandHandler<ActiveEntryCommand> {


    @Autowired
    private EntryAggregateService entryAggregateService;


    @Override
    public void handle(ActiveEntryCommand command) {
        entryAggregateService.active(command.getId());
    }
}