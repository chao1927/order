package org.order.domain.validator;

import lombok.extern.slf4j.Slf4j;
import org.order.domain.entity.Rule;
import org.order.domain.entity.RuleItem;
import org.order.domain.entity.version.VersionRule;
import org.order.domain.entity.version.VersionRuleItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 规则校验
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Slf4j
@Component
public class RuleValidator {


    /**
     * - 校验规则表达式，测试是否可执行 (校验规则和创建规则时一样)
     * - 测试结果类型是否正确
     *- 校验规则有没有循环引用 （规则嵌套层数限制，最多只能三层）
     *
     * @param rule 规则聚合
     */
    public void validate(Rule rule, List<RuleItem> items) {
                //- 校验规则子项，判断规则子项的id是否存在。
        validate(items);
    }

    public void validate(VersionRule versionRule, List<VersionRuleItem> items) {

    }

    public void validate(List<RuleItem> ruleItems) {}
}
