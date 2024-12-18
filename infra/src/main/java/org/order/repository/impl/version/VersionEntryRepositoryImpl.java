package org.order.repository.impl.version;

import org.order.domain.entity.version.VersionEntry;
import org.order.domain.repository.version.VersionEntryRepository;
import org.order.repository.dao.version.VersionEntryDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 版本入口仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionEntryRepositoryImpl extends BaseRepositoryImpl<VersionEntry, Long> implements VersionEntryRepository {

    @Autowired
    private VersionEntryDao versionEntryDao;


    @Override
    public Optional<VersionEntry> findByEntryIdAndVersion(Long entryId, Integer version) {
        return versionEntryDao.findByEntryIdAndVersion(entryId, version);
    }

    @Override
    public void deleteByEntryId(Long entryId) {
        versionEntryDao.deleteByEntryId(entryId);
    }

    @Override
    public Optional<List<VersionEntry>> findByFlowId(Long flowId) {
        return versionEntryDao.findByFlowId(flowId);
    }

    @Override
    public Optional<List<VersionEntry>> findByFlowIdAndFlowVersion(Long flowId, Integer flowVersion) {
        return versionEntryDao.findByFlowIdAndFlowVersion(flowId, flowVersion);
    }

    @Override
    public Optional<List<VersionEntry>> findByFlowIdAndFlowVersionAndStatus(Long flowId, Integer flowVersion, Integer status) {
        return Optional.empty();
    }
}
