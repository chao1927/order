package org.order.repository.impl;


import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
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

    /**
     * 规则存在则返回规则，否则抛出异常
     * @param ruleId 规则id
     * @return 变量
     */
    @Override
    public Rule findByIdWithEx(Long ruleId) {
        return findById(ruleId).orElseThrow(() -> new CustomBusinessException(ErrorCode.RULE_NOT_FOUND));
    }

    /**
     * 创建时，是否存在同名规则，如果有，抛出异常
     * @param name 规则名称
     */
    @Override
    public void checkDuplicateName(String name) {
        if (findByName(name).isEmpty()) {
            throw new CustomBusinessException(ErrorCode.RULE_NAME_DUPLICATION);
        }
    }

    /**
     * 修改时，是否存在同名规则，如果有，抛出异常
     * @param name 灌醉名称
     * @param ruleId 规则id
     */
    @Override
    public void checkDuplicateName(String name, Long ruleId) {
        if (findByNameAndIdNot(name, ruleId).isEmpty()) {
            throw new CustomBusinessException(ErrorCode.RULE_NAME_DUPLICATION);
        }
    }

    @Override
    public void checkRuleExist(Long ruleId) {
        findById(ruleId).orElseThrow(() -> new CustomBusinessException(ErrorCode.RULE_NOT_FOUND));
    }


}
