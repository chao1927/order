package org.order.repository.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.RuleItem;
import org.order.domain.repository.RuleItemRepository;
import org.order.domain.repository.version.VersionParamRepository;
import org.order.domain.repository.version.VersionRuleRepository;
import org.order.repository.dao.RuleItemDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 规则子项仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class RuleItemRepositoryImpl extends BaseRepositoryImpl<RuleItem, Long> implements RuleItemRepository {

    @Autowired
    private RuleItemDao ruleItemDao;

    @Autowired
    private VersionRuleRepository versionRuleRepository;

    @Autowired
    private VersionParamRepository versionParamRepository;

    @Override
    public Optional<List<RuleItem>> findByTypeAndRefId(Integer type, Long refId) {
        return ruleItemDao.findByTypeAndRefId(type, refId);
    }

    @Override
    public Optional<List<RuleItem>> findByTypeAndRefIdAndRefVersion(Integer type, Long refId, Integer refVersion) {
        return ruleItemDao.findByTypeAndRefIdAndRefVersion(type, refId, refVersion);
    }

    @Override
    public Optional<List<RuleItem>> findByRuleId(Long ruleId) {
        return ruleItemDao.findByRuleId(ruleId);
    }

    public List<RuleItem> findRuleItemsByRuleIdWithEx(Long ruleId) {
        Optional<List<RuleItem>> ruleItemsOp = findByRuleId(ruleId);
        if (!ruleItemsOp.isPresent() || CollectionUtils.isEmpty(ruleItemsOp.get())) {
            throw new CustomBusinessException(ErrorCode.RULE_ITEM_IS_EMPTY);
        }
        return ruleItemsOp.get();
    }

    @Override
    public void deleteByRuleId(Long ruleId) {
        ruleItemDao.deleteByRuleId(ruleId);
    }

    @Override
    public void checkRefByRule(RuleItemTypeEnum type, Long refId) {
        Optional<List<RuleItem>> ruleItemsOp = findByTypeAndRefId(type.getCode(), refId);
        checkResult(ruleItemsOp, type);
    }

    @Override
    public void checkRefByRule(RuleItemTypeEnum type, Long refId, Integer refVersion) {
        Optional<List<RuleItem>> ruleItemsOp = findByTypeAndRefIdAndRefVersion(type.getCode(), refId, refVersion);
        checkResult(ruleItemsOp, type);
    }

    @Override
    public void checkRuleItemActive(List<RuleItem> items) {
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
