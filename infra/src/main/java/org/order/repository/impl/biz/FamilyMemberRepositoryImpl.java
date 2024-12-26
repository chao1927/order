package org.order.repository.impl.biz;

import org.order.domain.entity.biz.FamilyMember;
import org.order.domain.repository.biz.FamilyMemberRepository;
import org.order.repository.dao.biz.FamilyMemberDao;
import org.order.repository.impl.common.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 业务数据-家庭成员仓储接口实现
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Component
public class FamilyMemberRepositoryImpl extends BaseRepositoryImpl<FamilyMember, Long> implements FamilyMemberRepository {

    @Autowired
    private FamilyMemberDao familyMemberDao;

    @Override
    public Optional<List<FamilyMember>> findByBizInfoId(Long bizInfoId) {
        return familyMemberDao.findByBizInfoId(bizInfoId);
    }
}
