package org.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.constant.CommonConstant;

import javax.persistence.*;
import java.util.Date;

/**
 * 入口
 * @author chaobo
 * @date 2024/11/17 
 */
@Entity
@Table(name = "entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

     /**
     * 入口 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Entry(Long id, String name, String description, String expression, Long flowId, Integer flowVersion) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.flowId = flowId;
        this.flowVersion = flowVersion;
    }

    public Entry(String name, String description, String expression, Long flowId, Integer flowVersion) {
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.flowId = flowId;
        this.flowVersion = flowVersion;
    }

    public void initVersion() {
        if (this.version == null) {
            this.version = CommonConstant.INIT_VERSION;
        }
    }

    public void versioned() {
        this.version++;
    }

    public void change(String name, String description, String expression, Long flowId, Integer flowVersion) {
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.flowId = flowId;
        this.flowVersion = flowVersion;
    }


}
