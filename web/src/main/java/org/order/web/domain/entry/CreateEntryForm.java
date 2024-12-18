package org.order.web.domain.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入口创建表单
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEntryForm {

    private String name;

    private String description;

    private String expression;

    private Long flowId;

    private Integer flowVersion;

}
