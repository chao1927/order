package org.order.application.command.version.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 激活变量
 *
 * @author chaobo
 * @date 2024/11/9 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveVersionParamCommand {

    private Long paramId;

    private Integer version;

}
