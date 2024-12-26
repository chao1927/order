package org.order.domain.repository;

import org.order.domain.entity.Rule;


/**
 * 规则仓储接口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface RuleRepository extends BaseRepository<Rule, Long>, NameRepository<Rule> {

    Rule findByIdWithEx(Long ruleId);

    void checkDuplicateName(String name);

    void checkDuplicateName(String name, Long ruleId);

    void checkRuleExist(Long ruleId);
}
