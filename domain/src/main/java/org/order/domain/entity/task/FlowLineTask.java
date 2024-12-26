package org.order.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.TaskStatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 流程边任务表
 * @author chaobo
 * @date 2024/11/16
 */
@Entity
@Table(name = "flow_line_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowLineTask {
    /**
     * 流程边任务表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 流程任务编号
     */
    @Column(name = "flow_task_no")
    private String flowTaskNo;

    /**
     * 版本流程边id
     */
    @Column(name = "version_flow_line_id")
    private Long versionFlowLineId;

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

    public FlowLineTask(String flowTaskNo, Long versionFlowLineId, String bizId) {
        this.flowTaskNo = flowTaskNo;
        this.versionFlowLineId = versionFlowLineId;
        this.bizId = bizId;
    }

    public void init() {
        this.status = TaskStatusEnum.WAIT_EXECUTE.getCode();
    }

    public void execute() {
        this.status = TaskStatusEnum.EXECUTING.getCode();
    }

    public void success(String result) {
        this.status = TaskStatusEnum.EXECUTE_SUCCESS.getCode();
        this.result = result;
        this.finishTime = new Date();
    }

    public void failed(String result, String failReason) {
        this.status = TaskStatusEnum.EXECUTE_FAILED.getCode();
        this.result = result;
        this.failReason = failReason;
        this.finishTime = new Date();
    }

    public void timeout() {
        this.status = TaskStatusEnum.TASK_TIMEOUT.getCode();
    }
}
