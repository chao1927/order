package org.order.web.controller;

import org.order.application.command.action.*;
import org.order.common.Result;
import org.order.domain.entity.Action;
import org.order.web.domain.action.CreateActionForm;
import org.order.web.domain.action.UpdateActionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * action 接口
 *
 * @author chaobo
 * @date 2024/11/16
 */
@RequestMapping("/action")
@RestController
public class ActionController {


    @Autowired
    private CreateActionCommandHandler createActionCommandHandler;

    @Autowired
    private UpdateActionCommandHandler updateActionCommandHandler;

    @Autowired
    private DeleteActionCommandHandler deleteActionCommandHandler;

    @Autowired
    private ActiveActionCommandHandler activeActionCommandHandler;



    /**
     * 创建 Action
     * @param form
     * @return
     */
    @PostMapping
    public Result<Action> create(@RequestBody CreateActionForm form) {
        createActionCommandHandler.handle(new CreateActionCommand(form.getName(), form.getDescription()));
        return Result.success();
    }

    // 修改 Action
    @PutMapping
    public Result<Action> update(@RequestBody UpdateActionForm form) {
        updateActionCommandHandler.handle(new UpdateActionCommand(form.getId(), form.getName(), form.getDescription()));
        return Result.success();
    }

    // 删除 Action
    @DeleteMapping("/{id}")
    public Result<Action> delete(@PathVariable Long id) {
        deleteActionCommandHandler.handle(new DeleteActionCommand(id));
        return Result.success();
    }

    /**
     * 发布 Action
     * @param id
     * @return
     */
    @PutMapping("/active/{id}")
    public Result<Action> active(@PathVariable Long id) {
        activeActionCommandHandler.handle(new ActiveActionCommand(id));
        return Result.success();
    }


}
