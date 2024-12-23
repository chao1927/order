package org.order.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 规则任务表
 * @author chaobo
 * @date 2024/11/16
 */
@Entity
@Table(name = "rule_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleTask {
    /**
     * 规则任务表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 规则任务编号
     */
    @Column(name = "rule_task_no")
    private String ruleTaskNo;

    /**
     * 流程任务编号
     */
    @Column(name = "flow_task_no")
    private String flowTaskNo;

    /**
     * 任务类型：1：来源于流程节点的规则任务，2：来源于规则子项的规则任务
     */
    @Column(name = "task_type")
    private Byte taskType;

    /**
     * 任务编号: taskType为1：来源于流程节点的任务，则为流程任务的id: flowTaskNo,taskType 为2：来源于规则子项的任务，则为规则任务的id：ruleTaskNo
     */
    @Column(name = "ref_task_no")
    private String refTaskNo;

    /**
     * 版本规则id
     */
    @Column(name = "version_rule_id")
    private Long versionRuleId;

    /**
     * 规则id
     */
    @Column(name = "rule_id")
    private Long ruleId;

    /**
     * 规则版本号
     */
    @Column(name = "rule_version")
    private Integer ruleVersion;

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
