package org.order.repository.dao.version;

import org.order.domain.entity.version.VersionParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 版本变量仓储 jpa
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Repository
public interface VersionParamDao extends JpaRepository<VersionParam, Long> {

    Optional<VersionParam> findByParamIdAndVersion(Long paramId, Integer version);


    Optional<List<VersionParam>> findByParamIdAndStatus(Long paramId, Integer status);

    Optional<List<VersionParam>> findByParamId(Long paramId);

    void deleteByParamId(Long paramId);

}
