package org.order.application.command.version.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发布版本入口
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveVersionEntryCommand {

    private Long entryId;

    private Integer version;

}
