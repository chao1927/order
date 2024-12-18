package org.order.repository.impl;

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
}
