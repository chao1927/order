package org.order.domain.repository.biz;

import org.order.domain.entity.biz.EduExperience;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 教育经历仓储
 *
 * @author chaobo
 * @date 2024/11/13
 */
public interface EduExperienceRepository extends BaseRepository<EduExperience, Long> {

    Optional<List<EduExperience>> findByBizInfoId(Long bizInfoId);

}
