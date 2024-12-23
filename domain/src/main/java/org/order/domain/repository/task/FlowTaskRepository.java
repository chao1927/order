package org.order.domain.repository.task;

import org.order.domain.entity.task.FlowTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowTaskRepository extends BaseRepository<FlowTask, Long> {
}
