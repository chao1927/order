package org.order.domain.repository;

import org.order.domain.entity.FlowLine;

import java.util.List;
import java.util.Optional;

/**
 * 流程边仓储
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface FlowLineRepository extends BaseRepository<FlowLine, Long> {
    void deleteByFlowId(Long flowId);

    Optional<List<FlowLine>> findByFlowId(Long flowId);
}
