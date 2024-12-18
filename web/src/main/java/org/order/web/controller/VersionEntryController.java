package org.order.web.controller;

import org.order.application.command.version.entry.*;
import org.order.common.Result;
import org.order.domain.entity.Entry;
import org.order.web.domain.version.entry.ActiveVersionEntryForm;
import org.order.web.domain.version.entry.DeleteVersionEntryForm;
import org.order.web.domain.version.entry.InactiveVersionEntryFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 入口
 *
 * @author chaobo
 * @date 2024/11/16
 */
@RequestMapping("/version/entry")
@RestController
public class VersionEntryController {


    @Autowired
    private DeleteVersionEntryCommandHandler deleteVersionEntryCommandHandler;

    @Autowired
    private ActiveVersionEntryCommandHandler activeVersionEntryCommandHandler;

    @Autowired
    private InactiveVersionEntryCommandHandler inactiveVersionEntryCommandHandler;


    // 删除入口
    @DeleteMapping
    public Result<Entry> delete(@RequestBody DeleteVersionEntryForm form) {
        deleteVersionEntryCommandHandler.handle(new DeleteVersionEntryCommand(form.getEntryId(), form.getVersion()));
        return Result.success();
    }

    // 发布入口
    @PutMapping("/active")
    public Result<Entry> active(@RequestBody ActiveVersionEntryForm form) {
        activeVersionEntryCommandHandler.handle(new ActiveVersionEntryCommand(form.getEntryId(), form.getVersion()));
        return Result.success();
    }

    // 下架入口
    @PutMapping("/inactive")
    public Result<Entry> inactive(@RequestBody InactiveVersionEntryFrom form) {
        inactiveVersionEntryCommandHandler.handle(new InactiveVersionEntryCommand(form.getEntryId(), form.getVersion()));
        return Result.success();
    }

}
