package org.order.application.command.version.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发布版本 action
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionVersionActionCommand {

    private Long actionId;

    private Integer version;

}
