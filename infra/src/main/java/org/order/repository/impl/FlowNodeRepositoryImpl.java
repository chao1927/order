package org.order.repository.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.FlowNodeTypeEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.FlowNode;
import org.order.domain.repository.FlowNodeRepository;
import org.order.domain.repository.version.VersionActionRepository;
import org.order.domain.repository.version.VersionRuleRepository;
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

    @Autowired
    private VersionActionRepository versionActionRepository;

    @Autowired
    private VersionRuleRepository versionRuleRepository;

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

    @Override
    public List<FlowNode> findFlowNodeByFlowIdWithEx(Long flowId) {
        Optional<List<FlowNode>> nodesOp = findByFlowId(flowId);
        if (nodesOp.isEmpty() || CollectionUtils.isEmpty(nodesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
        }
        return nodesOp.get();
    }

    @Override
    public void checkRefByFlow(FlowNodeTypeEnum type, Long refId) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<FlowNode>> nodesOp = findByTypeAndRefId(type.getCode(), refId);
        checkResult(nodesOp, type);
    }

    @Override
    public void checkRefByFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<FlowNode>> nodesOp = findByTypeAndRefIdAndRefVersion(type.getCode(), refId, refVersion);
        checkResult(nodesOp, type);
    }

    @Override
    public void checkFlowNodeActive(List<FlowNode> nodes) {
        nodes.forEach(node -> {
            FlowNodeTypeEnum type = FlowNodeTypeEnum.getByCode(node.getType());
            if (null == type) {
                throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
            }

            switch (type) {
                case ACTION:
                    // 校验动作是否已发布
                    versionActionRepository.checkVersionActionActive(node.getRefId(), node.getRefVersion());
                    break;
                case RULE:
                    // 校验规则是否已发布
                    versionRuleRepository.checkVersionRuleActive(node.getRefId(), node.getRefVersion());
                    break;
                default:
            }
        });
    }

    private <T> void checkResult(Optional<List<T>> nodesOp, FlowNodeTypeEnum type) {
        if (nodesOp.isPresent() && CollectionUtils.isNotEmpty(nodesOp.get())) {
            switch (type) {
                case ACTION:
                    throw new CustomBusinessException(ErrorCode.ACTION_REFERENCED_BY_FLOW);
                case RULE:
                    throw new CustomBusinessException(ErrorCode.RULE_REFERENCED_BY_FLOW);
                default:
            }
        }
    }
}
