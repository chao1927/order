package org.order.domain.repository.version;

import org.order.domain.entity.version.VersionFlow;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程仓储接口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface VersionFlowRepository extends BaseRepository<VersionFlow, Long> {

    Optional<VersionFlow> findByFlowIdAndVersion(Long flowId, Integer version);

    Optional<List<VersionFlow>> findByFlowId(Long flowId);

    VersionFlow findByFlowIdAndVersionWithEx(Long flowId, Integer version);

    void checkFlowActive(Long flowId, Integer version);
}
