package org.order.web.domain.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 业务数据教育信息
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduExperienceForm {

    private Date startDate;

    private Date endDate;

    private String title;

    private String content;

    private String certifier;

    private String certifierPhone;

}
