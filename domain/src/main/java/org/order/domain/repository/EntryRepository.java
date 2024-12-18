package org.order.domain.repository;

import org.order.domain.entity.Entry;

import java.util.List;
import java.util.Optional;

/**
 * 入口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface EntryRepository extends BaseRepository<Entry, Long>, NameRepository<Entry> {

    Optional<List<Entry>> findByFlowId(Long flowId);

    Optional<List<Entry>> findByFlowIdAndFlowVersion(Long flowId, Integer flowVersion);
}
