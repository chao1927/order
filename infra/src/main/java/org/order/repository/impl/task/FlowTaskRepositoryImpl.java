package org.order.repository.impl.task;

import org.order.domain.entity.task.FlowTask;
import org.order.domain.repository.task.FlowTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class FlowTaskRepositoryImpl extends BaseRepositoryImpl<FlowTask, Long> implements FlowTaskRepository {
}
