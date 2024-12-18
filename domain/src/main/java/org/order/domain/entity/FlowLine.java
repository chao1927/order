package org.order.domain.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * 流程边
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "flow_line")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowLine {
    /**
     * 流程边 id
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
     * 流程边内容
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

    public FlowLine(Long id, Long flowId, Long preNodeId, String preNodeName, Long nextNodeId, String nextNodeName, String content) {
        this.id = id;
        this.flowId = flowId;
        this.preNodeId = preNodeId;
        this.preNodeName = preNodeName;
        this.nextNodeId = nextNodeId;
        this.nextNodeName = nextNodeName;
        this.content = content;
    }

    public FlowLine(String preNodeName, String nextNodeName, String content) {
        this.preNodeName = preNodeName;
        this.nextNodeName = nextNodeName;
        this.content = content;
    }

    public FlowLine(Long flowId, Long preNodeId, String preNodeName, Long nextNodeId, String nextNodeName, String content) {
        this.flowId = flowId;
        this.preNodeId = preNodeId;
        this.preNodeName = preNodeName;
        this.nextNodeId = nextNodeId;
        this.nextNodeName = nextNodeName;
        this.content = content;
    }
}
