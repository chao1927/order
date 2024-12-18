package org.order.web.controller;

import org.order.application.command.param.*;
import org.order.domain.entity.Param;
import org.order.domain.entity.version.VersionParam;
import org.order.web.domain.param.CreateParamForm;
import org.order.web.domain.param.UpdateParamForm;
import org.order.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chaobo
 * @date 2024/11/9 15:31
 */
@RestController
@RequestMapping("/param")
public class ParamController {

    @Autowired
    private CreateParamCommandHandler createParamCommandHandler;

    @Autowired
    private UpdateParamCommandHandler updateParamCommandHandler;

    @Autowired
    private ActiveParamCommandHandler activeParamCommandHandler;

    @Autowired
    private DeleteParamCommandHandler deleteParamCommandHandler;

    @PostMapping
    public Result<Param> create(@RequestBody CreateParamForm form) {
        createParamCommandHandler.handle(new CreateParamCommand(
                form.getName(),
                form.getDescription(),
                form.getExpression(),
                form.getResultType()
        ));
        return Result.success();
    }

    @PutMapping
    public Result<Param> update(@RequestBody UpdateParamForm form) {
        updateParamCommandHandler.handle(new UpdateParamCommand(
                form.getId(),
                form.getName(),
                form.getDescription(),
                form.getExpression(),
                form.getResultType()
        ));
        return Result.success();
    }

    @PutMapping("/active/{id}")
    public Result<Param> active(@PathVariable Long id) {
        activeParamCommandHandler.handle(new ActiveParamCommand(id));

        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Param> delete(@PathVariable Long id) {
        deleteParamCommandHandler.handle(new DeleteParamCommand(id));
        return Result.success();
    }


}
