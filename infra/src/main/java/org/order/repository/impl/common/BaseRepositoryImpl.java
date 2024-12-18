package org.order.repository.impl.common;

import org.order.domain.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * 通用仓储接口实现
 *
 * @author chaobo
 * @date 2024/11/19
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T, ID> implements BaseRepository<T, ID> {

    @Autowired
    private JpaRepository<T, ID> repository;


    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void saveAll(List<T> entities) {
        repository.saveAll(entities);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<T> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<List<T>> findByIds(List<ID> ids) {
        return Optional.of(repository.findAllById(ids));
    }
}
