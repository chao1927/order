package org.order.repository.impl.version;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.version.VersionRuleItem;
import org.order.domain.repository.version.VersionParamRepository;
import org.order.domain.repository.version.VersionRuleItemRepository;
import org.order.domain.repository.version.VersionRuleRepository;
import org.order.repository.dao.version.VersionRuleItemDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 版本规则子项仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class VersionRuleItemRepositoryImpl extends BaseRepositoryImpl<VersionRuleItem, Long> implements VersionRuleItemRepository {

    @Autowired
    private VersionRuleItemDao versionRuleItemDao;

    @Autowired
    private VersionRuleRepository versionRuleRepository;

    @Autowired
    private VersionParamRepository versionParamRepository;


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

    @Override
    public void checkRefByVersionRule(RuleItemTypeEnum type, Long refId) {
        checkResult(findByTypeAndRefId(type.getCode(), refId), type);
    }

    @Override
    public void checkRefByVersionRule(RuleItemTypeEnum type, Long refId, Integer refVersion) {
        checkResult(findByTypeAndRefIdAndRefVersion(RuleItemTypeEnum.PARAM.getCode(), refId, refVersion), type);
    }

    @Override
    public void checkRefByActiveVersionRule(RuleItemTypeEnum type, Long refId, Integer refVersion) {
        checkResult(findByTypeAndRefIdAndRefVersionAndStatus(type.getCode(), refId, refVersion, StatusEnum.ACTIVATED.getCode()), type);
    }

    @Override
    public List<VersionRuleItem> findRuleItemsByVersionRuleIdWithEx(Long versionRuleId) {
        Optional<List<VersionRuleItem>> ruleItemsOp = findByVersionRuleId(versionRuleId);
        if (ruleItemsOp.isEmpty() || CollectionUtils.isEmpty(ruleItemsOp.get())) {
            throw new CustomBusinessException(ErrorCode.RULE_ITEM_IS_EMPTY);
        }
        return ruleItemsOp.get();
    }

    @Override
    public void checkVersionRuleItemActive(List<VersionRuleItem> items) {
        items.forEach(item -> {
            RuleItemTypeEnum type = RuleItemTypeEnum.getByCode(item.getType());
            if (type == null) {
                throw new CustomBusinessException(ErrorCode.RULE_ITEM_TYPE_ERROR);
            }

            switch (type) {
                case PARAM:
                    versionParamRepository.checkVersionParamActive(item.getRefId(), item.getRefVersion());
                    break;
                case RULE:
                    versionRuleRepository.checkVersionRuleActive(item.getRefId(), item.getRefVersion());
                    break;
            }
        });
    }

    private <T> void checkResult(Optional<List<T>> itemsOp, RuleItemTypeEnum type) {
        if (itemsOp.isPresent() && CollectionUtils.isNotEmpty(itemsOp.get())) {
            switch (type) {
                case PARAM:
                    throw new CustomBusinessException(ErrorCode.PARAM_REFERENCED_BY_RULE);
                case RULE:
                    throw new CustomBusinessException(ErrorCode.RULE_REFERENCED_BY_RULE);
                default:
            }
        }
    }
}
