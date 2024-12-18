package org.order.domain.repository.wrapper;

import org.order.common.enums.ErrorCode;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Action;
import org.order.domain.entity.version.VersionAction;
import org.order.domain.repository.ActionRepository;
import org.order.domain.repository.version.VersionActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * action 仓储实现包装
 *
 * @author chaobo
 * @date 2024/11/19
 */
@Component
public class ActionRepositoryWrapper {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private VersionActionRepository versionActionRepository;


    /**
     * action 存在则返回 action, 否则抛出异常
     *
     * @param actionId actionId
     * @return action
     */
    public Action findByIdWithEx(Long actionId) {
        return actionRepository.findById(actionId).orElseThrow(() -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND));
    }

    /**
     * 根据 actionId，版本号查询 action, 存在则返回， 否则抛出异常
     *
     * @param actionId actionId
     * @param version 版本号
     * @return 版本 Action
     */
    public VersionAction findByActionIdAndVersionWithEx(Long actionId, Integer version) {
        return versionActionRepository.findByActionIdAndVersion(actionId, version).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND));
    }

    /**
     * 判断是否有指定名称的 Action
     *
     * @param name action 名称
     */
    public void checkDuplicateName(String name) {
        actionRepository.findByName(name).ifPresent(action -> {
            throw new CustomBusinessException(ErrorCode.ACTION_NAME_DUPLICATE);
        });
    }

    /**
     * 修改时，判断是否有除自身外的相同名称的 Action
     *
     * @param name action 名称
     * @param actionId action id
     */
    public void checkDuplicateName(String name, Long actionId) {
        actionRepository.findByNameAndIdNot(name, actionId).ifPresent(action -> {
            throw new CustomBusinessException(ErrorCode.ACTION_NAME_DUPLICATE);
        });
    }


    public void checkActionExist(Long actionId) {
        actionRepository.findById(actionId).orElseThrow(() -> new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND));
    }

    public void checkVersionActionActive(Long actionId, Integer version) {
        Optional<VersionAction> versionActionOp = versionActionRepository.findByActionIdAndVersion(actionId, version);
        if (!versionActionOp.isPresent()) {
            throw new CustomBusinessException(ErrorCode.ACTION_NOT_FOUND);
        }

        if (!versionActionOp.get().isActive()) {
            throw new CustomBusinessException(ErrorCode.ACTION_NOT_ACTIVE);
        }
    }

}
