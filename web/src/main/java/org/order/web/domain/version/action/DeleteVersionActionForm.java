package org.order.web.domain.version.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除版本 action
 *
 * @author chaobo
 * @date 2024/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteVersionActionForm {

    private Long actionId;

    private Integer version;

}
