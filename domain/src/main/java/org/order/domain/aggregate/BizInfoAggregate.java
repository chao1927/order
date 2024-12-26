package org.order.domain.aggregate;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.order.domain.entity.biz.BizInfo;
import org.order.domain.entity.biz.EduExperience;
import org.order.domain.entity.biz.FamilyMember;
import org.order.domain.entity.biz.WorkExperience;

import java.util.Date;
import java.util.List;

/**
 * 业务信息聚合根
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BizInfoAggregate extends BizInfo {

    private List<EduExperience> eduExperiences;

    private List<WorkExperience> workExperiences;

    private List<FamilyMember> familyMembers;

    public BizInfoAggregate(String name, String idCard, String phone, String bankcard, String orderNo, Integer source,
                            List<EduExperience> eduExperiences,
                            List<WorkExperience> workExperiences,
                            List<FamilyMember> familyMembers) {
        super(name, idCard, phone, bankcard, orderNo, source);
        this.eduExperiences = eduExperiences;
        this.workExperiences = workExperiences;
        this.familyMembers = familyMembers;
    }

    public BizInfoAggregate(Long id, String name, String idCard, String phone, String bankcard, String orderNo,
                            Integer source, Date createTime, Date updateTime, Boolean isDeleted) {
        super(id, name, idCard, phone, bankcard, orderNo, source, createTime, updateTime, isDeleted);
    }

    public String toData() {
        return JSON.toJSONString(this);
    }
}
