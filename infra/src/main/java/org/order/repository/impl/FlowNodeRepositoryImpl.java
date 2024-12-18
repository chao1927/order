package org.order.repository.impl;

import org.order.common.enums.FlowNodeTypeEnum;
import org.order.domain.entity.FlowNode;
import org.order.domain.repository.FlowNodeRepository;
import org.order.repository.dao.FlowNodeDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 流程解除仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class FlowNodeRepositoryImpl extends BaseRepositoryImpl<FlowNode, Long> implements FlowNodeRepository {

    @Autowired
    private FlowNodeDao flowNodeDao;

    @Override
    public void deleteByFlowId(Long flowId) {
        flowNodeDao.deleteByFlowId(flowId);
    }

    @Override
    public Optional<List<FlowNode>> findByTypeAndRefId(Integer type, Long refId) {
        return flowNodeDao.findByTypeAndRefId(type, refId);
    }

    @Override
    public Optional<List<FlowNode>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion) {
        return flowNodeDao.findByTypeAndRefIdAndRefVersion(type, refId, refVersion);
    }

    @Override
    public Optional<List<FlowNode>> findByFlowId(Long flowId) {
        return flowNodeDao.findByFlowId(flowId);
    }
}
