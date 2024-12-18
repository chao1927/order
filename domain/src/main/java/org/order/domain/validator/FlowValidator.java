package org.order.domain.validator;

import org.order.domain.entity.FlowLine;
import org.order.domain.entity.FlowNode;
import org.order.domain.entity.version.VersionFlowLine;
import org.order.domain.entity.version.VersionFlowNode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 流程校验器
 *
 * @author chaobo
 * @date 2024/11/20
 */
@Component
public class FlowValidator {


    /**
     * 校验流程是否有效
     *
     * @param nodes 流程节点
     * @param lines 流程边
     */
    public void validFlow(List<FlowNode> nodes, List<FlowLine> lines) {

    }

    /**
     * 校验版本流程是否有效
     *
     * @param nodes 流程节点
     * @param lines 流程边
     */
    public void validVersionFlow(List<VersionFlowNode> nodes, List<VersionFlowLine> lines) {

    }

}
