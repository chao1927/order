package org.order.web.domain.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 修改流程
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowForm {

    private Long id;

    private String name;

    private String description;

    private List<UpdateFlowNodeForm> nodes;

    private List<UpdateFlowLineForm> lines;

}
