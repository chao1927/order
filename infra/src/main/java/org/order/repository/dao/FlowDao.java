package org.order.repository.dao;

import org.order.domain.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Flow 数据访问对象
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface FlowDao extends JpaRepository<Flow, Long>, NameDao<Flow> {
}
