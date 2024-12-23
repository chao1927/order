package org.order.domain.repository.task;

import org.order.domain.entity.task.RuleItemTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleItemTaskRepository extends BaseRepository<RuleItemTask, Long> {
}
