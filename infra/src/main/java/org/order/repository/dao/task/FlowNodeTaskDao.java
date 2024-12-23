package org.order.repository.dao.task;

import org.order.domain.entity.task.FlowNodeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowNodeTaskDao extends JpaRepository<FlowNodeTask, Long> {
}
