package org.order.repository.dao.task;

import org.order.domain.entity.task.FlowTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowTaskDao extends JpaRepository<FlowTask, Long> {
}
