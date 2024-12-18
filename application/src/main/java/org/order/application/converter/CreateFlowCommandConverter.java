package org.order.application.converter;

import org.order.application.command.flow.CreateFlowCommand;
import org.order.domain.aggregate.FlowAggregate;
import org.order.domain.entity.FlowLine;
import org.order.domain.entity.FlowNode;
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
public class CreateFlowCommandConverter {

    public FlowAggregate convert(CreateFlowCommand command) {
        List<FlowNode> nodes = command.getNodes().stream().map(node -> new FlowNode(
                node.getName(),
                node.getDescription(),
                node.getType(),
                node.getContent(),
                node.getRefId(),
                node.getRefVersion()
        )).collect(Collectors.toList());

        List<FlowLine> lines = command.getLines().stream().map(line -> new FlowLine(
                line.getPreNodeName(),
                line.getNextNodeName(),
                line.getContent()
        )).collect(Collectors.toList());

        return new FlowAggregate(
                command.getName(),
                command.getDescription(),
                nodes,
                lines
        );
    }

}
