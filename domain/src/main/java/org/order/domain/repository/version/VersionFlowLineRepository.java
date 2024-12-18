package org.order.domain.repository.version;

import org.order.domain.entity.version.VersionFlowLine;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程边
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface VersionFlowLineRepository extends BaseRepository<VersionFlowLine, Long> {
    void deleteByVersionFlowId(Long versionFlowId);

    Optional<List<VersionFlowLine>> findByVersionFlowId(Long versionFlowId);
}
