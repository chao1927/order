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

    Entry findByIdWithEx(Long id);

    void checkDuplicateName(String name);

    void checkDuplicateName(String name, Long id);

    void checkEntryExist(Long id);

    void checkRefByEntry(Long flowId);

    void checkRefByEntry(Long flowId, Integer flowVersion);
}
