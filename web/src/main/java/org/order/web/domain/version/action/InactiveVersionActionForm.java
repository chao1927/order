package org.order.web.domain.version.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下架版本 action 表单
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactiveVersionActionForm {

    private Long actionId;

    private Integer version;

}
