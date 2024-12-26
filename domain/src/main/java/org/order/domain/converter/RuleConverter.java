package org.order.domain.converter;

import org.order.domain.entity.RuleItem;
import org.order.domain.entity.version.VersionRuleItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 规则相关转换
 *
 * @author chaobo
 * @date 2024/12/24
 */
public class RuleConverter {


    public static List<RuleItem> convertNewRuleItems(Long ruleId, List<RuleItem> ruleItems) {
        return ruleItems.stream().map(item -> new RuleItem(
                ruleId,
                item.getSort(),
                item.getName(),
                item.getType(),
                item.getRefId(),
                item.getRefVersion()
        )).collect(Collectors.toList());
    }

    public static List<VersionRuleItem> convertNewVersionRuleItems(Long versionRuleId, List<RuleItem> ruleItems) {
        return ruleItems.stream().map(item -> new VersionRuleItem(
                versionRuleId,
                item.getSort(),
                item.getName(),
                item.getType(),
                item.getRefId(),
                item.getRefVersion()
        )).collect(Collectors.toList());
    }

}
