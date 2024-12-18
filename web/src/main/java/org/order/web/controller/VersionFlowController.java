package org.order.web.controller;

import org.order.application.command.version.flow.*;
import org.order.common.Result;
import org.order.domain.entity.Flow;
import org.order.web.domain.version.flow.ActiveVersionFlowForm;
import org.order.web.domain.version.flow.DeleteVersionFlowForm;
import org.order.web.domain.version.flow.InactiveVersionFlowForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 版本流程
 *
 * @author chaobo
 * @date 2024/11/16
 */
@RequestMapping("/version/flow")
@RestController
public class VersionFlowController {

    @Autowired
    private DeleteVersionFlowCommandHandler deleteVersionFlowCommandHandler;

    @Autowired
    private ActiveVersionFlowCommandHandler activeVersionFlowCommandHandler;

    @Autowired
    private InactiveVersionFlowCommandHandler inactiveVersionFlowCommandHandler;


    // 删除版本流程
    @DeleteMapping
    public Result<Flow> delete(@RequestBody DeleteVersionFlowForm form) {
        deleteVersionFlowCommandHandler.handle(new DeleteVersionFlowCommand(form.getFlowId(), form.getVersion()));
        return Result.success();
    }

    // 发布版本流程
    @PutMapping("/active/{id}")
    public Result<Flow> active(@RequestBody ActiveVersionFlowForm form) {
        activeVersionFlowCommandHandler.handle(new ActiveVersionFlowCommand(form.getFlowId(), form.getVersion()));
        return Result.success();
    }

    // 下架版本流程
    @PutMapping("/inactive/{id}")
    public Result<Flow> inactive(@RequestBody InactiveVersionFlowForm form) {
        inactiveVersionFlowCommandHandler.handle(new InactiveVersionFlowCommand(form.getFlowId(), form.getVersion()));
        return Result.success();
    }


}
