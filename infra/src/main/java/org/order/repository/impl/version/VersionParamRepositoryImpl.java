package org.order.repository.impl.version;

import org.order.common.enums.ErrorCode;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.VersionParam;
import org.order.domain.repository.version.VersionParamRepository;
import org.order.repository.dao.version.VersionParamDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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


    @Override
    public void checkVersionParamActive(Long paramId, Integer version) {
        Optional<VersionParam> versionParamOp = findByParamIdAndVersion(paramId, version);
        if (versionParamOp.isEmpty()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND);
        }

        if (!versionParamOp.get().isActive()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NOT_ACTIVE);
        }
    }

    /**
     * 删除版本变量, 版本变量是否存在
     * @param paramId 变量id
     * @param version 版本
     * @return 版本变量
     */
    @Override
    public VersionParam findByParamIdAndVersionWithEx(Long paramId, Integer version) {
        return findByParamIdAndVersion(paramId, version).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND)
        );
    }

    @Override
    public Optional<List<VersionParam>> findActive() {
        return versionParamDao.findByStatus(StatusEnum.ACTIVATED.getCode());
    }

}
