package org.order.application.command.version.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下架版本入口 命令
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactiveVersionEntryCommand {

    private Long entryId;

    private Integer version;

}
