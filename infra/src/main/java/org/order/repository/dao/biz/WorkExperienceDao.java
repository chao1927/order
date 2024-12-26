package org.order.repository.dao.biz;

import org.order.domain.entity.biz.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 工作经验 jpa 接口
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Repository
public interface WorkExperienceDao extends JpaRepository<WorkExperience, Long> {

    Optional<List<WorkExperience>> findByBizInfoId(Long bizInfoId);

}
