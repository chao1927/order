package org.order.repository.impl.version;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.VersionFlowLine;
import org.order.domain.repository.version.VersionFlowLineRepository;
import org.order.repository.dao.version.VersionFlowLineDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程边仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionFlowLineRepositoryImpl extends BaseRepositoryImpl<VersionFlowLine, Long> implements VersionFlowLineRepository {

    @Autowired
    private VersionFlowLineDao versionFlowLineDao;

    @Override
    public void deleteByVersionFlowId(Long versionFlowId) {
        versionFlowLineDao.deleteByVersionFlowId(versionFlowId);
    }

    @Override
    public Optional<List<VersionFlowLine>> findByVersionFlowId(Long versionFlowId) {
        return versionFlowLineDao.findByVersionFlowId(versionFlowId);
    }

    @Override
    public List<VersionFlowLine> findVersionFlowLineByFlowIdWithEx(Long versionFlowId) {
        Optional<List<VersionFlowLine>> linesOp = findByVersionFlowId(versionFlowId);
        if (linesOp.isEmpty() || CollectionUtils.isEmpty(linesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_LINE_IS_EMPTY);
        }
        return linesOp.get();
    }

}
