package org.order.executor.converter;

import org.order.domain.entity.version.VersionEntry;
import org.order.executor.cache.domain.VersionEntryConfig;

/**
 * 入口配置转换器
 *
 * @author chaobo
 * @date 2024/12/26
 */
public class EntryConfigConverter {

    public static VersionEntryConfig convertVersionEntryConfig(VersionEntry versionEntry) {
        return new VersionEntryConfig(
                versionEntry.getId(),
                versionEntry.getEntryId(),
                versionEntry.getVersion(),
                versionEntry.getName(),
                versionEntry.getExpression(),
                versionEntry.getFlowId(),
                versionEntry.getFlowVersion());
    }

}
