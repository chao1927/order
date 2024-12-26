package org.order.repository.impl;

import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Flow;
import org.order.domain.repository.FlowRepository;
import org.order.repository.dao.FlowDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 流程仓储实现
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Component
public class FlowRepositoryImpl extends BaseRepositoryImpl<Flow, Long> implements FlowRepository {

    @Autowired
    private FlowDao flowDao;

    @Override
    public Optional<Flow> findByNameAndIdNot(String name, Long id) {
        return flowDao.findByNameAndIdNot(name, id);
    }

    @Override
    public Optional<Flow> findByName(String name) {
        return flowDao.findByName(name);
    }

    @Override
    public Flow findByIdWithEx(Long id) {
        return findById(id).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.FLOW_NOT_FOUND)
        );
    }

    @Override
    public void checkDuplicateName(String name) {
        findByName(name).ifPresent(flow -> {
            throw new CustomBusinessException(ErrorCode.FLOW_NAME_DUPLICATE);
        });
    }

    @Override
    public void checkDuplicateName(String name, Long id) {
        findByNameAndIdNot(name, id).ifPresent(flow -> {
            throw new CustomBusinessException(ErrorCode.FLOW_NAME_DUPLICATE);
        });
    }

    @Override
    public void checkFlowExist(Long id) {
        findById(id).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.FLOW_NOT_FOUND)
        );
    }

}
