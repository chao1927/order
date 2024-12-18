package org.order.web.domain.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 家庭成员
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberForm {

    private String identity;

    private String name;

    private String job;


}
