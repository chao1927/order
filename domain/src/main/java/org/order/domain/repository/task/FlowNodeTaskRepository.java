package org.order.domain.repository.task;

import org.order.domain.entity.task.FlowNodeTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowNodeTaskRepository extends BaseRepository<FlowNodeTask, Long> {
}
