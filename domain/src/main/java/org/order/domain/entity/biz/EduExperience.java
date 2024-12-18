package org.order.domain.entity.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 业务数据-教育经历
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Entity
@Table(name = "edu_experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduExperience {

    /**
     * 教育经历id
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
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

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
     * 是否删除：0.否，1.是
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public EduExperience(Date startDate, Date endDate, String title, String content, String certifier, String certifierPhone) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
        this.certifier = certifier;
        this.certifierPhone = certifierPhone;
    }
}
