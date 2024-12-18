package org.order.web.controller;

import org.order.application.command.version.param.*;
import org.order.domain.entity.version.VersionParam;
import org.order.web.domain.version.param.ActiveVersionParamForm;
import org.order.web.domain.version.param.DeleteVersionParamForm;
import org.order.web.domain.version.param.InactiveVersionParamForm;
import org.order.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chaobo
 * @date 2024/11/9 15:31
 */
@RestController
@RequestMapping("/version/param")
public class VersionParamController {

    @Autowired
    private ActiveVersionParamCommandHandler activeVersionParamCommandHandler;

    @Autowired
    private InactiveVersionParamCommandHandler inactiveVersionParamCommandHandler;

    @Autowired
    private DeleteVersionParamCommandHandler deleteVersionParamCommandHandler;


    @PutMapping("/active")
    public Result<VersionParam> active(@RequestBody ActiveVersionParamForm form) {
        activeVersionParamCommandHandler.handle(new ActiveVersionParamCommand(
                form.getParamId(),
                form.getVersion()
        ));
        return Result.success();
    }

    @PutMapping("/inactive")
    public Result<VersionParam> inactive(@RequestBody InactiveVersionParamForm form) {
        inactiveVersionParamCommandHandler.handle(new InactiveVersionParamCommand(
                form.getParamId(),
                form.getVersion()
        ));
        return Result.success();
    }

    @DeleteMapping
    public Result<VersionParam> delete(@RequestBody DeleteVersionParamForm form) {
        deleteVersionParamCommandHandler.handle(new DeleteVersionParamCommand(
                form.getParamId(),
                form.getVersion()
        ));
        return Result.success();
    }


}
