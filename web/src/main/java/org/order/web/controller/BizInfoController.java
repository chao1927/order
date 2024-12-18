package org.order.web.controller;

import org.order.web.converter.biz.CreateBizInfoConverter;
import org.order.web.domain.biz.CreateBizInfoForm;
import org.order.common.Result;
import org.order.application.command.biz.CreateBizInfoCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务数据相关接口
 *
 * @author chaobo
 * @date 2024/11/12
 */
@RestController
@RequestMapping("/biz/info")
public class BizInfoController {


    @Autowired
    private CreateBizInfoCommandHandler createBizInfoCommandHandler;

    @Autowired
    private CreateBizInfoConverter createBizInfoConverter;

    /**
     * 新增业务数据
     * Create
     */
    @PostMapping
    public Result<Object> create(@RequestBody CreateBizInfoForm form) {
        createBizInfoCommandHandler.handle(createBizInfoConverter.convert(form));
        return Result.success();
    }



}
