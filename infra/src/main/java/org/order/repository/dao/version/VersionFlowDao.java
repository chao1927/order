package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface VersionFlowDao extends JpaRepository<VersionFlow, Long> {

    Optional<VersionFlow> findByFlowIdAndVersion(Long flowId, Integer version);

    Optional<List<VersionFlow>> findByFlowId(Long flowId);

}
