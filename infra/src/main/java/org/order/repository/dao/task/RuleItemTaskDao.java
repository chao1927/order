package org.order.repository.dao.task;

import org.order.domain.entity.task.RuleItemTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleItemTaskDao extends JpaRepository<RuleItemTask, Long> {
}
