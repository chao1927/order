package org.order.repository.dao.biz;

import org.order.domain.entity.biz.BizInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Repository
public interface BizInfoDao extends JpaRepository<BizInfo, Long> {
}
