package org.order.domain.repository.task;

import org.order.domain.entity.task.ActionTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionTaskRepository extends BaseRepository<ActionTask, Long> {
}
