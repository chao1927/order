package org.order.application.command.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除变量
 *
 * @author chaobo
 * @date 2024/11/9 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteParamCommand {

    private Long id;

}
