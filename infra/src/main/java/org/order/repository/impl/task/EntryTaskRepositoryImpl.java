package org.order.repository.impl.task;

import org.order.domain.entity.task.EntryTask;
import org.order.domain.repository.task.EntryTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class EntryTaskRepositoryImpl extends BaseRepositoryImpl<EntryTask, Long> implements EntryTaskRepository {
}
