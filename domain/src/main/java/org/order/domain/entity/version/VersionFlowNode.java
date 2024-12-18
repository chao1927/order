package org.order.domain.entity.version;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * 版本流程节点
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "version_flow_node")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionFlowNode {
    /**
     * 版本流程节点 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 版本流程 id
     */
    @Column(name = "version_flow_id")
    private Long versionFlowId;

    /**
     * 版本流程节点名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 版本流程节点描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 版本流程节点类型：1.start，2.end，3.rule，4.action，5.judge
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 版本流程节点内容
     */
    @Column(name = "content")
    private String content;

        /**
     * 引用id
     */
    @Column(name = "ref_id")
    private Long refId;

    /**
     * 引用版本
     */
    @Column(name = "ref_version")
    private Integer refVersion;

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

    public VersionFlowNode(Long versionFlowId, String name, String description, Integer type, String content, Long refId, Integer refVersion) {
        this.versionFlowId = versionFlowId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.content = content;
        this.refId = refId;
        this.refVersion = refVersion;
    }
}
