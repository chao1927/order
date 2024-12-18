package org.order.application.command.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 业务数据工作经历
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceCommand {

    private Date startDate;

    private Date endDate;

    private String company;

    private String position;

    private String certifier;

    private String certifierPhone;

}
