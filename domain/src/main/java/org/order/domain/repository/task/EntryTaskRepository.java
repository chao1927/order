package org.order.domain.repository.task;

import org.order.domain.entity.task.EntryTask;
import org.order.domain.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTaskRepository extends BaseRepository<EntryTask, Long> {
}
