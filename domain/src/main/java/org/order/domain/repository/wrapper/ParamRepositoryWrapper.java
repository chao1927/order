package org.order.domain.repository.wrapper;

import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Param;
import org.order.domain.entity.version.VersionParam;
import org.order.domain.repository.ParamRepository;
import org.order.domain.repository.version.VersionParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 变量仓储包装类
 *
 * @author chaobo
 * @date 2024/11/19
 */
@Component
public class ParamRepositoryWrapper {


    @Autowired
    private ParamRepository paramRepository;

    @Autowired
    private VersionParamRepository versionParamRepository;


    /******************************** Param ************************************************/

    /**
     * 变量存在则返回变量，否则抛出异常
     * @param id 变量id
     * @return 变量
     */
    public Param findByIdWithEx(Long id) {
        return paramRepository.findById(id).orElseThrow(() -> new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND));
    }

    /**
     * 创建时，是否存在同名变量，如果有，抛出异常
     * @param name 变量名称
     */
    public void checkDuplicateName(String name) {
        if (!paramRepository.findByName(name).isPresent()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NAME_DUPLICATION);
        }
    }

    /**
     * 修改时，是否存在同名变量，如果有，抛出异常
     * @param name 变量名称
     * @param id 变量id
     */
    public void checkDuplicateName(String name, Long id) {
        if (!paramRepository.findByNameAndIdNot(name, id).isPresent()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NAME_DUPLICATION);
        }
    }


    public void checkParamExist(Long id) {
        paramRepository.findById(id).orElseThrow(() -> new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND));
    }


    /******************************** Version Param ************************************************/
    /**
     * 删除版本变量, 版本变量是否存在
     * @param paramId 变量id
     * @param version 版本
     * @return 版本变量
     */
    public VersionParam findByParamIdAndVersionWithEx(Long paramId, Integer version) {
        return versionParamRepository.findByParamIdAndVersion(paramId, version).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND)
        );
    }

    public void checkVersionParamActive(Long paramId, Integer version) {
        Optional<VersionParam> versionParamOp = versionParamRepository.findByParamIdAndVersion(paramId, version);
        if (!versionParamOp.isPresent()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND);
        }

        if (!versionParamOp.get().isActive()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NOT_ACTIVE);
        }
    }

}
