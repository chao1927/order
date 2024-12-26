package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.executor.cache.config.IdAndVersion;

/**
 * 用于缓存的 action 配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionActionConfig implements IdAndVersion {

    private Long id;

    private Long actionId;

    private String name;

    private Integer version;

    @Override
    public Long id() {
        return actionId;
    }

    @Override
    public Integer version() {
        return version;
    }
}
