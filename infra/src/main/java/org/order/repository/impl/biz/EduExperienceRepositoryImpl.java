package org.order.repository.impl.biz;

import org.order.domain.entity.biz.EduExperience;
import org.order.domain.repository.biz.EduExperienceRepository;
import org.order.repository.dao.biz.EduExperienceDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 业务数据教育仓储实现
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Component
public class EduExperienceRepositoryImpl extends BaseRepositoryImpl<EduExperience, Long> implements EduExperienceRepository {

    @Autowired
    private EduExperienceDao eduExperienceDao;

    @Override
    public Optional<EduExperience> findByBizInfoId(Long bizInfoId) {
        return eduExperienceDao.findByBizInfoId(bizInfoId);
    }
}
