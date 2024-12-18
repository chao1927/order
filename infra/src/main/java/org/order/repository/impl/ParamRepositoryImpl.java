package org.order.repository.impl;


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



}
