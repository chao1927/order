package org.order.domain.repository.biz;

import org.order.domain.entity.biz.FamilyMember;
import org.order.domain.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 业务数据-家庭成员仓储接口定义
 *
 * @author chaobo
 * @date 2024/11/13
 */
public interface FamilyMemberRepository extends BaseRepository<FamilyMember, Long> {

    Optional<List<FamilyMember>> findByBizInfoId(Long bizInfoId);

}
