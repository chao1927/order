package org.order.web.domain.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建流程
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlowForm {

    private String name;

    private String description;

    private List<CreateFlowNodeForm> nodes;

    private List<CreateFlowLineForm> lines;

}
