package org.order.web.domain.param;

import lombok.Data;

/**
 * @author chaobo
 * @date 2024/11/9 15:58
 */
@Data
public class CreateParamForm {

    /**
     * 变量名称
     */
    private String name;


    /**
     * 变量描述
     */
    private String description;


    /**
     * 变量表达式
     */
    private String expression;

    /**
     * 结果类型
     */
    private Integer resultType;

}
