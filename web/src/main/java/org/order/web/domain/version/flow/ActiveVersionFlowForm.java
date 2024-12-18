package org.order.web.domain.version.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发布版本流程表单
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveVersionFlowForm {

    private Long flowId;

    private Integer version;

}
