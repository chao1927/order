package org.order.repository.impl;

import org.order.domain.entity.RuleItem;
import org.order.domain.repository.RuleItemRepository;
import org.order.repository.dao.RuleItemDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 规则子项仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class RuleItemRepositoryImpl extends BaseRepositoryImpl<RuleItem, Long> implements RuleItemRepository {

    @Autowired
    private RuleItemDao ruleItemDao;

    @Override
    public Optional<List<RuleItem>> findByTypeAndRefId(Integer type, Long refId) {
        return ruleItemDao.findByTypeAndRefId(type, refId);
    }

    @Override
    public Optional<List<RuleItem>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion) {
        return ruleItemDao.findByTypeAndRefIdAndRefVersion(type, refId, refVersion);
    }

    @Override
    public Optional<List<RuleItem>> findByRuleId(Long ruleId) {
        return ruleItemDao.findByRuleId(ruleId);
    }

    @Override
    public void deleteByRuleId(Long ruleId) {
        ruleItemDao.deleteByRuleId(ruleId);
    }

}
