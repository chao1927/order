package org.order.domain.repository;

import org.order.common.enums.FlowNodeTypeEnum;
import org.order.domain.entity.FlowNode;

import java.util.List;
import java.util.Optional;

/**
 * 流程节点 仓储接口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface FlowNodeRepository extends BaseRepository<FlowNode, Long> {
    void deleteByFlowId(Long flowId);

    Optional<List<FlowNode>> findByTypeAndRefId(Integer type, Long refId);

    Optional<List<FlowNode>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion);

    Optional<List<FlowNode>> findByFlowId(Long flowId);

    List<FlowNode> findFlowNodeByFlowIdWithEx(Long flowId);

    void checkRefByFlow(FlowNodeTypeEnum type, Long refId);

    void checkRefByFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion);

    void checkFlowNodeActive(List<FlowNode> nodes);

}
