package org.order.domain.entity.version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 版本入口
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "version_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionEntry {

    /**
     * 入口 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 入口 id
     */
    @Column(name = "entry_id")
    private Long entryId;

    /**
     * 入口名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 入口描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 入口表达式
     */
    @Column(name = "expression")
    private String expression;

    /**
     * 流程id
     */
    @Column(name = "flow_id")
    private Long flowId;

    /**
     * 流程版本号
     */
    @Column(name = "flow_version")
    private Integer flowVersion;

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

    public VersionEntry(Long entryId, String name, String description, String expression, Long flowId, Integer flowVersion, Integer version) {
        this.entryId = entryId;
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.flowId = flowId;
        this.flowVersion = flowVersion;
        this.version = version;
    }

    public boolean isActive() {
        return StatusEnum.isActive(this.status);
    }

    public boolean isInactive() {
        return StatusEnum.isInactive(this.status);
    }
}
