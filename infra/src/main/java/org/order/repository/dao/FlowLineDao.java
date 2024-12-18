package org.order.repository.dao;

import org.order.domain.entity.FlowLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 流程边
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface FlowLineDao extends JpaRepository<FlowLine, Long> {

    void deleteByFlowId(Long flowId);

    Optional<List<FlowLine>> findByFlowId(Long flowId);
}
