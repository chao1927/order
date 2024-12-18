package org.order.web.controller;

import org.order.application.command.version.rule.*;
import org.order.domain.entity.version.VersionRule;
import org.order.web.domain.version.rule.ActiveVersionRuleForm;
import org.order.web.domain.version.rule.DeleteVersionRuleForm;
import org.order.web.domain.version.rule.InactiveVersionRuleForm;
import org.order.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chaobo
 * @date 2024/11/9 15:31
 */
@RestController
@RequestMapping("/version/rule")
public class VersionRuleController {


    @Autowired
    private ActiveVersionRuleCommandHandler activeVersionRuleCommandHandler;

    @Autowired
    private InactiveVersionRuleCommandHandler inactiveVersionRuleCommandHandler;

    @Autowired
    private DeleteVersionRuleCommandHandler deleteVersionRuleCommandHandler;


    @PutMapping("/active")
    public Result<VersionRule> active(@RequestBody ActiveVersionRuleForm form) {
        activeVersionRuleCommandHandler.handle(new ActiveVersionRuleCommand(
                form.getRuleId(),
                form.getVersion()
        ));
        return Result.success();
    }

    @PutMapping("/inactive")
    public Result<VersionRule> inactive(@RequestBody InactiveVersionRuleForm form) {
        inactiveVersionRuleCommandHandler.handle(new InactiveVersionRuleCommand(
                form.getRuleId(),
                form.getVersion()
        ));
        return Result.success();
    }

    @DeleteMapping
    public Result<VersionRule> delete(@RequestBody DeleteVersionRuleForm form) {
        deleteVersionRuleCommandHandler.handle(new DeleteVersionRuleCommand(
                form.getRuleId(),
                form.getVersion()
        ));
        return Result.success();
    }


}
