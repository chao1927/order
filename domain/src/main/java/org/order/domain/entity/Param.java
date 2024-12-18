package org.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.constant.CommonConstant;

import javax.persistence.*;
import java.util.Date;

/**
 * 变量
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Entity
@Table(name = "param")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {

    /**
     * 变量id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Param(String name, String description, String expression, Integer resultType) {
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.resultType = resultType;
    }

    public Param(Long id, String name, String description, String expression, Integer resultType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.resultType = resultType;
    }

    public void initVersion() {
        if (this.version == null) {
            this.version = CommonConstant.INIT_VERSION;
        }
    }

    public void versioned() {
        this.version++;
    }

    public void change(String name, String description, String expression, Integer resultType) {
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.resultType = resultType;
    }

}
