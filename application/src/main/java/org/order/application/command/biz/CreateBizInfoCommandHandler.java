package org.order.application.command.biz;

import org.order.application.command.CommandHandler;
import org.order.application.converter.CreateBizInfoCommandConverter;
import org.order.domain.aggregate.BizInfoAggregate;
import org.order.domain.aggregate.BizInfoAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 新增业务数据，处理业务系统推送过来的数据
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Component
public class CreateBizInfoCommandHandler implements CommandHandler<CreateBizInfoCommand> {


    @Autowired
    private CreateBizInfoCommandConverter createBizInfoCommandConverter;

    @Autowired
    private BizInfoAggregateService bizInfoAggregateService;

    @Override
    public void handle(CreateBizInfoCommand command) {
        BizInfoAggregate bizInfoAggregate = createBizInfoCommandConverter.convert(command);
        bizInfoAggregateService.save(bizInfoAggregate);
    }


}
