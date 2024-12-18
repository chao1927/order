package org.order.application.converter;

import org.order.application.command.flow.UpdateFlowCommand;
import org.order.domain.aggregate.FlowAggregate;
import org.order.domain.entity.FlowLine;
import org.order.domain.entity.FlowNode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 更新流程命令转换器
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Component
public class UpdateFlowCommandConverter {

    public FlowAggregate convert(UpdateFlowCommand command) {
        List<FlowNode> nodes = command.getNodes().stream().map(node -> new FlowNode(
                node.getId(),
                node.getFlowId(),
                node.getName(),
                node.getDescription(),
                node.getType(),
                node.getContent(),
                node.getRefId(),
                node.getRefVersion()
        )).collect(Collectors.toList());

        List<FlowLine> lines = command.getLines().stream().map(line -> new FlowLine(
                line.getId(),
                line.getFlowId(),
                line.getPreNodeId(),
                line.getPreNodeName(),
                line.getNextNodeId(),
                line.getNextNodeName(),
                line.getContent()
        )).collect(Collectors.toList());


        return new FlowAggregate(
                command.getId(),
                command.getName(),
                command.getDescription(),
                nodes,
                lines
        );
    }

}
