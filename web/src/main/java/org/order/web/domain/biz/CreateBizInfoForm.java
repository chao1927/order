package org.order.web.domain.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 业务数据新增
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBizInfoForm {

    private String name;

    private String idCard;

    private String phone;

    private String bankcard;

    private String orderNo;

    private Integer source;

    private List<EduExperienceForm> eduExperiences;

    private List<WorkExperienceForm> workExperiences;

    private List<FamilyMemberForm> familyMembers;

    // todo 其它业务字段信息待增加

}
