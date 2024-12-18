package org.order.domain.entity.version;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.order.common.enums.StatusEnum;

/**
 * 版本流程
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "version_flow")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionFlow {
    /**
     * 版本流程 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 流程 id
     */
    @Column(name = "flow_id")
    private Long flowId;

    /**
     * 版本流程名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 版本流程描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 版本流程版本号
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

    public VersionFlow(Long flowId, String name, String description, Integer version) {
        this.flowId = flowId;
        this.name = name;
        this.description = description;
        this.version = version;
    }
}
