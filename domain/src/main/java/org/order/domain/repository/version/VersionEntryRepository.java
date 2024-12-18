package org.order.domain.repository.version;

import org.order.domain.entity.version.VersionEntry;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 版本入口仓储
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface VersionEntryRepository extends BaseRepository<VersionEntry, Long> {
    Optional<VersionEntry> findByEntryIdAndVersion(Long entryId, Integer version);

    void deleteByEntryId(Long entryId);

    Optional<List<VersionEntry>> findByFlowId(Long flowId);

    Optional<List<VersionEntry>> findByFlowIdAndFlowVersion(Long flowId, Integer flowVersion);

    Optional<List<VersionEntry>> findByFlowIdAndFlowVersionAndStatus(Long flowId, Integer flowVersion, Integer status);
}
