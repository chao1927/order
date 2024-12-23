package org.order.executor.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.ResultTypeEnum;
import org.order.executor.cache.config.IdAndVersion;

/**
 * 用于缓存的变量配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionParamConfig implements IdAndVersion {

    private Long id;

    private Long paramId;

    private String name;

    private String description;

    private String expression;

    private ResultTypeEnum resultType;

    private Integer version;

    @Override
    public Long id() {
        return paramId;
    }

    @Override
    public Integer version() {
        return version;
    }
}
