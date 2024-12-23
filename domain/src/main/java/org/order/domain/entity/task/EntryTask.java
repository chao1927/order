package org.order.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 入口任务表
 * @author chaobo
 * @date 2024/11/16
 */
@Entity
@Table(name = "entry_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryTask {
    /**
     * 入口任务表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务编号
     */
    @Column(name = "entry_task_no")
    private String entryTaskNo;

    /**
     * 版本入口id
     */
    @Column(name = "version_entry_id")
    private Long versionEntryId;

    /**
     * 入口id
     */
    @Column(name = "entry_id")
    private Long entryId;

    /**
     * 入口版本号
     */
    @Column(name = "entry_version")
    private Integer entryVersion;

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
