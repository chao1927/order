package org.order.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * action 任务表
 * @author chaobo
 * @date 2024/11/16
 */
@Entity
@Table(name = "action_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionTask {
    /**
     * action 任务表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * action 任务编号
     */
    @Column(name = "action_task_no")
    private String actionTaskNo;

    /**
     * 流程任务编号
     */
    @Column(name = "flow_task_no")
    private String flowTaskNo;

    /**
     * 版本流程节点id
     */
    @Column(name = "version_flow_node_id")
    private Long versionFlowNodeId;

    /**
     * 版本 action id
     */
    @Column(name = "version_action_id")
    private Long versionActionId;

    /**
     * action id
     */
    @Column(name = "action_id")
    private Long actionId;

    /**
     * action 版本号
     */
    @Column(name = "action_version")
    private Integer actionVersion;

    /**
     * 业务编号
     */
    @Column(name = "biz_id")
    private String bizId;

    /**
     * 执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 执行结果
     */
    @Column(name = "result")
    private String result;

    /**
     * 执行失败原因
     */
    @Column(name = "fail_reason")
    private String failReason;

    /**
     * 执行完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}
