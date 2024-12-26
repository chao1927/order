package org.order.executor.task.flow;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.TaskFailedReasonEnum;
import org.order.common.enums.TaskTypeEnum;
import org.order.domain.aggregate.task.FlowTaskAggregate;
import org.order.domain.entity.task.FlowLineTask;
import org.order.domain.entity.task.FlowTask;
import org.order.executor.cache.config.ConfigCacheManager;
import org.order.executor.cache.data.GlobalCache;
import org.order.executor.cache.domain.VersionFlowConfig;
import org.order.executor.cache.domain.VersionFlowLineConfig;
import org.order.executor.cache.domain.VersionFlowNodeConfig;
import org.order.executor.task.TaskRunner;
import org.order.executor.utils.ExpressionUtil;
import org.order.executor.utils.TaskNoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 流程任务运行器
 *
 * @author chaobo
 * @date 2024/12/26
 */
@Component
public class FlowTaskRunner implements TaskRunner<FlowTaskUnit> {

    @Autowired
    private ConfigCacheManager configCacheManager;

    @Autowired
    private GlobalCache globalCache;

    @Autowired
    private FlowTaskAggregate flowTaskAggregate;

    @Autowired
    private FlowNodeTaskRunner flowNodeTaskRunner;



    @Override
    public void run(FlowTaskUnit taskUnit) {
        // 1. 根据 flowId, flowVersion 查询流程配置，如果流程配置不存在，则直接返回
        Long flowId = taskUnit.getFlowId();
        Integer flowVersion = taskUnit.getFlowVersion();
        VersionFlowConfig versionFlowConfig = configCacheManager.getFlowConfig(flowId, flowVersion);
        if (null == versionFlowConfig) {
            return;
        }

        // 2. 创建流程任务 flowTask，初始化并保存到数据库
        FlowTask flowTask = new FlowTask(
                TaskNoGenerator.generate(TaskTypeEnum.FLOW),
                taskUnit.getEntryTaskNo(),
                versionFlowConfig.getId(),
                versionFlowConfig.getFlowId(),
                versionFlowConfig.getVersion(),
                taskUnit.bizId()
        );
        flowTask.init();
        flowTaskAggregate.save(flowTask);

        flowTask.execute();
        flowTaskAggregate.save(flowTask);

        // 3. 获取流程的开始节点配置
        VersionFlowNodeConfig startNodeConfig = versionFlowConfig.getStartNodeConfig();
        if (null == startNodeConfig) {
            flowTask.failed(null, TaskFailedReasonEnum.FLOW_START_NODE_NOT_EXIST.getValue());
            flowTaskAggregate.save(flowTask);
            return;
        }

        runFlow(taskUnit, flowTask, startNodeConfig);
    }

    private void runFlow(FlowTaskUnit taskUnit, FlowTask flowTask, VersionFlowNodeConfig nodeConfig) {
        // 4. 创建流程节点任务
        FlowNodeTaskUnit flowNodeTaskUnit = new FlowNodeTaskUnit(taskUnit.bizId(), flowTask.getFlowTaskNo(), nodeConfig);
        flowNodeTaskRunner.run(flowNodeTaskUnit);

        List<VersionFlowLineConfig> versionFlowLineConfigs = nodeConfig.getNextLines();
        if (CollectionUtils.isEmpty(versionFlowLineConfigs)) {
            return;
        }

        // 5. 获取节点的边配置列表，遍历节点边配置列表
        for (VersionFlowLineConfig lineConfig : versionFlowLineConfigs) {
            // 6. 创建流程边任务
            FlowLineTask flowLineTask = new FlowLineTask(flowTask.getFlowTaskNo(), lineConfig.getId(), taskUnit.bizId());
            flowLineTask.init();
            flowTaskAggregate.saveFlowLineTask(flowLineTask);

            // 7. 执行边表达式，并更新流程边任务的状态和结果，如果结果为 true，则继续执行接下来的流程节点任务
            boolean result = ExpressionUtil.eval(lineConfig.getContent(), globalCache.getDataCache(taskUnit.bizId()));
            flowLineTask.success(String.valueOf(result));
            flowTaskAggregate.saveFlowLineTask(flowLineTask);
            if (!result) {
                continue;
            }

            VersionFlowNodeConfig nextNodeConfig = lineConfig.getNextNode();
            if (null != nextNodeConfig) {
                runFlow(taskUnit, flowTask, nextNodeConfig);
            }
        }
    }

    @Override
    public void callback(FlowTaskUnit taskUnit) {


    }
}
