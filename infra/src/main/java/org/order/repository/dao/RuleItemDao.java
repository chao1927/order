package org.order.repository.dao;

import org.order.domain.entity.RuleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 规则子项dao
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface RuleItemDao extends JpaRepository<RuleItem, Long> {

    Optional<List<RuleItem>> findByTypeAndRefId(Integer type, Long refId);

    Optional<List<RuleItem>> findByTypeAndRefIdAndRefVersion(Integer code, Long refId, Integer version);

    Optional<List<RuleItem>> findByRuleId(Long ruleId);

    void deleteByRuleId(Long ruleId);
}
