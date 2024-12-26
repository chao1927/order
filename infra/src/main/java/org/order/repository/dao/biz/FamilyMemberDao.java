package org.order.repository.dao.biz;

import org.order.domain.entity.biz.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 业务数据-家庭成员仓储 jpa
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Repository
public interface FamilyMemberDao extends JpaRepository<FamilyMember, Long> {

    Optional<List<FamilyMember>> findByBizInfoId(Long bizInfoId);

}
