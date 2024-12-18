package org.order.domain.repository.wrapper;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.RuleItem;
import org.order.domain.entity.version.VersionRuleItem;
import org.order.domain.repository.RuleItemRepository;
import org.order.domain.repository.version.VersionRuleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 规则子项校验
 * 1. 用于校验 规则、变量是否被规则子项引用
 * 2. 校验规则子项是否已经发布
 *
 * @author chaobo
 * @date 2024/11/21
 */
@Component
public class RuleItemRepositoryWrapper {

    @Autowired
    private RuleItemRepository ruleItemRepository;

    @Autowired
    private VersionRuleItemRepository versionRuleItemRepository;

    @Autowired
    private RuleRepositoryWrapper ruleRepositoryWrapper;

    @Autowired
    private ParamRepositoryWrapper paramRepositoryWrapper;


    public void checkRefByRule(RuleItemTypeEnum type, Long refId) {
        Optional<List<RuleItem>> ruleItemsOp = ruleItemRepository.findByTypeAndRefId(type.getCode(), refId);
        checkResult(ruleItemsOp, type);
    }

    public void checkRefByVersionRule(RuleItemTypeEnum type, Long refId) {
        Optional<List<VersionRuleItem>> versionRUleItemsOp = versionRuleItemRepository.findByTypeAndRefId(type.getCode(), refId);
        checkResult(versionRUleItemsOp, type);
    }

    public void checkRefByRule(RuleItemTypeEnum type, Long refId, Integer refVersion) {
        Optional<List<RuleItem>> ruleItemsOp = ruleItemRepository.findByTypeAndRefIdAndRefVersion(type.getCode(), refId, refVersion);
        checkResult(ruleItemsOp, type);
    }

    public void checkRefByVersionRule(RuleItemTypeEnum type, Long refId, Integer refVersion) {
        Optional<List<VersionRuleItem>> versionRuleItemsOp
                = versionRuleItemRepository.findByTypeAndRefIdAndRefVersion(RuleItemTypeEnum.PARAM.getCode(), refId, refVersion);
        checkResult(versionRuleItemsOp, type);
    }

    public void checkRefByActiveVersionRule(RuleItemTypeEnum type, Long refId, Integer refVersion) {
        Optional<List<VersionRuleItem>> itemsOp
                = versionRuleItemRepository.findByTypeAndRefIdAndRefVersionAndStatus(type.getCode(),
                refId, refVersion, StatusEnum.ACTIVATED.getCode());
        checkResult(itemsOp, type);
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

    /******************************** 校验规则子项是否都已发布 ************************************************/

    public void checkRuleItemActive(List<RuleItem> items) {
        items.forEach(item -> {
            RuleItemTypeEnum type = RuleItemTypeEnum.getByCode(item.getType());
            if (type == null) {
                throw new CustomBusinessException(ErrorCode.RULE_ITEM_TYPE_ERROR);
            }

            switch (type) {
                case PARAM:
                    paramRepositoryWrapper.checkVersionParamActive(item.getRefId(), item.getRefVersion());
                    break;
                case RULE:
                    ruleRepositoryWrapper.checkVersionRuleActive(item.getRefId(), item.getRefVersion());
                    break;
            }
        });
    }

    public void checkVersionRuleItemActive(List<VersionRuleItem> items) {
        items.forEach(item -> {
            RuleItemTypeEnum type = RuleItemTypeEnum.getByCode(item.getType());
            if (type == null) {
                throw new CustomBusinessException(ErrorCode.RULE_ITEM_TYPE_ERROR);
            }

            switch (type) {
                case PARAM:
                    paramRepositoryWrapper.checkVersionParamActive(item.getRefId(), item.getRefVersion());
                    break;
                case RULE:
                    ruleRepositoryWrapper.checkVersionRuleActive(item.getRefId(), item.getRefVersion());
                    break;
            }
        });
    }

    public List<RuleItem> findRuleItemsByRuleIdWithEx(Long ruleId) {
        Optional<List<RuleItem>> ruleItemsOp = ruleItemRepository.findByRuleId(ruleId);
        if (!ruleItemsOp.isPresent() || CollectionUtils.isEmpty(ruleItemsOp.get())) {
            throw new CustomBusinessException(ErrorCode.RULE_ITEM_IS_EMPTY);
        }
        return ruleItemsOp.get();
    }

    public List<VersionRuleItem> findRuleItemsByVersionRuleIdWithEx(Long versionRuleId) {
        Optional<List<VersionRuleItem>> ruleItemsOp = versionRuleItemRepository.findByVersionRuleId(versionRuleId);
        if (!ruleItemsOp.isPresent() || CollectionUtils.isEmpty(ruleItemsOp.get())) {
            throw new CustomBusinessException(ErrorCode.RULE_ITEM_IS_EMPTY);
        }
        return ruleItemsOp.get();
    }


}
