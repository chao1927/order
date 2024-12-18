package org.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.constant.CommonConstant;

import javax.persistence.*;
import java.util.Date;

/**
 * action
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Entity
@Table(name = "action")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    /**
     * action id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * action名称
     */
    @Column(name = "name")
    private String name;

    /**
     * action 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 创建人
     */
    @Column(name = "create_at")
    private String createAt;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_at")
    private String updateAt;

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

    public Action(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Action(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void initVersion() {
        if (this.version == null) {
            this.version = CommonConstant.INIT_VERSION;
        }
    }

    public void versioned() {
        this.version++;
    }


    public void change(String name, String description) {
        this.name = name;
        this.description = description;
    }


}
