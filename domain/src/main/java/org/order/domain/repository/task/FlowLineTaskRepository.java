package org.order.domain.repository.task;

import org.order.domain.entity.task.FlowLineTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowLineTaskRepository extends BaseRepository<FlowLineTask, Long> {
}
