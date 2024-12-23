package org.order.repository.dao.task;

import org.order.domain.entity.task.ActionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionTaskDao extends JpaRepository<ActionTask, Long> {
}
