package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 缓存的版本流程边配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionFlowLineConfig {

    private Long id;

    private Long versionFlowId;

    private Long preNodeId;

    private String preNodeName;

    private Long nextNodeId;

    private String nextNodeName;

    private String content;

    private VersionFlowNodeConfig preNode;

    private VersionFlowNodeConfig nextNode;

    public VersionFlowLineConfig(Long id, Long versionFlowId, Long preNodeId, String preNodeName, Long nextNodeId, String nextNodeName, String content) {
        this.id = id;
        this.versionFlowId = versionFlowId;
        this.preNodeId = preNodeId;
        this.preNodeName = preNodeName;
        this.nextNodeId = nextNodeId;
        this.nextNodeName = nextNodeName;
        this.content = content;
    }
}
