package org.order.domain.repository.task;

import org.order.domain.entity.task.ParamTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamTaskRepository extends BaseRepository<ParamTask, Long> {
}
