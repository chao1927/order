package org.order.application.command.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 教育经历
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduExperienceCommand {

    private Date startDate;

    private Date endDate;

    private String title;

    private String content;

    private String certifier;

    private String certifierPhone;

}
