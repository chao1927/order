package org.order.domain.validator;

import org.springframework.stereotype.Component;

/**
 * 表达式校验器
 *
 * @author chaobo
 * @date 2024/11/15
 */
@Component
public class ExpressionValidator {

    public boolean validate(String expression, Integer resultType) {
        // TODO: 校验表达式是否能执行，且返回的结果类型是否匹配

        return true;
    }

}
