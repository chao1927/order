package org.order.web.converter.flow;

import org.order.application.command.flow.CreateFlowLineCommand;
import org.order.application.command.flow.CreateFlowNodeCommand;
import org.order.application.command.flow.CreateFlowCommand;
import org.order.web.domain.flow.CreateFlowForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建流程转换器
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Component
public class CreateFlowConverter {

    public CreateFlowCommand convert(CreateFlowForm form) {
        List<CreateFlowNodeCommand> nodes = form.getNodes()
                .stream()
                .map(node -> new CreateFlowNodeCommand(
                        node.getName(),
                        node.getDescription(),
                        node.getType(),
                        node.getContent(),
                        node.getRefId(),
                        node.getRefVersion())
                ).collect(Collectors.toList());

        List<CreateFlowLineCommand> lines = form.getLines().stream().map(line -> new CreateFlowLineCommand(
                line.getPreNodeName(), line.getNextNodeName(), line.getContent()
        )).collect(Collectors.toList());


        return new CreateFlowCommand(form.getName(), form.getDescription(), nodes, lines);
    }

}
