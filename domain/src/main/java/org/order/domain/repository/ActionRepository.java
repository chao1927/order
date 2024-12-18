package org.order.domain.repository;

import org.order.domain.entity.Action;

/**
 * action 仓储接口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface ActionRepository extends BaseRepository<Action, Long>, NameRepository<Action> {

}
