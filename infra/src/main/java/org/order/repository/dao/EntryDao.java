package org.order.repository.dao;

import org.order.domain.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Entry 数据访问对象
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface EntryDao extends JpaRepository<Entry, Long>, NameDao<Entry> {

    Optional<List<Entry>> findByFlowId(Long flowId);

    Optional<List<Entry>> findByFlowIdAndFlowVersion(Long flowId, Integer flowVersion);
}
