package org.order.application.converter;

import org.apache.commons.collections4.CollectionUtils;
import org.order.application.command.biz.CreateBizInfoCommand;
import org.order.application.command.biz.EduExperienceCommand;
import org.order.application.command.biz.FamilyMemberCommand;
import org.order.application.command.biz.WorkExperienceCommand;
import org.order.domain.aggregate.BizInfoAggregate;
import org.order.domain.entity.biz.EduExperience;
import org.order.domain.entity.biz.FamilyMember;
import org.order.domain.entity.biz.WorkExperience;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务信息工厂类
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Component
public class CreateBizInfoCommandConverter {


    public BizInfoAggregate convert(CreateBizInfoCommand command) {
        return new BizInfoAggregate(
                command.getName(),
                command.getIdCard(),
                command.getPhone(),
                command.getIdCard(),
                command.getOrderNo(),
                command.getSource().getCode(),
                convertEduExperience(command.getEduExperiences()),
                convertWorkExperience(command.getWorkExperiences()),
                convertFamilyMember(command.getFamilyMembers())
        );
    }

    private List<FamilyMember> convertFamilyMember(List<FamilyMemberCommand> familyMembers) {
        if (CollectionUtils.isEmpty(familyMembers)) {
            return Collections.emptyList();
        }

        return familyMembers.stream().map(familyMember -> new FamilyMember(
                familyMember.getIdentity(),
                familyMember.getName(),
                familyMember.getJob()
        )).collect(Collectors.toList());
    }


    private List<EduExperience> convertEduExperience(List<EduExperienceCommand> eduExperiences) {
        if (CollectionUtils.isNotEmpty(eduExperiences)) {
            return Collections.emptyList();
        }

        return eduExperiences.stream().map(eduExperience -> new EduExperience(
                eduExperience.getStartDate(),
                eduExperience.getEndDate(),
                eduExperience.getTitle(),
                eduExperience.getContent(),
                eduExperience.getCertifier(),
                eduExperience.getCertifierPhone()
        )).collect(Collectors.toList());
    }


    private List<WorkExperience> convertWorkExperience(List<WorkExperienceCommand> workExperiences) {
        if (CollectionUtils.isEmpty(workExperiences)) {
            return Collections.emptyList();
        }

        return workExperiences.stream().map(workExperience -> new WorkExperience(
                workExperience.getStartDate(),
                workExperience.getEndDate(),
                workExperience.getCompany(),
                workExperience.getPosition(),
                workExperience.getCertifier(),
                workExperience.getCertifierPhone()
        )).collect(Collectors.toList());

    }


}
