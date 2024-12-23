package org.order.repository.impl.task;

import org.order.domain.entity.task.FlowNodeTask;
import org.order.domain.repository.task.FlowNodeTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class FlowNodeTaskRepositoryImpl extends BaseRepositoryImpl<FlowNodeTask, Long> implements FlowNodeTaskRepository {
}
