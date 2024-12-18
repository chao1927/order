package org.order.web.domain.param;

import lombok.Data;

/**
 * @author chaobo
 * @date 2024/11/9 16:14
 */
@Data
public class UpdateParamForm {

    private Long id;

    private String name;

    private String description;

    private String expression;

    private Integer resultType;

}
