package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.FlowNodeTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于缓存的版本流程节点配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionFlowNodeConfig {

    private Long id;

    private Long versionFlowId;

    private String name;

    private String description;

    private FlowNodeTypeEnum type;

    private String content;

    private Long refId;

    private Integer refVersion;

    private VersionFlowLineConfig preLine;

    private List<VersionFlowLineConfig> nextLines;


    public VersionFlowNodeConfig(Long id, Long versionFlowId, String name, String description, FlowNodeTypeEnum type, String content, Long refId, Integer refVersion) {
        this.id = id;
        this.versionFlowId = versionFlowId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.content = content;
        this.refId = refId;
        this.refVersion = refVersion;
    }

    public boolean isStartNode() {
        return FlowNodeTypeEnum.START.equals(type);
    }

    public void addFlowLine(VersionFlowLineConfig flowLine) {
        if (null == nextLines) {
            this.nextLines = new ArrayList<>();
        }

        this.nextLines.add(flowLine);
    }

}
