package org.order.application.command.version.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下架变量
 *
 * @author chaobo
 * @date 2024/11/9 17:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactiveVersionParamCommand {

    private Long paramId;

    private Integer version;

}
