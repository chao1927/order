package org.order.repository.impl.version;

import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.VersionAction;
import org.order.domain.repository.version.VersionActionRepository;
import org.order.repository.dao.version.VersionActionDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 版本 action 仓储实现
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

    /**
     * 根据 actionId，版本号查询 action, 存在则返回， 否则抛出异常
     *
     * @param actionId actionId
     * @param version 版本号
     * @return 版本 Action
     */
    @Override
    public VersionAction findByActionIdAndVersionWithEx(Long actionId, Integer version) {
        return findByActionIdAndVersion(actionId, version).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND));
    }

    @Override
    public void checkVersionActionActive(Long actionId, Integer version) {
        Optional<VersionAction> versionActionOp = findByActionIdAndVersion(actionId, version);
        if (versionActionOp.isEmpty()) {
            throw new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND);
        }

        if (!versionActionOp.get().isActive()) {
            throw new CustomBusinessException(ErrorCode.ACTION_NOT_ACTIVE);
        }
    }
}
