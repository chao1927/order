package org.order.repository.impl.version;

import org.order.domain.entity.version.VersionRuleItem;
import org.order.domain.repository.version.VersionRuleItemRepository;
import org.order.repository.dao.version.VersionRuleItemDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionRuleItemRepositoryImpl extends BaseRepositoryImpl<VersionRuleItem, Long> implements VersionRuleItemRepository {

    @Autowired
    private VersionRuleItemDao versionRuleItemDao;


    @Override
    public Optional<List<VersionRuleItem>> findByTypeAndRefId(Integer type, Long refId) {
        return versionRuleItemDao.findByTypeAndRefId(type, refId);
    }

    @Override
    public Optional<List<VersionRuleItem>> findByTypeAndRefIdAndRefVersion(Integer type, Long paramId, Integer version) {
        return versionRuleItemDao.findByTypeAndRefIdAndRefVersion(type, paramId, version);
    }

    @Override
    public Optional<List<VersionRuleItem>> findByTypeAndRefIdAndRefVersionAndStatus(Integer type, Long paramId, Integer version, Integer status) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<List<VersionRuleItem>> findByVersionRuleId(Long versionRuleId) {
        return versionRuleItemDao.findByVersionRuleId(versionRuleId);
    }

    @Override
    public void deleteByVersionRuleId(Long versionRuleId) {
        versionRuleItemDao.deleteByVersionRuleId(versionRuleId);
    }
}
