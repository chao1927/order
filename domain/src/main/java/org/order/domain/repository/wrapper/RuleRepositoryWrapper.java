package org.order.domain.repository.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Rule;
import org.order.domain.entity.version.VersionRule;
import org.order.domain.repository.RuleRepository;
import org.order.domain.repository.version.VersionRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 规则仓储包装类
 *
 * @author chaobo
 * @date 2024/11/19
 */
@Component
public class RuleRepositoryWrapper {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private VersionRuleRepository versionRuleRepository;



    /******************************** Rule ************************************************/
    /**
     * 规则存在则返回规则，否则抛出异常
     * @param ruleId 规则id
     * @return 变量
     */
    public Rule findByIdWithEx(Long ruleId) {
        return ruleRepository.findById(ruleId).orElseThrow(() -> new CustomBusinessException(ErrorCode.RULE_NOT_FOUND));
    }


    /**
     * 创建时，是否存在同名规则，如果有，抛出异常
     * @param name 规则名称
     */
    public void checkDuplicateName(String name) {
        if (!ruleRepository.findByName(name).isPresent()) {
            throw new CustomBusinessException(ErrorCode.RULE_NAME_DUPLICATION);
        }
    }

    /**
     * 修改时，是否存在同名规则，如果有，抛出异常
     * @param name 灌醉名称
     * @param ruleId 规则id
     */
    public void checkDuplicateName(String name, Long ruleId) {
        if (!ruleRepository.findByNameAndIdNot(name, ruleId).isPresent()) {
            throw new CustomBusinessException(ErrorCode.RULE_NAME_DUPLICATION);
        }
    }


    public void checkRuleExist(Long ruleId) {
        ruleRepository.findById(ruleId).orElseThrow(() -> new CustomBusinessException(ErrorCode.RULE_NOT_FOUND));
    }

    public void checkVersionRuleActive(Long ruleId, Integer version) {
        Optional<VersionRule> versionRuleOp = versionRuleRepository.findByRuleIdAndVersion(ruleId, version);
        if (!versionRuleOp.isPresent()) {
            throw new CustomBusinessException(ErrorCode.RULE_NOT_FOUND);
        }

        if (!versionRuleOp.get().isActive()) {
            throw new CustomBusinessException(ErrorCode.RULE_NOT_ACTIVE);
        }
    }


    /******************************** Version Rule ************************************************/

    public VersionRule findByRuleIdAndVersionWithEx(Long ruleId, Integer version) {
        return versionRuleRepository.findByRuleIdAndVersion(ruleId, version)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.RULE_NOT_FOUND));
    }


}
