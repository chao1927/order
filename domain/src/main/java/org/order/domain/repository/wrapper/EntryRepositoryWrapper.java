package org.order.domain.repository.wrapper;

import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.entity.Entry;
import org.order.domain.entity.version.VersionEntry;
import org.order.domain.repository.EntryRepository;
import org.order.domain.repository.version.VersionEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 入口仓储接口包装类
 *
 * @author chaobo
 * @date 2024/11/19
 */
@Component
public class EntryRepositoryWrapper {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private VersionEntryRepository versionEntryRepository;

    public Entry findByIdWithEx(Long id) {
       return entryRepository.findById(id).orElseThrow(() -> new CustomBusinessException(ErrorCode.ENTRY_NOT_FOUND));
    }

    public VersionEntry findByEntryIdAndVersion(Long entryId, Integer version) {
        return versionEntryRepository.findByEntryIdAndVersion(entryId, version).orElseThrow(
               () -> new CustomBusinessException(ErrorCode.ENTRY_NOT_FOUND)
       );
    }

    public void checkDuplicateName(String name) {
        entryRepository.findByName(name).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.ENTRY_NAME_DUPLICATE)
        );
    }

    public void checkDuplicateName(String name, Long id) {
        entryRepository.findByNameAndIdNot(name, id).orElseThrow(
                () -> new CustomBusinessException(ErrorCode.ENTRY_NAME_DUPLICATE)
        );
    }

    public void checkEntryExist(Long id) {
        entryRepository.findById(id).orElseThrow(() -> new CustomBusinessException(ErrorCode.ENTRY_NOT_FOUND));
    }

    /******************************** 校验流程与入口的引用关系 ************************************************/
    public void checkRefByEntry(Long flowId) {
        Optional<List<Entry>> entriesOp = entryRepository.findByFlowId(flowId);
        checkResult(entriesOp);
    }

    public void checkRefByEntry(Long flowId, Integer flowVersion) {
        Optional<List<Entry>> entriesOp = entryRepository.findByFlowIdAndFlowVersion(flowId, flowVersion);
        checkResult(entriesOp);
    }

    public void checkRefByVersionEntry(Long flowId) {
        Optional<List<VersionEntry>> entriesOp = versionEntryRepository.findByFlowId(flowId);
        checkResult(entriesOp);
    }

    public void checkRefByVersionEntry(Long flowId, Integer flowVersion) {
        Optional<List<VersionEntry>> entriesOp = versionEntryRepository.findByFlowIdAndFlowVersion(flowId, flowVersion);
        checkResult(entriesOp);
    }

    public void checkRefByActiveVersionEntry(Long flowId, Integer flowVersion) {
        Optional<List<VersionEntry>> entriesOp = versionEntryRepository.findByFlowIdAndFlowVersionAndStatus(flowId, flowVersion, StatusEnum.ACTIVATED.getCode());
        checkResult(entriesOp);
    }

    private <T> void checkResult(Optional<List<T>> nodesOp) {
        if (nodesOp.isPresent() && CollectionUtils.isNotEmpty(nodesOp.get())) {
            throw new CustomBusinessException(ErrorCode.FLOW_REFERENCE_BY_ENTRY);
        }
    }


}
