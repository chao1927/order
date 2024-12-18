package org.order.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.order.domain.entity.Flow;
import org.order.domain.entity.FlowLine;
import org.order.domain.entity.FlowNode;

import java.util.List;

/**
 * 流程聚合
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlowAggregate extends Flow {

    private List<FlowNode> nodes;

    private List<FlowLine> lines;

    public FlowAggregate(Long id, String name, String description, List<FlowNode> nodes, List<FlowLine> lines) {
        super(id, name, description);
        this.nodes = nodes;
        this.lines = lines;
    }

    public FlowAggregate(String name, String description, List<FlowNode> nodes, List<FlowLine> lines) {
        super(name, description);
        this.nodes = nodes;
        this.lines = lines;
    }
}
