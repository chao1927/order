package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * VersionEntry 数据访问对象
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface VersionEntryDao extends JpaRepository<VersionEntry, Long> {

    Optional<VersionEntry> findByEntryIdAndVersion(Long entryId, Integer version);

    void deleteByEntryId(Long entryId);

    Optional<List<VersionEntry>> findByFlowId(Long flowId);

    Optional<List<VersionEntry>> findByFlowIdAndFlowVersion(Long flowId, Integer flowVersion);

}
