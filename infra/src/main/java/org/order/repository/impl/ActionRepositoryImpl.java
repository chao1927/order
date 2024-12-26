package org.order.repository.impl;

import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
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

    /**
     * action 存在则返回 action, 否则抛出异常
     *
     * @param actionId actionId
     * @return action
     */
    @Override
    public Action findByIdWithEx(Long actionId) {
        return findById(actionId).orElseThrow(() -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND));
    }

    @Override
    public void checkDuplicateName(String name) {
        findByName(name).ifPresent(action -> {
            throw new CustomBusinessException(ErrorCode.ACTION_NAME_DUPLICATE);
        });
    }

    /**
     * 修改时，判断是否有除自身外的相同名称的 Action
     *
     * @param name action 名称
     * @param actionId action id
     */
    @Override
    public void checkDuplicateName(String name, Long actionId) {
        findByNameAndIdNot(name, actionId).ifPresent(action -> {
            throw new CustomBusinessException(ErrorCode.ACTION_NAME_DUPLICATE);
        });
    }

    @Override
    public void checkActionExist(Long actionId) {
        findById(actionId).orElseThrow(() -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND));
    }

}
