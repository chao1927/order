package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 版本规则
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface VersionRuleDao extends JpaRepository<VersionRule, Long> {

    Optional<VersionRule> findByRuleIdAndVersion(Long ruleId, Integer version);

    Optional<List<VersionRule>> findByRuleId(Long ruleId);

}
