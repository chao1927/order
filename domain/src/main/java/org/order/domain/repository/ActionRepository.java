package org.order.domain.repository;

import org.order.domain.entity.Action;

/**
 * action 仓储接口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface ActionRepository extends BaseRepository<Action, Long>, NameRepository<Action> {

    Action findByIdWithEx(Long actionId);

    void checkActionExist(Long actionId);

    /**
     * 判断是否有指定名称的 Action
     *
     * @param name action 名称
     */
    void checkDuplicateName(String name);

    /**
     * 修改时，判断是否有除自身外的相同名称的 Action
     *
     * @param name action 名称
     * @param actionId action id
     */
    void checkDuplicateName(String name, Long actionId);

}
