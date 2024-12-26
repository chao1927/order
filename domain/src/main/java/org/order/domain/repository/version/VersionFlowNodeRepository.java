package org.order.domain.repository.version;

import org.order.common.enums.FlowNodeTypeEnum;
import org.order.domain.entity.version.VersionFlowNode;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程节点
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface VersionFlowNodeRepository extends BaseRepository<VersionFlowNode, Long> {
    void deleteByVersionFlowId(Long versionFlowId);

    Optional<List<VersionFlowNode>> findByTypeAndRefId(Integer type, Long refId);

    Optional<List<VersionFlowNode>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion);

    Optional<List<VersionFlowNode>> findByTypeAndRefIdAndRefVersionAndStatus(Integer type, Long refId, Integer refVersion, Integer status);

    Optional<List<VersionFlowNode>> findByVersionFlowId(Long versionFlowId);

    List<VersionFlowNode> findVersionFlowNodeByFlowIdWithEx(Long versionFlowId);

    void checkRefByVersionFlow(FlowNodeTypeEnum type, Long refId);

    void checkRefByVersionFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion);

    void checkRefByActiveVersionFlow(FlowNodeTypeEnum type, Long refId, Integer refVersion);

    void checkVersionFlowNodeActive(List<VersionFlowNode> nodes);
}
