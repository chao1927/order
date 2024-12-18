package org.order.repository.impl.version;

import org.order.domain.entity.version.VersionRule;
import org.order.domain.repository.version.VersionRuleRepository;
import org.order.repository.dao.version.VersionRuleDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 版本规则仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionRuleRepositoryImpl extends BaseRepositoryImpl<VersionRule, Long> implements VersionRuleRepository {


    @Autowired
    private VersionRuleDao versionRuleDao;

    @Override
    public Optional<VersionRule> findByRuleIdAndVersion(Long ruleId, Integer version) {
        return versionRuleDao.findByRuleIdAndVersion(ruleId, version);
    }

    @Override
    public Optional<List<VersionRule>> findByRuleId(Long ruleId) {
        return versionRuleDao.findByRuleId(ruleId);
    }
}