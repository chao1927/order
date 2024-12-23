package org.order.repository.dao.task;

import org.order.domain.entity.task.ParamTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamTaskDao extends JpaRepository<ParamTask, Long> {
}
