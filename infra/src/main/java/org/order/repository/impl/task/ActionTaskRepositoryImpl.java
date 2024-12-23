package org.order.repository.impl.task;

import org.order.domain.entity.task.ActionTask;
import org.order.domain.repository.task.ActionTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class ActionTaskRepositoryImpl extends BaseRepositoryImpl<ActionTask, Long> implements ActionTaskRepository {
}
