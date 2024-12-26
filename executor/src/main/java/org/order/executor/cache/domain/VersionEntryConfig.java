package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.executor.cache.config.IdAndVersion;

import java.util.List;

/**
 * 版本入口配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionEntryConfig implements IdAndVersion {

    private Long id;

    private Long entryId;

    private Integer version;

    private String name;

    private String expression;

    private Long flowId;

    private Integer flowVersion;

    private VersionFlowConfig flowConfig;

    @Override
    public Long id() {
        return entryId;
    }

    @Override
    public Integer version() {
        return version;
    }

    public VersionEntryConfig(Long id, Long entryId, Integer version, String name, String expression, Long flowId, Integer flowVersion) {
        this.id = id;
        this.entryId = entryId;
        this.version = version;
        this.name = name;
        this.expression = expression;
        this.flowId = flowId;
        this.flowVersion = flowVersion;
    }
}
