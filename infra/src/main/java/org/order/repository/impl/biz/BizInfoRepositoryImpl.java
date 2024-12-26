package org.order.repository.impl.biz;

import org.order.domain.entity.biz.BizInfo;
import org.order.domain.repository.biz.BizInfoRepository;
import org.order.repository.dao.biz.BizInfoDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 业务仓储实现类
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Component
public class BizInfoRepositoryImpl extends BaseRepositoryImpl<BizInfo, Long> implements BizInfoRepository {

    @Autowired
    private BizInfoDao bizInfoDao;

    @Override
    public void save(BizInfo bizInfo) {
        bizInfoDao.save(bizInfo);
    }

    @Override
    public Optional<BizInfo> findById(Long id) {
        return bizInfoDao.findById(id);
    }

    @Override
    public Optional<BizInfo> findByOrderNo(String orderNo) {
        return bizInfoDao.findByOrderNo(orderNo);
    }
}
