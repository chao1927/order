package org.order.repository.dao;

import org.order.domain.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Action 数据访问对象
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Repository
public interface ActionDao extends JpaRepository<Action, Long>, NameDao<Action> {
}
