package org.order.domain.repository.biz;

import org.order.domain.entity.biz.WorkExperience;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 工作经验仓储接口
 *
 * @author chaobo
 * @date 2024/11/13
 */
public interface WorkExperienceRepository extends BaseRepository<WorkExperience, Long> {

    Optional<List<WorkExperience>> findByBizInfoId(Long bizInfoId);

}
