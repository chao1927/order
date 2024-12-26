package org.order.repository.dao.biz;

import org.order.domain.entity.biz.EduExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 业务数据教育仓储 jpa
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Repository
public interface EduExperienceDao extends JpaRepository<EduExperience, Long> {

    Optional<List<EduExperience>> findByBizInfoId(Long bizInfoId);

}
