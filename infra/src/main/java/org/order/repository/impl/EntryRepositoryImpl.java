package org.order.repository.impl;

import org.order.domain.entity.Entry;
import org.order.domain.repository.EntryRepository;
import org.order.repository.dao.EntryDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 入口仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class EntryRepositoryImpl extends BaseRepositoryImpl<Entry, Long> implements EntryRepository {

    @Autowired
    private EntryDao entryDao;

    @Override
    public Optional<List<Entry>> findByFlowId(Long flowId) {
        return entryDao.findByFlowId(flowId);
    }

    @Override
    public Optional<List<Entry>> findByFlowIdAndFlowVersion(Long flowId, Integer flowVersion) {
        return entryDao.findByFlowIdAndFlowVersion(flowId, flowVersion);
    }

    @Override
    public Optional<Entry> findByNameAndIdNot(String name, Long id) {
        return entryDao.findByNameAndIdNot(name, id);
    }

    @Override
    public Optional<Entry> findByName(String name) {
        return entryDao.findByName(name);
    }
}
