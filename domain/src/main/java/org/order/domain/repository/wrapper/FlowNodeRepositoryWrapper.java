package org.order.domain.repository.wrapper;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.FlowNodeTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.FlowNode;
import org.order.domain.entity.version.VersionFlowNode;
import org.order.domain.repository.FlowNodeRepository;
import org.order.domain.repository.version.VersionFlowNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 流程节点校验
 *
 * @author chaobo
 * @date 2024/11/21
 */
@Component
public class FlowNodeRepositoryWrapper {


    @Autowired
    private FlowNodeRepository flowNodeRepository;

    @Autowired
    private VersionFlowNodeRepository versionFlowNodeRepository;

    @Autowired
    private RuleRepositoryWrapper ruleRepositoryWrapper;

    @Autowired
    private ActionRepositoryWrapper actionRepositoryWrapper;


    public void checkRefByFlow(FlowNodeTypeEnum type, Long refId) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<FlowNode>> nodesOp = flowNodeRepository.findByTypeAndRefId(type.getCode(), refId);
        checkResult(nodesOp, type);
    }

    public void checkRefByVersionFlow(FlowNodeTypeEnum type, Long refId) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<VersionFlowNode>> nodesOp = versionFlowNodeRepository.findByTypeAndRefId(type.getCode(), refId);
        checkResult(nodesOp, type);
    }

    public void checkRefByFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<FlowNode>> nodesOp = flowNodeRepository.findByTypeAndRefIdAndRefVersion(type.getCode(), refId, refVersion);
        checkResult(nodesOp, type);
    }

    public void checkRefByVersionFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<VersionFlowNode>> nodesOp = versionFlowNodeRepository.findByTypeAndRefIdAndRefVersion(type.getCode(), refId, refVersion);
        checkResult(nodesOp, type);
    }

    public void checkRefByActiveVersionFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<VersionFlowNode>> nodesOp
                = versionFlowNodeRepository.findByTypeAndRefIdAndRefVersionAndStatus(type.getCode(), refId, refVersion,
                StatusEnum.ACTIVATED.getCode());
        checkResult(nodesOp, type);
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

    /******************************** 校验引用的流程节点是否都已发布 ************************************************/

    public void checkFlowNodeActive(List<FlowNode> nodes) {
        nodes.forEach(node -> {
            FlowNodeTypeEnum type = FlowNodeTypeEnum.getByCode(node.getType());
            if (null == type) {
                throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
            }

            switch (type) {
                case ACTION:
                    // 校验动作是否已发布
                    actionRepositoryWrapper.checkVersionActionActive(node.getRefId(), node.getRefVersion());
                    break;
                case RULE:
                    // 校验规则是否已发布
                    ruleRepositoryWrapper.checkVersionRuleActive(node.getRefId(), node.getRefVersion());
                    break;
                default:
            }
        });
    }

    public void checkVersionFlowNodeActive(List<VersionFlowNode> nodes) {
        nodes.forEach(node -> {
            FlowNodeTypeEnum type = FlowNodeTypeEnum.getByCode(node.getType());
            if (null == type) {
                throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
            }

            switch (type) {
                case ACTION:
                    // 校验动作是否已发布
                    actionRepositoryWrapper.checkVersionActionActive(node.getRefId(), node.getRefVersion());
                    break;
                case RULE:
                    // 校验规则是否已发布
                    ruleRepositoryWrapper.checkVersionRuleActive(node.getRefId(), node.getRefVersion());
                    break;
                default:
            }
        });
    }

    public List<FlowNode> findFlowNodeByFlowIdWithEx(Long flowId) {
        Optional<List<FlowNode>> nodesOp = flowNodeRepository.findByFlowId(flowId);
        if (!nodesOp.isPresent() || CollectionUtils.isEmpty(nodesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
        }
        return nodesOp.get();
    }

    public List<VersionFlowNode> findVersionFlowNodeByFlowIdWithEx(Long versionFlowId) {
        Optional<List<VersionFlowNode>> nodesOp = versionFlowNodeRepository.findByVersionFlowId(versionFlowId);
        if (!nodesOp.isPresent() || CollectionUtils.isEmpty(nodesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
        }
        return nodesOp.get();
    }

}
