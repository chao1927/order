package org.order.repository.impl.task;

import org.order.domain.entity.task.RuleItemTask;
import org.order.domain.repository.task.RuleItemTaskRepository;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.stereotype.Component;


@Component
public class RuleItemTaskRepositoryImpl extends BaseRepositoryImpl<RuleItemTask, Long> implements RuleItemTaskRepository {
}
