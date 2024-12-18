package org.order.web.domain.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建流程边表单
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlowLineForm {

    private String preNodeName;

    private String nextNodeName;

    private String content;

}
