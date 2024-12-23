package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.executor.cache.config.IdAndVersion;

import java.util.List;

/**
 * 用于缓存的流程配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionFlowConfig implements IdAndVersion {

    private Long id;

    private Long flowId;

    private Integer version;

    private String name;

    private String description;

    private List<VersionFlowLineConfig> lines;

    private List<VersionFlowNodeConfig> nodes;

    private VersionFlowNodeConfig startNode;

    public VersionFlowConfig(Long id, Long flowId, Integer version, String name, String description) {
        this.id = id;
        this.flowId = flowId;
        this.version = version;
        this.name = name;
        this.description = description;
    }

    @Override
    public Long id() {
        return flowId;
    }

    @Override
    public Integer version() {
        return version;
    }
}
