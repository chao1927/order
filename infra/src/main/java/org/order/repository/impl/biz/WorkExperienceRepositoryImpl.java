package org.order.repository.impl.biz;

import org.order.domain.entity.biz.WorkExperience;
import org.order.domain.repository.biz.WorkExperienceRepository;
import org.order.repository.dao.biz.WorkExperienceDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 工作经验仓储接口实现
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Component
public class WorkExperienceRepositoryImpl extends BaseRepositoryImpl<WorkExperience, Long> implements WorkExperienceRepository {

    @Autowired
    private WorkExperienceDao workExperienceDao;


    @Override
    public Optional<WorkExperience> findByBizInfoId(Long bizInfoId) {
        return workExperienceDao.findByBizInfoId(bizInfoId);
    }
}
