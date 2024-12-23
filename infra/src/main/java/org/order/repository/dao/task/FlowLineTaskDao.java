package org.order.repository.dao.task;

import org.order.domain.entity.task.FlowLineTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowLineTaskDao extends JpaRepository<FlowLineTask, Long> {
}
