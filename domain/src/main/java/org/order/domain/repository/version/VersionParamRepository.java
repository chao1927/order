package org.order.domain.repository.version;

import org.order.domain.entity.version.VersionParam;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 版本变量仓储接口
 *
 * @author chaobo
 * @date 2024/11/13
 */
public interface VersionParamRepository extends BaseRepository<VersionParam, Long> {

    Optional<VersionParam> findByParamIdAndVersion(Long paramId, Integer version);

    void deleteByParamId(Long paramId);

    void checkVersionParamActive(Long paramId, Integer version);

    VersionParam findByParamIdAndVersionWithEx(Long paramId, Integer version);

    Optional<List<VersionParam>> findActive();
}
