package org.order.application.command.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberCommand {

    private String identity;

    private String name;

    private String job;

}
