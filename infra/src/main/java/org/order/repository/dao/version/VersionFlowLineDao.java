package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionFlowLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程边
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface VersionFlowLineDao extends JpaRepository<VersionFlowLine, Long> {

    void deleteByVersionFlowId(Long versionFlowId);

    Optional<List<VersionFlowLine>> findByVersionFlowId(Long versionFlowId);
}
