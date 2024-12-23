package org.order.domain.repository.task;

import org.order.domain.entity.task.RuleTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleTaskRepository extends BaseRepository<RuleTask, Long> {
}
