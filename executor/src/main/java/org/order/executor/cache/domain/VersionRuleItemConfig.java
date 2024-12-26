package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.RuleItemTypeEnum;

/**
 * 版本规则子项配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionRuleItemConfig {

    private Long id;

    private Long versionRuleId;

    private Integer sort;

    private String name;

    private RuleItemTypeEnum type;

    private Long refId;

    private Integer refVersion;

    private VersionParamConfig paramConfig;

    public VersionRuleItemConfig(Long id, Long versionRuleId, Integer sort, String name, RuleItemTypeEnum type, Long refId, Integer refVersion) {
        this.id = id;
        this.versionRuleId = versionRuleId;
        this.sort = sort;
        this.name = name;
        this.type = type;
        this.refId = refId;
        this.refVersion = refVersion;
    }
}
