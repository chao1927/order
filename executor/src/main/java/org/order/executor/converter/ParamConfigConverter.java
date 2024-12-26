package org.order.executor.converter;

import org.order.common.enums.ResultTypeEnum;
import org.order.domain.entity.version.VersionParam;
import org.order.executor.cache.domain.VersionParamConfig;

/**
 * 变量相关转换
 *
 * @author chaobo
 * @date 2024/12/26
 */
public class ParamConfigConverter {


    public static VersionParamConfig convertVersionParamConfig(VersionParam versionParam) {
        return new VersionParamConfig(
                versionParam.getId(),
                versionParam.getParamId(),
                versionParam.getName(),
                versionParam.getExpression(),
                ResultTypeEnum.getByCode(versionParam.getResultType()),
                versionParam.getVersion()
        );
    }


}
