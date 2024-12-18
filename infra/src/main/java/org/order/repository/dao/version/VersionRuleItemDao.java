package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionRuleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 版本规则子项
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface VersionRuleItemDao extends JpaRepository<VersionRuleItem, Long> {

    Optional<List<VersionRuleItem>> findByTypeAndRefId(Integer type, Long refId);

    Optional<List<VersionRuleItem>> findByTypeAndRefIdAndRefVersion(Integer type, Long paramId, Integer version);

    Optional<List<VersionRuleItem>> findByVersionRuleId(Long versionRuleId);

    void deleteByVersionRuleId(Long versionRuleId);

}
