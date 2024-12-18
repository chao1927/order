package org.order.web.controller;

import org.order.application.command.entry.*;
import org.order.common.Result;
import org.order.domain.entity.Entry;
import org.order.web.domain.entry.CreateEntryForm;
import org.order.web.domain.entry.UpdateEntryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 入口
 *
 * @author chaobo
 * @date 2024/11/16
 */
@RequestMapping("/entry")
@RestController
public class EntryController {

    @Autowired
    private CreateEntryCommandHandler createEntryCommandHandler;

    @Autowired
    private UpdateEntryCommandHandler updateEntryCommandHandler;

    @Autowired
    private DeleteEntryCommandHandler deleteEntryCommandHandler;

    @Autowired
    private ActiveEntryCommandHandler activeEntryCommandHandler;


    // 创建入口
    @PostMapping
    public Result<Entry> create(@RequestBody CreateEntryForm form) {
        createEntryCommandHandler.handle(new CreateEntryCommand(
                form.getName(),
                form.getDescription(),
                form.getExpression(),
                form.getFlowId(),
                form.getFlowVersion()
        ));
        return Result.success();
    }

    // 更新入口
    @PutMapping
    public Result<Entry> update(@RequestBody UpdateEntryForm form) {
        updateEntryCommandHandler.handle(new UpdateEntryCommand(
                form.getId(),
                form.getName(),
                form.getDescription(),
                form.getExpression(),
                form.getFlowId(),
                form.getFlowVersion()
        ));
        return Result.success();
    }

    // 删除入口
    @DeleteMapping("/{id}")
    public Result<Entry> delete(@PathVariable Long id) {
        deleteEntryCommandHandler.handle(new DeleteEntryCommand(id));
        return Result.success();
    }

    // 发布入口
    @PutMapping("/active/{id}")
    public Result<Entry> active(@PathVariable Long id) {
        activeEntryCommandHandler.handle(new ActiveEntryCommand(id));
        return Result.success();
    }


}
