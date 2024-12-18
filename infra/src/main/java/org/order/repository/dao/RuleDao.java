package org.order.repository.dao;

import org.order.domain.entity.Rule;
import org.order.domain.entity.version.VersionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 规则
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Repository
public interface RuleDao extends JpaRepository<Rule, Long>, NameDao<Rule> {

}
