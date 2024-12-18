package org.order.web.controller;

import org.order.application.command.flow.*;
import org.order.common.Result;
import org.order.domain.entity.Flow;
import org.order.web.converter.flow.CreateFlowConverter;
import org.order.web.converter.flow.UpdateFlowConverter;
import org.order.web.domain.flow.CreateFlowForm;
import org.order.web.domain.flow.UpdateFlowForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 流程
 *
 * @author chaobo
 * @date 2024/11/16
 */
@RequestMapping("/flow")
@RestController
public class FlowController {

    @Autowired
    private CreateFlowCommandHandler createFlowCommandHandler;

    @Autowired
    private UpdateFlowCommandHandler updateFlowCommandHandler;

    @Autowired
    private DeleteFlowCommandHandler deleteFlowCommandHandler;

    @Autowired
    private ActiveFlowCommandHandler activeFlowCommandHandler;


    @Autowired
    private CreateFlowConverter createFlowConverter;

    @Autowired
    private UpdateFlowConverter updateFlowConverter;


    // 创建流程
    @PostMapping
    public Result<Flow> create(@RequestBody CreateFlowForm form) {
        CreateFlowCommand command = createFlowConverter.convert(form);
        createFlowCommandHandler.handle(command);
        return Result.success();
    }

    // 修改流程
    @PutMapping
    public Result<Flow> update(@RequestBody UpdateFlowForm form) {
        UpdateFlowCommand command = updateFlowConverter.convert(form);
        updateFlowCommandHandler.handle(command);
        return Result.success();
    }

    // 删除流程
    @DeleteMapping("/{id}")
    public Result<Flow> delete(@PathVariable Long id) {
        deleteFlowCommandHandler.handle(new DeleteFlowCommand(id));
        return Result.success();
    }

    // 发布流程
    @PutMapping("/active/{id}")
    public Result<Flow> active(@PathVariable Long id) {
        activeFlowCommandHandler.handle(new ActiveFlowCommand(id));
        return Result.success();
    }

}
