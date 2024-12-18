package org.order.web.domain.version.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chaobo
 * @date 2024/11/9 17:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveVersionParamForm {

    private Long paramId;

    private Integer version;

}
