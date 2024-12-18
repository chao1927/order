package org.order.web.controller;

import org.order.application.command.rule.*;
import org.order.domain.entity.Rule;
import org.order.web.converter.rule.CreateRuleConverter;
import org.order.web.converter.rule.UpdateRuleConverter;
import org.order.web.domain.rule.CreateRuleForm;
import org.order.web.domain.rule.UpdateRuleForm;
import org.order.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chaobo
 * @date 2024/11/9 15:31
 */
@RestController
@RequestMapping("/rule")
public class RuleController {

    // command handlers ...

    @Autowired
    private CreateRuleCommandHandler createRuleCommandHandler;

    @Autowired
    private UpdateRuleCommandHandler updateRuleCommandHandler;

    @Autowired
    private ActiveRuleCommandHandler activeRuleCommandHandler;

    @Autowired
    private DeleteRuleCommandHandler deleteRuleCommandHandler;

    // converters ..
    @Autowired
    private CreateRuleConverter createRuleConverter;

    @Autowired
    private UpdateRuleConverter updateRuleConverter;


    @PostMapping
    public Result<Rule> create(@RequestBody CreateRuleForm form) {
        createRuleCommandHandler.handle(createRuleConverter.convert(form));
        return Result.success();
    }

    @PutMapping
    public Result<Rule> update(@RequestBody UpdateRuleForm form) {
        updateRuleCommandHandler.handle(updateRuleConverter.convert(form));
        return Result.success();
    }

    @PutMapping("/active/{id}")
    public Result<Rule> active(@PathVariable Long id) {
        activeRuleCommandHandler.handle(new ActiveRuleCommand(id));
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Rule> delete(@PathVariable Long id) {
        deleteRuleCommandHandler.handle(new DeleteRuleCommand(id));
        return Result.success();
    }


}
