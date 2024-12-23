package org.order.repository.dao.task;

import org.order.domain.entity.task.EntryTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTaskDao extends JpaRepository<EntryTask, Long> {
}
