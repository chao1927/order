package org.order.web.domain.version.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除版本入口
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteVersionEntryForm {

    private Long entryId;

    private Integer version;

}
