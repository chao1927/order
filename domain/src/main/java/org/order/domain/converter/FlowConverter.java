package org.order.domain.converter;

import org.order.domain.entity.FlowLine;
import org.order.domain.entity.FlowNode;
import org.order.domain.entity.version.VersionFlowLine;
import org.order.domain.entity.version.VersionFlowNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程相关转换
 *
 * @author chaobo
 * @date 2024/12/24
 */
public class FlowConverter {

    public static List<FlowNode> convertNewFlowNodes(Long flowId, List<FlowNode> flowNodes) {
        return flowNodes.stream().map(node -> new FlowNode(
                flowId,
                node.getName(),
                node.getDescription(),
                node.getType(),
                node.getContent(),
                node.getRefId(),
                node.getRefVersion()
        )).collect(Collectors.toList());
    }

    public static List<FlowLine> convertNewFlowLines(Long flowId, List<FlowLine> flowLines) {
        return flowLines.stream().map(line -> new FlowLine(
                flowId,
                line.getPreNodeId(),
                line.getPreNodeName(),
                line.getNextNodeId(),
                line.getNextNodeName(),
                line.getContent()
        )).collect(Collectors.toList());
    }

    public static List<VersionFlowNode> convertNewVersionFlowNodes(Long versionFlowId, List<FlowNode> flowNodes) {
        return flowNodes.stream().map(node -> new VersionFlowNode(
                versionFlowId,
                node.getName(),
                node.getDescription(),
                node.getType(),
                node.getContent(),
                node.getRefId(),
                node.getRefVersion()
        )).collect(Collectors.toList());
    }

    public static List<VersionFlowLine> convertNewVersionFlowLines(Long versionFlowId, List<FlowLine> flowLines) {
        return flowLines.stream().map(line -> new VersionFlowLine(
                versionFlowId,
                line.getPreNodeId(),
                line.getPreNodeName(),
                line.getNextNodeId(),
                line.getNextNodeName(),
                line.getContent()
        )).collect(Collectors.toList());
    }

}
