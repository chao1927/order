package org.order.repository.impl;


import org.order.domain.entity.Rule;
import org.order.domain.repository.RuleRepository;
import org.order.repository.dao.RuleDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 规则仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class RuleRepositoryImpl extends BaseRepositoryImpl<Rule, Long> implements RuleRepository {


    @Autowired
    private RuleDao ruleDao;

    @Override
    public Optional<Rule> findByNameAndIdNot(String name, Long id) {
        return ruleDao.findByNameAndIdNot(name, id);
    }

    @Override
    public Optional<Rule> findByName(String name) {
        return ruleDao.findByName(name);
    }


}
