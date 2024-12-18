package org.order.web.converter.flow;

import org.order.application.command.flow.UpdateFlowCommand;
import org.order.application.command.flow.UpdateFlowLineCommand;
import org.order.application.command.flow.UpdateFlowNodeCommand;
import org.order.web.domain.flow.UpdateFlowForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Component
public class UpdateFlowConverter {

    public UpdateFlowCommand convert(UpdateFlowForm form) {
        List<UpdateFlowNodeCommand> nodes = form.getNodes().stream().map(node -> new UpdateFlowNodeCommand(
                node.getId(),
                node.getFlowId(),
                node.getName(),
                node.getDescription(),
                node.getType(),
                node.getContent(),
                node.getRefId(),
                node.getRefVersion()
        )).collect(Collectors.toList());

        List<UpdateFlowLineCommand> lines = form.getLines().stream().map(line -> new UpdateFlowLineCommand(
                line.getId(),
                line.getFlowId(),
                line.getPreNodeId(),
                line.getPreNodeName(),
                line.getNextNodeId(),
                line.getNextNodeName(),
                line.getContent()
        )).collect(Collectors.toList());


        return new UpdateFlowCommand(form.getId(), form.getName(), form.getDescription(), nodes, lines);
    }

}
