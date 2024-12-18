package org.order.repository.impl.version;

import org.order.domain.entity.version.VersionAction;
import org.order.domain.repository.version.VersionActionRepository;
import org.order.repository.dao.version.VersionActionDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionActionRepositoryImpl extends BaseRepositoryImpl<VersionAction, Long> implements VersionActionRepository {

    @Autowired
    private VersionActionDao versionActionDao;

    @Override
    public Optional<VersionAction> findByActionIdAndVersion(Long actionId, Integer version) {
        return versionActionDao.findByActionIdAndVersion(actionId, version);
    }

    @Override
    public void deleteByActionId(Long actionId) {
        versionActionDao.deleteByActionId(actionId);
    }
}
