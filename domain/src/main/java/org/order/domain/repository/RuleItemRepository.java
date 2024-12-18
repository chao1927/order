package org.order.domain.repository;

import org.order.domain.entity.RuleItem;

import java.util.List;
import java.util.Optional;

/**
 * 规则子项仓储接口
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface RuleItemRepository extends BaseRepository<RuleItem, Long> {


    Optional<List<RuleItem>> findByTypeAndRefId(Integer type, Long refId);

    Optional<List<RuleItem>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion);

    Optional<List<RuleItem>> findByRuleId(Long ruleId);

    void deleteByRuleId(Long ruleId);
}
