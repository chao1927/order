package org.order.repository.impl.version;

import org.order.domain.entity.version.VersionFlowNode;
import org.order.domain.repository.version.VersionFlowNodeRepository;
import org.order.repository.dao.version.VersionFlowNodeDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 版本流程节点仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionFlowNodeRepositoryImpl extends BaseRepositoryImpl<VersionFlowNode, Long> implements VersionFlowNodeRepository {

    @Autowired
    private VersionFlowNodeDao versionFlowNodeDao;


    @Override
    public void deleteByVersionFlowId(Long versionFlowId) {
        versionFlowNodeDao.deleteByVersionFlowId(versionFlowId);
    }

    @Override
    public Optional<List<VersionFlowNode>> findByTypeAndRefId(Integer code, Long refId) {
        return versionFlowNodeDao.findByTypeAndRefId(code, refId);
    }

    @Override
    public Optional<List<VersionFlowNode>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion) {
        return versionFlowNodeDao.findByTypeAndRefIdAndRefVersion(type, refId, refVersion);
    }

    @Override
    public Optional<List<VersionFlowNode>> findByTypeAndRefIdAndRefVersionAndStatus(Integer type, Long refId, Integer refVersion, Integer status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<VersionFlowNode>> findByVersionFlowId(Long versionFlowId) {
        return versionFlowNodeDao.findByVersionFlowId(versionFlowId);
    }
}
