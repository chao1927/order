package org.order.domain.repository.wrapper;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.FlowLine;
import org.order.domain.entity.version.VersionFlowLine;
import org.order.domain.repository.FlowLineRepository;
import org.order.domain.repository.version.VersionFlowLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 流程边校验
 *
 * @author chaobo
 * @date 2024/11/21
 */
@Component
public class FlowLineRepositoryWrapper {

    @Autowired
    private FlowLineRepository flowLineRepository;

    @Autowired
    private VersionFlowLineRepository versionFlowLineRepository;


    public List<FlowLine> findFlowLineByFlowIdWithEx(Long flowId) {
        Optional<List<FlowLine>> linesOp = flowLineRepository.findByFlowId(flowId);
        if (!linesOp.isPresent() || CollectionUtils.isEmpty(linesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_LINE_IS_EMPTY);
        }
        return linesOp.get();
    }

    public List<VersionFlowLine> findVersionFlowLineByFlowIdWithEx(Long versionFlowId) {
        Optional<List<VersionFlowLine>> linesOp = versionFlowLineRepository.findByVersionFlowId(versionFlowId);
        if (!linesOp.isPresent() || CollectionUtils.isEmpty(linesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_LINE_IS_EMPTY);
        }
        return linesOp.get();
    }

}
