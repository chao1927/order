package org.order.domain.entity.version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 版本变量
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Entity
@Table(name = "version_param")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionParam {

    /**
     * 版本变量id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 变量id
     */
    @Column(name = "param_id")
    private Long paramId;

    /**
     * 变量名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 变量描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 变量表达式
     */
    @Column(name = "expression")
    private String expression;

    /**
     * 结果类型
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
    private Long createAt;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_at")
    private Long updateAt;

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

    public VersionParam(Long paramId, String name, String description, String expression,
                        Integer resultType, Integer version) {
        this.paramId = paramId;
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.resultType = resultType;
        this.version = version;
    }

    public void active() {
        this.status = StatusEnum.ACTIVATED.getCode();
    }

    public void inactive() {
        this.status = StatusEnum.INACTIVATED.getCode();
    }

    public boolean isActive() {
        return StatusEnum.isActive(this.status);
    }

    public boolean isInactive() {
        return StatusEnum.isInactive(this.status);
    }

}
