package org.order.domain.repository.biz;

import org.order.domain.entity.biz.BizInfo;
import org.order.domain.repository.BaseRepository;

import java.util.Optional;

/**
 * 业务信息仓储接口
 *
 * @author chaobo
 * @date 2024/11/12
 */
public interface BizInfoRepository extends BaseRepository<BizInfo, Long> {

    Optional<BizInfo> findByOrderNo(String orderNo);

}
