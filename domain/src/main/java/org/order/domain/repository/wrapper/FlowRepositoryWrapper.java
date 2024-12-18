package org.order.domain.repository.wrapper;

import org.order.common.enums.ErrorCode;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Flow;
import org.order.domain.entity.version.VersionFlow;
import org.order.domain.repository.FlowRepository;
import org.order.domain.repository.version.VersionFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 流程仓储包装类
 *
 * @author chaobo
 * @date 2024/11/19
 */
@Component
public class FlowRepositoryWrapper {

    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private VersionFlowRepository versionFlowRepository;


    /******************************** flow ************************************************/

    public Flow findByIdWithEx(Long id) {
        return flowRepository.findById(id).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.FLOW_NOT_FOUND)
        );
    }



    public void checkDuplicateName(String name) {
        flowRepository.findByName(name).ifPresent(flow -> {
            throw new CustomBusinessException(ErrorCode.FLOW_NAME_DUPLICATE);
        });
    }

    public void checkDuplicateName(String name, Long id) {
        flowRepository.findByNameAndIdNot(name, id).ifPresent(flow -> {
            throw new CustomBusinessException(ErrorCode.FLOW_NAME_DUPLICATE);
        });
    }

    public void checkFlowExist(Long id) {
        flowRepository.findById(id).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.FLOW_NOT_FOUND)
        );
    }



    /******************************** version flow ************************************************/

    public VersionFlow findByFlowIdAndVersionWithEx(Long flowId, Integer version) {
        return versionFlowRepository.findByFlowIdAndVersion(flowId, version).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND)
        );
    }

    public void checkFlowActive(Long flowId, Integer version) {
        versionFlowRepository.findByFlowIdAndVersion(flowId, version).ifPresent(versionFlow -> {
            if (!StatusEnum.isActive(versionFlow.getStatus())) {
                throw new CustomBusinessException(ErrorCode.FLOW_NOT_ACTIVE);
            }
        });
    }




}
