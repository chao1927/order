package org.order.executor.task.entry;

import lombok.Data;
import org.order.executor.task.TaskUnit;

import java.util.List;

/**
 * 入口任务执行单元
 *
 * @author chaobo
 * @date 2024/12/23
 */
@Data
public class EntryTaskUnit implements TaskUnit {

    private final String bizId;

    public EntryTaskUnit(String bizId) {
        this.bizId = bizId;
    }

    @Override
    public String bizId() {
        return bizId;
    }

}
