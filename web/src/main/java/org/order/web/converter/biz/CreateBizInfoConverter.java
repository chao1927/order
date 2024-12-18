package org.order.web.converter.biz;

import org.order.application.command.biz.EduExperienceCommand;
import org.order.application.command.biz.FamilyMemberCommand;
import org.order.application.command.biz.WorkExperienceCommand;
import org.order.common.enums.BizSource;
import org.order.web.domain.biz.CreateBizInfoForm;
import org.order.application.command.biz.CreateBizInfoCommand;
import org.order.web.domain.biz.EduExperienceForm;
import org.order.web.domain.biz.FamilyMemberForm;
import org.order.web.domain.biz.WorkExperienceForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务数据转换成 Command
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Component
public class CreateBizInfoConverter {

    public CreateBizInfoCommand convert(CreateBizInfoForm form) {
         return new CreateBizInfoCommand(
                    form.getName(),
                    form.getIdCard(),
                    form.getPhone(),
                    form.getBankcard(),
                    form.getOrderNo(),
                    BizSource.getByCode(form.getSource()),
                    convertEduExperiences(form.getEduExperiences()),
                    convertWorkExperiences(form.getWorkExperiences()),
                    convertFamilyMembers(form.getFamilyMembers())
         );
    }

    private List<EduExperienceCommand> convertEduExperiences(List<EduExperienceForm> forms) {
        return forms.stream().map(form -> new EduExperienceCommand(
                form.getStartDate(),
                form.getEndDate(),
                form.getTitle(),
                form.getContent(),
                form.getCertifier(),
                form.getCertifierPhone()
        )).collect(Collectors.toList());
    }

    private List<WorkExperienceCommand> convertWorkExperiences(List<WorkExperienceForm> forms) {
        return forms.stream().map(form -> new WorkExperienceCommand(
                form.getStartDate(),
                form.getEndDate(),
                form.getCompany(),
                form.getPosition(),
                form.getCertifier(),
                form.getCertifierPhone()
        )).collect(Collectors.toList());
    }

    private List<FamilyMemberCommand> convertFamilyMembers(List<FamilyMemberForm> forms) {
        return forms.stream().map(form -> new FamilyMemberCommand(
                form.getIdentity(),
                form.getName(),
                form.getJob()
        )).collect(Collectors.toList());
    }

}
