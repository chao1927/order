package org.order.executor.converter;

import org.order.domain.entity.version.VersionAction;
import org.order.executor.cache.domain.VersionActionConfig;

/**
 * action 配置转换器
 *
 * @author chaobo
 * @date 2024/12/26
 */
public class ActionConfigConverter {

    public static VersionActionConfig convertVersionActionConfig(VersionAction versionAction) {
        return new VersionActionConfig(
                versionAction.getId(),
                versionAction.getActionId(),
                versionAction.getName(),
                versionAction.getVersion()
        );
    }

}
