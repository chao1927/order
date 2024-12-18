package org.order.application.command.entry;

import org.order.application.command.CommandHandler;
import org.order.domain.aggregate.EntryAggregateService;
import org.order.domain.entity.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateEntryCommandHandler implements CommandHandler<UpdateEntryCommand> {

    @Autowired
    private EntryAggregateService entryAggregateService;


    @Override
    public void handle(UpdateEntryCommand command) {
        entryAggregateService.update(new Entry(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getExpression(),
                command.getFlowId(),
                command.getFlowVersion()
        ));
    }
}