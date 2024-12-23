package org.order.repository.impl.task;

import org.order.domain.entity.task.FlowLineTask;
import org.order.domain.repository.task.FlowLineTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class FlowLineTaskRepositoryImpl extends BaseRepositoryImpl<FlowLineTask, Long> implements FlowLineTaskRepository {
}
