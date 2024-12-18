package org.order.domain.entity.version;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * 版本流程边
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "version_flow_line")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionFlowLine {
    /**
     * 版本流程边 id
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
     * 前置节点 id
     */
    @Column(name = "pre_node_id")
    private Long preNodeId;

    /**
     * 前置节点名称
     */
    @Column(name = "pre_node_name")
    private String preNodeName;

    /**
     * 后置节点 id
     */
    @Column(name = "next_node_id")
    private Long nextNodeId;

    /**
     * 后置节点名称
     */
    @Column(name = "next_node_name")
    private String nextNodeName;

    /**
     * 版本流程边内容
     */
    @Column(name = "content")
    private String content;

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

    public VersionFlowLine(Long versionFlowId, Long preNodeId, String preNodeName, Long nextNodeId, String nextNodeName, String content) {
        this.versionFlowId = versionFlowId;
        this.preNodeId = preNodeId;
        this.preNodeName = preNodeName;
        this.nextNodeId = nextNodeId;
        this.nextNodeName = nextNodeName;
        this.content = content;
    }
}
