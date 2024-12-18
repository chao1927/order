package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionFlowNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程节点
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface VersionFlowNodeDao extends JpaRepository<VersionFlowNode, Long> {

    void deleteByVersionFlowId(Long versionFlowId);

    Optional<List<VersionFlowNode>> findByTypeAndRefId(Integer code, Long refId);

    Optional<List<VersionFlowNode>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion);

    Optional<List<VersionFlowNode>> findByVersionFlowId(Long versionFlowId);

}
