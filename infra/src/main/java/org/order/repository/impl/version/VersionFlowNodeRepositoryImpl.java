package org.order.repository.impl.version;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.FlowNodeTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.VersionFlowNode;
import org.order.domain.repository.version.VersionActionRepository;
import org.order.domain.repository.version.VersionFlowNodeRepository;
import org.order.domain.repository.version.VersionRuleRepository;
import org.order.repository.dao.version.VersionFlowNodeDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程节点仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionFlowNodeRepositoryImpl extends BaseRepositoryImpl<VersionFlowNode, Long> implements VersionFlowNodeRepository {

    @Autowired
    private VersionFlowNodeDao versionFlowNodeDao;

    @Autowired
    private VersionActionRepository versionActionRepository;

    @Autowired
    private VersionRuleRepository versionRuleRepository;


    @Override
    public void deleteByVersionFlowId(Long versionFlowId) {
        versionFlowNodeDao.deleteByVersionFlowId(versionFlowId);
    }

    @Override
    public Optional<List<VersionFlowNode>> findByTypeAndRefId(Integer code, Long refId) {
        return versionFlowNodeDao.findByTypeAndRefId(code, refId);
    }

    @Override
    public Optional<List<VersionFlowNode>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion) {
        return versionFlowNodeDao.findByTypeAndRefIdAndRefVersion(type, refId, refVersion);
    }

    @Override
    public Optional<List<VersionFlowNode>> findByTypeAndRefIdAndRefVersionAndStatus(Integer type, Long refId, Integer refVersion, Integer status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<VersionFlowNode>> findByVersionFlowId(Long versionFlowId) {
        return versionFlowNodeDao.findByVersionFlowId(versionFlowId);
    }

    @Override
    public List<VersionFlowNode> findVersionFlowNodeByFlowIdWithEx(Long versionFlowId) {
        Optional<List<VersionFlowNode>> nodesOp = findByVersionFlowId(versionFlowId);
        if (nodesOp.isEmpty() || CollectionUtils.isEmpty(nodesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_IS_EMPTY);
        }
        return nodesOp.get();
    }

    @Override
    public void checkRefByVersionFlow(FlowNodeTypeEnum type, Long refId) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<VersionFlowNode>> nodesOp = findByTypeAndRefId(type.getCode(), refId);
        checkResult(nodesOp, type);
    }

    @Override
    public void checkRefByVersionFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<VersionFlowNode>> nodesOp = findByTypeAndRefIdAndRefVersion(type.getCode(), refId, refVersion);
        checkResult(nodesOp, type);
    }

    @Override
    public void checkRefByActiveVersionFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion) {
        if (!FlowNodeTypeEnum.ACTION.equals(type) && !FlowNodeTypeEnum.RULE.equals(type)) {
            throw new CustomBusinessException(ErrorCode.FLOW_NODE_TYPE_ERROR);
        }

        Optional<List<VersionFlowNode>> nodesOp
                = findByTypeAndRefIdAndRefVersionAndStatus(type.getCode(), refId, refVersion, StatusEnum.ACTIVATED.getCode());
        checkResult(nodesOp, type);
    }

    @Override
    public void checkVersionFlowNodeActive(List<VersionFlowNode> nodes) {
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
