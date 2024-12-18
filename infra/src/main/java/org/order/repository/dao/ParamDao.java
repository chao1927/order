package org.order.repository.dao;

import org.order.domain.entity.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 变量仓储 jpa
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Repository
public interface ParamDao extends JpaRepository<Param, Long> {

    Optional<Param> findByName(String name);

    Optional<Param> findByNameAndIdNot(String name, Long id);
}
