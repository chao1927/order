package org.order.domain.converter;

import org.order.domain.aggregate.BizInfoAggregate;
import org.order.domain.entity.biz.BizInfo;

/**
 * 业务信息 转换器
 *
 * @author chaobo
 * @date 2024/12/25
 */
public class BizInfoConverter {

    public static BizInfoAggregate toAggregate(BizInfo bizInfo) {
        return new BizInfoAggregate(
                bizInfo.getId(),
                bizInfo.getName(),
                bizInfo.getIdCard(),
                bizInfo.getPhone(),
                bizInfo.getBankcard(),
                bizInfo.getOrderNo(),
                bizInfo.getSource(),
                bizInfo.getCreateTime(),
                bizInfo.getUpdateTime(),
                bizInfo.getIsDeleted()
        );
    }

}
