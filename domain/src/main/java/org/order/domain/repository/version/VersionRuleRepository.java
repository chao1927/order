package org.order.domain.repository.version;

import org.order.domain.entity.version.VersionRule;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 版本规则仓储
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface VersionRuleRepository extends BaseRepository<VersionRule, Long> {
    Optional<VersionRule> findByRuleIdAndVersion(Long ruleId, Integer version);

    Optional<List<VersionRule>> findByRuleId(Long ruleId);

    void checkVersionRuleActive(Long ruleId, Integer version);

    VersionRule findByRuleIdAndVersionWithEx(Long ruleId, Integer version);
}
