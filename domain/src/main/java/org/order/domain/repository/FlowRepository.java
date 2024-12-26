package org.order.domain.repository;

import org.order.domain.entity.Flow;

/**
 * 流程
 *
 * @author chaobo
 * @date 2024/11/17
 */
public interface FlowRepository extends BaseRepository<Flow, Long>, NameRepository<Flow> {

    Flow findByIdWithEx(Long id);

    void checkDuplicateName(String name);

    void checkDuplicateName(String name, Long id);

    void checkFlowExist(Long id);
}
