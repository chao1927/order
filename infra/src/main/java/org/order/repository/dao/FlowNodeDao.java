package org.order.repository.dao;

import org.order.domain.entity.FlowNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 流程节点 dao
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface FlowNodeDao extends JpaRepository<FlowNode, Long> {

    void deleteByFlowId(Long flowId);

    Optional<List<FlowNode>> findByTypeAndRefId(Integer type, Long refId);

    Optional<List<FlowNode>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion);

    Optional<List<FlowNode>> findByFlowId(Long flowId);
}
