package org.order.executor.task.entry;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.TaskTypeEnum;
import org.order.domain.aggregate.task.EntryTaskAggregate;
import org.order.domain.entity.task.EntryTask;
import org.order.executor.cache.config.ConfigCacheManager;
import org.order.executor.cache.data.GlobalCache;
import org.order.executor.cache.domain.VersionEntryConfig;
import org.order.executor.task.TaskRunner;
import org.order.executor.task.FlowTaskExecutor;
import org.order.executor.task.flow.FlowTaskUnit;
import org.order.executor.utils.ExpressionUtil;
import org.order.executor.utils.TaskNoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 入口任务执行器
 *
 * @author chaobo
 * @date 2024/12/25
 */
@Component
public class EntryTaskRunner implements TaskRunner<EntryTaskUnit> {


    @Autowired
    private EntryTaskAggregate entryTaskAggregate;

    @Autowired
    private FlowTaskExecutor flowTaskExecutor;

    @Autowired
    private ConfigCacheManager configCacheManager;

    @Autowired
    private GlobalCache globalCache;

    @Override
    public void run(EntryTaskUnit taskUnit) {
        // 入口运行流程

        // 1. 查询所有的入口配置，如果没有配置的话，则直接返回
        List<VersionEntryConfig> entryConfigs = configCacheManager.getAllEntryConfigList();
        if (CollectionUtils.isEmpty(entryConfigs)) {
            return;
        }

        // 2. 根据 bizId 加载业务数据到全局缓存中
        globalCache.loadDataCache(taskUnit.bizId());

        // 3. 遍历入口配置
        for (VersionEntryConfig config : entryConfigs) {
            // 4. 保存入口执行记录和执行结果，作为入口任务 entryTask, 设置入口任务状态为待执行
            EntryTask entryTask = new EntryTask(
                    TaskNoGenerator.generate(TaskTypeEnum.Entry),
                    config.getId(),
                    config.getEntryId(),
                    config.getVersion(),
                    taskUnit.bizId()
            );
            entryTask.init();
            entryTaskAggregate.save(entryTask);

            // 5. 设置入口任务执行状态为执行中
            entryTask.execute();
            entryTaskAggregate.save(entryTask);

            // 6. 执行入口配置的表达式
            boolean result = ExpressionUtil.eval(config.getExpression(), globalCache.getDataCache(taskUnit.bizId()));
            if (result) {
                // 7. 如果结果返回 true，则根据入口配置对应的流程id, 流程版本号生成流程任务, 提交流程任务到流程任务执行器
                FlowTaskUnit flowTaskUnit = new FlowTaskUnit(
                        taskUnit.bizId(),
                        config.getFlowId(),
                        config.getFlowVersion(),
                        entryTask.getEntryTaskNo()
                        // todo 回调函数绑定
                        // 8. 注册回调函数，回调函数会在入口任务提交的流程任务执行完成后，执行回调函数
                );
                flowTaskExecutor.submitTask(flowTaskUnit);
            }

            // 9. 修改入口任务状态为执行完成
            entryTask.success(String.valueOf(result));
            entryTaskAggregate.save(entryTask);
        }
    }


    @Override
    public void callback(EntryTaskUnit taskUnit) {
        // 清理缓存的业务数据
        globalCache.removeDataCache(taskUnit.bizId());
    }
}
