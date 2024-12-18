package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 版本 action dao
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface VersionActionDao extends JpaRepository<VersionAction, Long> {

    Optional<VersionAction> findByActionIdAndVersion(Long actionId, Integer version);

    void deleteByActionId(Long actionId);
}
