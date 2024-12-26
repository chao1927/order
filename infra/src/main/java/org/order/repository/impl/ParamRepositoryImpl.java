package org.order.repository.impl;


import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Param;
import org.order.domain.repository.ParamRepository;
import org.order.repository.dao.ParamDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 变量仓储接口实现
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Component
public class ParamRepositoryImpl extends BaseRepositoryImpl<Param, Long> implements ParamRepository {


    @Autowired
    private ParamDao paramDao;

    @Override
    public Optional<Param> findByNameAndIdNot(String name, Long id) {
        return paramDao.findByNameAndIdNot(name, id);
    }

    @Override
    public Optional<Param> findByName(String name) {
        return paramDao.findByName(name);
    }


    /******************************** Param ************************************************/

    /**
     * 变量存在则返回变量，否则抛出异常
     * @param id 变量id
     * @return 变量
     */
    @Override
    public Param findByIdWithEx(Long id) {
        return findById(id).orElseThrow(() -> new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND));
    }

    /**
     * 创建时，是否存在同名变量，如果有，抛出异常
     * @param name 变量名称
     */
    @Override
    public void checkDuplicateName(String name) {
        if (findByName(name).isEmpty()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NAME_DUPLICATION);
        }
    }

    /**
     * 修改时，是否存在同名变量，如果有，抛出异常
     * @param name 变量名称
     * @param id 变量id
     */
    @Override
    public void checkDuplicateName(String name, Long id) {
        if (findByNameAndIdNot(name, id).isEmpty()) {
            throw new CustomBusinessException(ErrorCode.PARAM_NAME_DUPLICATION);
        }
    }

    @Override
    public void checkParamExist(Long id) {
        findById(id).orElseThrow(() -> new CustomBusinessException(ErrorCode.PARAM_NOT_FOUND));
    }

}
