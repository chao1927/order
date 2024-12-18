package org.order.application.command.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.BizSource;

import java.util.List;

/**
 * 新增业务数据
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBizInfoCommand {

    private String name;

    private String idCard;

    private String phone;

    private String bankcard;

    private String orderNo;

    private BizSource source;

    private List<EduExperienceCommand> eduExperiences;

    private List<WorkExperienceCommand> workExperiences;

    private List<FamilyMemberCommand> familyMembers;

}
