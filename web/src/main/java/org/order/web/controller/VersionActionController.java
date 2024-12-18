package org.order.web.controller;

import org.order.application.command.version.action.*;
import org.order.common.Result;
import org.order.domain.entity.Action;
import org.order.web.domain.version.action.ActionVersionActionForm;
import org.order.web.domain.version.action.DeleteVersionActionForm;
import org.order.web.domain.version.action.InactiveVersionActionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 版本 action
 *
 * @author chaobo
 * @date 2024/11/16
 */
@RequestMapping("/version/action")
@RestController
public class VersionActionController {

    @Autowired
    private ActionVersionActionCommandHandler actionVersionActionCommandHandler;


    @Autowired
    private InactiveVersionActionCommandHandler inactiveVersionActionCommandHandler;

    @Autowired
    private DeleteVersionActionCommandHandler deleteVersionActionCommandHandler;



    // 发布版本 Action
    @PutMapping("/active")
    public Result<Action> active(@RequestBody ActionVersionActionForm form) {
        actionVersionActionCommandHandler.handle(new ActionVersionActionCommand(form.getActionId(), form.getVersion()));
        return Result.success();
    }

    // 下架版本 Action
    @PutMapping("/inactive")
    public Result<Action> inactive(@RequestBody InactiveVersionActionForm form) {
        inactiveVersionActionCommandHandler.handle(new InactiveVersionActionCommand(form.getActionId(), form.getVersion()));
        return Result.success();
    }

    // 删除版本 Action
    @DeleteMapping
    public Result<Action> delete(@RequestBody DeleteVersionActionForm form) {
        deleteVersionActionCommandHandler.handle(new DeleteVersionActionCommand(form.getActionId(), form.getVersion()));
        return Result.success();
    }


}
