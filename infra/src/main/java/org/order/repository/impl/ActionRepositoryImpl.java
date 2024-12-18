package org.order.repository.impl;

import org.order.domain.entity.Action;
import org.order.domain.repository.ActionRepository;
import org.order.repository.dao.ActionDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * action 仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class ActionRepositoryImpl extends BaseRepositoryImpl<Action, Long> implements ActionRepository {

    @Autowired
    private ActionDao actionDao;

    @Override
    public Optional<Action> findByNameAndIdNot(String name, Long id) {
        return actionDao.findByNameAndIdNot(name, id);
    }

    @Override
    public Optional<Action> findByName(String name) {
        return actionDao.findByName(name);
    }

}
