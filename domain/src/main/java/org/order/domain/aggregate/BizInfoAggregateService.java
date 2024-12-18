package org.order.domain.aggregate;

import org.apache.commons.collections4.CollectionUtils;
import org.order.domain.entity.biz.EduExperience;
import org.order.domain.entity.biz.FamilyMember;
import org.order.domain.entity.biz.WorkExperience;
import org.order.domain.event.BizDataArrivedDomainEvent;
import org.order.domain.event.DomainEventPublisher;
import org.order.domain.repository.biz.BizInfoRepository;
import org.order.domain.repository.biz.EduExperienceRepository;
import org.order.domain.repository.biz.FamilyMemberRepository;
import org.order.domain.repository.biz.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务数据聚合根服务
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Component
public class BizInfoAggregateService {

    @Autowired
    private BizInfoRepository bizInfoRepository;

    @Autowired
    private EduExperienceRepository eduExperienceRepository;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Autowired
    private DomainEventPublisher domainEventPublisher;


    @Transactional(rollbackFor = Exception.class)
    public void save(BizInfoAggregate bizInfoAgg) {
        bizInfoRepository.save(bizInfoAgg);

        Long bizInfoId = bizInfoAgg.getId();
        List<EduExperience> eduExperiences = bizInfoAgg.getEduExperiences();
        if (CollectionUtils.isNotEmpty(eduExperiences)) {
            eduExperiences.forEach(eduExperience -> eduExperience.setBizInfoId(bizInfoId));
            eduExperienceRepository.saveAll(eduExperiences);
        }

        List<WorkExperience> workExperiences = bizInfoAgg.getWorkExperiences();
        if (CollectionUtils.isNotEmpty(workExperiences)) {
            workExperiences.forEach(workExperience -> workExperience.setBizInfoId(bizInfoId));
            workExperienceRepository.saveAll(workExperiences);
        }

        List<FamilyMember> familyMembers = bizInfoAgg.getFamilyMembers();
        if (CollectionUtils.isNotEmpty(familyMembers)) {
            familyMembers.forEach(familyMember -> familyMember.setBizInfoId(bizInfoId));
            familyMemberRepository.saveAll(familyMembers);
        }

        domainEventPublisher.publish(new BizDataArrivedDomainEvent(bizInfoAgg.getOrderNo()));
    }

}
