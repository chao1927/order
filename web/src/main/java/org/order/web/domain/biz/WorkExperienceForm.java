package org.order.web.domain.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 工作信息
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceForm {

    private Date startDate;

    private Date endDate;

    private String company;

    private String position;

    private String certifier;

    private String certifierPhone;


}
