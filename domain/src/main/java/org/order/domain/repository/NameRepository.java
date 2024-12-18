package org.order.domain.repository;

import java.util.Optional;

/**
 * 按名称查询能力
 *
 * @author chaobo
 * @date 2024/11/19
 */
public interface NameRepository<T> {

    /**
     * 修改时，判断是否重复的名称
     *
     * @param name 要修改的名称
     * @param id 要修改的实体的id
     * @return 是否重复
     */
    Optional<T> findByNameAndIdNot(String name, Long id);

    /**
     * 新增时，判断是否重复的名称
     * @param name 要新增的名称
     * @return 是否重复
     */
    Optional<T> findByName(String name);

}
