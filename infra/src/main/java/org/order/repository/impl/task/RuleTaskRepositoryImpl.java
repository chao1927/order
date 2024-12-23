package org.order.repository.impl.task;

import org.order.domain.entity.task.RuleTask;
import org.order.domain.repository.task.RuleTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;


@Component
public class RuleTaskRepositoryImpl extends BaseRepositoryImpl<RuleTask, Long> implements RuleTaskRepository {
}
