package org.order.repository.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.FlowLine;
import org.order.domain.repository.FlowLineRepository;
import org.order.repository.dao.FlowLineDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 流程边仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class FlowLineRepositoryImpl extends BaseRepositoryImpl<FlowLine, Long> implements FlowLineRepository {

    @Autowired
    private FlowLineDao flowLineDao;

    @Override
    public void deleteByFlowId(Long flowId) {
        flowLineDao.deleteByFlowId(flowId);
    }

    @Override
    public Optional<List<FlowLine>> findByFlowId(Long flowId) {
        return flowLineDao.findByFlowId(flowId);
    }

    @Override
    public List<FlowLine> findFlowLineByFlowIdWithEx(Long flowId) {
        Optional<List<FlowLine>> linesOp = findByFlowId(flowId);
        if (linesOp.isEmpty() || CollectionUtils.isEmpty(linesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_LINE_IS_EMPTY);
        }
        return linesOp.get();
    }
}
