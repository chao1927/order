package org.order.executor.converter;

import org.order.common.enums.ResultTypeEnum;
import org.order.common.enums.RuleItemTypeEnum;
import org.order.domain.entity.version.VersionRule;
import org.order.domain.entity.version.VersionRuleItem;
import org.order.executor.cache.domain.VersionRuleConfig;
import org.order.executor.cache.domain.VersionRuleItemConfig;

import java.util.List;

/**
 * 规则配置相关转换器
 *
 * @author chaobo
 * @date 2024/12/26
 */
public class RuleConfigConverter {

    public static VersionRuleItemConfig convertVersionRuleItemConfig(VersionRuleItem item) {
        return new VersionRuleItemConfig(
                item.getId(),
                item.getVersionRuleId(),
                item.getSort(),
                item.getName(),
                RuleItemTypeEnum.getByCode(item.getType()),
                item.getRefId(),
                item.getRefVersion()
        );
    }

    public static VersionRuleConfig convertVersionRuleConfig(VersionRule versionRule,
                                                             List<VersionRuleItemConfig> versionRuleItemConfigs) {
        return new VersionRuleConfig(
                versionRule.getId(),
                versionRule.getRuleId(),
                versionRule.getName(),
                versionRule.getDescription(),
                versionRule.getExpression(),
                ResultTypeEnum.getByCode(versionRule.getResultType()),
                versionRule.getVersion(),
                versionRuleItemConfigs);
    }

}
