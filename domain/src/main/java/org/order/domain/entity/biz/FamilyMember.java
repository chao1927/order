package org.order.domain.entity.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 业务数据-家庭成员
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Entity
@Table(name = "family_member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMember {

    /**
     * 家庭成员id
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
     * 家庭成员身份
     */
    @Column(name = "identity")
    private String identity;

    /**
     * 家庭成员名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 家庭成员工作
     */
    @Column(name = "job")
    private String job;

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
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public FamilyMember(String identity, String name, String job) {
        this.identity = identity;
        this.name = name;
        this.job = job;
    }
}
