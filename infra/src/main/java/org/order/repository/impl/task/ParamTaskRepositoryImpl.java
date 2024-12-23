package org.order.repository.impl.task;

import org.order.domain.entity.task.ParamTask;
import org.order.domain.repository.task.ParamTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class ParamTaskRepositoryImpl extends BaseRepositoryImpl<ParamTask, Long> implements ParamTaskRepository {
}
