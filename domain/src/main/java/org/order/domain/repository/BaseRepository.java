package org.order.domain.repository;

import java.util.List;
import java.util.Optional;

/**
 * base 仓储
 *
 * @author chaobo
 * @date 2024/11/19
 */
public interface BaseRepository<T, ID> {

    void save(T entity);

    void saveAll(List<T> entities);

    void deleteById(ID id);

    void deleteAll(List<T> entities);

    Optional<T> findById(ID id);

    Optional<List<T>> findByIds(List<ID> ids);



}
