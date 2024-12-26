package org.order.executor.converter;

import org.order.common.enums.ErrorCode;
import org.order.common.enums.FlowNodeTypeEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.VersionFlow;
import org.order.domain.entity.version.VersionFlowLine;
import org.order.domain.entity.version.VersionFlowNode;
import org.order.executor.cache.domain.VersionFlowConfig;
import org.order.executor.cache.domain.VersionFlowLineConfig;
import org.order.executor.cache.domain.VersionFlowNodeConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程配置转换器
 *
 * @author chaobo
 * @date 2024/12/26
 */
public class FlowConfigConverter {

    public static VersionFlowConfig convertVersionFlowConfig(VersionFlow versionFlow,
                                                             List<VersionFlowNodeConfig> versionFlowNodeConfigs,
                                                             List<VersionFlowLineConfig> versionFlowLineConfigs) {

        VersionFlowNodeConfig startNode = null;
        Map<Long, VersionFlowNodeConfig> versionNodeId2NodeMap = new HashMap<>();
        for (VersionFlowNodeConfig node : versionFlowNodeConfigs) {
            if (node.isStartNode()) {
                startNode = node;
            }
            versionNodeId2NodeMap.put(node.getId(), node);
        }

        for (VersionFlowLineConfig line : versionFlowLineConfigs) {
            VersionFlowNodeConfig preNode = versionNodeId2NodeMap.get(line.getPreNodeId());
            if (null == preNode) {
                throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
            }

            VersionFlowNodeConfig nextNode = versionNodeId2NodeMap.get(line.getNextNodeId());
            if (null == nextNode) {
                throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
            }

            line.setPreNode(preNode);
            line.setNextNode(nextNode);

            preNode.addNextLine(line);
            nextNode.addPreLine(line);
        }

        return new VersionFlowConfig(
                versionFlow.getId(),
                versionFlow.getFlowId(),
                versionFlow.getVersion(),
                versionFlow.getName(),
                versionFlowLineConfigs,
                versionFlowNodeConfigs,
                startNode
        );
    }

    public static VersionFlowNodeConfig convertVersionFlowNodeConfig(VersionFlowNode versionFlowNode) {

        return new VersionFlowNodeConfig(
                versionFlowNode.getId(),
                versionFlowNode.getVersionFlowId(),
                versionFlowNode.getName(),
                FlowNodeTypeEnum.getByCode(versionFlowNode.getType()),
                versionFlowNode.getContent(),
                versionFlowNode.getRefId(),
                versionFlowNode.getRefVersion()
        );
    }

    public static VersionFlowLineConfig convertVersionFlowLineConfig(VersionFlowLine versionFlowLine) {
        return new VersionFlowLineConfig(
                versionFlowLine.getId(),
                versionFlowLine.getVersionFlowId(),
                versionFlowLine.getPreNodeId(),
                versionFlowLine.getPreNodeName(),
                versionFlowLine.getNextNodeId(),
                versionFlowLine.getNextNodeName(),
                versionFlowLine.getContent()
        );
    }

}
