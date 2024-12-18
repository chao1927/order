package org.order.domain.entity.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 业务数据-工作经验
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Entity
@Table(name = "work_experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {

    /**
     * 工作经历id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 业务数据id
     */
    @Column(name = "biz_info_id")
    private Long bizInfoId;

    /**
     * 开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 公司
     */
    @Column(name = "company")
    private String company;

    /**
     * 职位
     */
    @Column(name = "position")
    private String position;

    /**
     * 证明人
     */
    @Column(name = "certifier")
    private String certifier;

    /**
     * 证明人手机号
     */
    @Column(name = "certifier_phone")
    private String certifierPhone;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除，0.否，1.是
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public WorkExperience(Date startDate, Date endDate, String company, String position, String certifier, String certifierPhone) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
        this.position = position;
        this.certifier = certifier;
        this.certifierPhone = certifierPhone;
    }
}
