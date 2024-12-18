package org.order.domain.entity.version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 版本 action
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "version_action")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionAction {

    /**
     * 版本 action id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * action id
     */
    @Column(name = "action_id")
    private Long actionId;

    /**
     * 版本action名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 版本action描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 状态：（1. 已发布，2.已下架，3.已删除）
     */
    @Column(name = "status")
    private Integer status;

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

    public void active() {
        this.status = StatusEnum.ACTIVATED.getCode();
    }

    public void inactive() {
        this.status = StatusEnum.INACTIVATED.getCode();
    }

    public VersionAction(Long actionId, String name, String description, Integer version) {
        this.actionId = actionId;
        this.name = name;
        this.description = description;
        this.version = version;
    }

    public boolean isActive() {
        return StatusEnum.isActive(this.status);
    }

    public boolean isInactive() {
        return StatusEnum.isInactive(this.status);
    }
}
