package org.order.repository.impl.version;

import org.order.common.enums.ErrorCode;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.VersionFlow;
import org.order.domain.repository.version.VersionFlowRepository;
import org.order.repository.dao.version.VersionFlowDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionFlowRepositoryImpl extends BaseRepositoryImpl<VersionFlow, Long> implements VersionFlowRepository {


    @Autowired
    private VersionFlowDao versionFlowDao;

    @Override
    public Optional<VersionFlow> findByFlowIdAndVersion(Long flowId, Integer version) {
        return versionFlowDao.findByFlowIdAndVersion(flowId, version);
    }

    @Override
    public Optional<List<VersionFlow>> findByFlowId(Long flowId) {
        return versionFlowDao.findByFlowId(flowId);
    }

    @Override
    public VersionFlow findByFlowIdAndVersionWithEx(Long flowId, Integer version) {
        return findByFlowIdAndVersion(flowId, version).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND)
        );
    }

    @Override
    public void checkFlowActive(Long flowId, Integer version) {
        findByFlowIdAndVersion(flowId, version).ifPresent(versionFlow -> {
            if (!StatusEnum.isActive(versionFlow.getStatus())) {
                throw new CustomBusinessException(ErrorCode.FLOW_NOT_ACTIVE);
            }
        });
    }
}
