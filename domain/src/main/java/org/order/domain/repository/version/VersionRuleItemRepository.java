package org.order.domain.repository.version;

import org.order.domain.entity.version.VersionRuleItem;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 版本规则子项
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface VersionRuleItemRepository extends BaseRepository<VersionRuleItem, Long> {

    Optional<List<VersionRuleItem>> findByTypeAndRefId(Integer type, Long refId);

    Optional<List<VersionRuleItem>> findByTypeAndRefIdAndRefVersion(Integer code, Long refId, Integer refVersion);

    Optional<List<VersionRuleItem>> findByTypeAndRefIdAndRefVersionAndStatus(Integer type, Long refId, Integer refVersion, Integer status);

    Optional<List<VersionRuleItem>> findByVersionRuleId(Long versionRuleId);

    void deleteByVersionRuleId(Long versionRuleId);
}
