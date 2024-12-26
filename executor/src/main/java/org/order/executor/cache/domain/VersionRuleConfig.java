package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.ResultTypeEnum;
import org.order.executor.cache.config.IdAndVersion;

import java.util.List;

/**
 * 用于缓存的规则配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionRuleConfig implements IdAndVersion {

    private Long id;

    private Long ruleId;

    private String name;

    private String description;

    private String expression;

    private ResultTypeEnum resultType;

    private Integer version;

    private List<VersionRuleItemConfig> items;

    @Override
    public Long id() {
        return ruleId;
    }

    @Override
    public Integer version() {
        return version;
    }

    public VersionRuleConfig(Long id, Long ruleId, String name, String description, String expression, ResultTypeEnum resultType, Integer version) {
        this.id = id;
        this.ruleId = ruleId;
        this.name = name;
        this.description = description;
        this.expression = expression;
        this.resultType = resultType;
        this.version = version;
    }
}
