package org.order.domain.repository;

import org.order.domain.entity.Param;

/**
 * 变量仓储接口
 *
 * @author chaobo
 * @date 2024/11/13
 */
public interface ParamRepository extends BaseRepository<Param, Long>, NameRepository<Param> {

    Param findByIdWithEx(Long id);

    void checkDuplicateName(String name);

    void checkDuplicateName(String name, Long id);

    void checkParamExist(Long id);


}
