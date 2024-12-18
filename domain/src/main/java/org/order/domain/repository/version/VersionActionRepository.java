package org.order.domain.repository.version;

import org.order.domain.entity.version.VersionAction;
import org.order.domain.repository.BaseRepository;

import java.util.Optional;

/**
 * 版本 action 仓储接口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface VersionActionRepository extends BaseRepository<VersionAction, Long> {
    Optional<VersionAction> findByActionIdAndVersion(Long actionId, Integer version);

    void deleteByActionId(Long actionId);
}
