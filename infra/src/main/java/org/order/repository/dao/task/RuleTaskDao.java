package org.order.repository.dao.task;

import org.order.domain.entity.task.RuleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleTaskDao extends JpaRepository<RuleTask, Long> {
}