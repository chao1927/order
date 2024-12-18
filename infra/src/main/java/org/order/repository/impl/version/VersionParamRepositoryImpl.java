package org.order.repository.impl.version;

import org.order.domain.entity.version.VersionParam;
import org.order.domain.repository.version.VersionParamRepository;
import org.order.repository.dao.version.VersionParamDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 版本变量仓储实现
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Component
public class VersionParamRepositoryImpl extends BaseRepositoryImpl<VersionParam, Long> implements VersionParamRepository {

    @Autowired
    private VersionParamDao versionParamDao;


    @Override
    public Optional<VersionParam> findByParamIdAndVersion(Long paramId, Integer version) {
        return versionParamDao.findByParamIdAndVersion(paramId, version);
    }

    @Override
    public void deleteByParamId(Long paramId) {
        versionParamDao.deleteByParamId(paramId);
    }


}
