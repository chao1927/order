package org.order.domain.entity.version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 版本规则
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "version_rule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionRule {
    /**
     * 版本规则id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 版本规则id
     */
    @Column(name = "rule_id")
    private Long ruleId;

    /**
     * 版本规则名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 版本规则描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 版本规则表达式
     */
    @Column(name = "expression")
    private String expression;

    /**
     * 版本规则结果类型
     */
    @Column(name = "result_type")
    private Integer resultType;

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

    public VersionRule(Long ruleId, String name, String description, String expression, Integer resultType, Integer version) {
        this.ruleId = ruleId;
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.resultType = resultType;
        this.version = version;
    }

    public VersionRule(VersionRule versionRule) {
        this.id = versionRule.getId();
        this.ruleId = versionRule.getRuleId();
        this.name = versionRule.getName();
        this.description = versionRule.getDescription();
        this.expression = versionRule.getExpression();
        this.resultType = versionRule.getResultType();
        this.version = versionRule.getVersion();
        this.status = versionRule.getStatus();
        this.createAt = versionRule.getCreateAt();
        this.createTime = versionRule.getCreateTime();
        this.updateAt = versionRule.getUpdateAt();
        this.updateTime = versionRule.getUpdateTime();
        this.isDeleted = versionRule.getIsDeleted();
    }

    public boolean isActive() {
        return StatusEnum.isActive(this.status);
    }

    public boolean isInactive() {
        return StatusEnum.isInactive(this.status);
    }
}
