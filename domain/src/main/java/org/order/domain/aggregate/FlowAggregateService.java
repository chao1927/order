package org.order.domain.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.order.common.enums.ErrorCode;
import org.order.common.enums.StatusEnum;
import org.order.common.exception.CustomBusinessException;
import org.order.domain.converter.FlowConverter;
import org.order.domain.entity.Flow;
import org.order.domain.entity.FlowLine;
import org.order.domain.entity.FlowNode;
import org.order.domain.entity.version.VersionFlow;
import org.order.domain.entity.version.VersionFlowLine;
import org.order.domain.entity.version.VersionFlowNode;
import org.order.domain.event.ConfigChangeDomainEvent;
import org.order.domain.event.DomainEventEnum;
import org.order.domain.event.DomainEventPublisher;
import org.order.domain.event.VersionConfigChangeDomainEvent;
import org.order.domain.repository.EntryRepository;
import org.order.domain.repository.FlowLineRepository;
import org.order.domain.repository.FlowNodeRepository;
import org.order.domain.repository.FlowRepository;
import org.order.domain.repository.version.VersionEntryRepository;
import org.order.domain.repository.version.VersionFlowLineRepository;
import org.order.domain.repository.version.VersionFlowNodeRepository;
import org.order.domain.repository.version.VersionFlowRepository;
import org.order.domain.validator.FlowValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流程聚合服务
 *
 * @author chaobo
 * @date 2024/11/18
 */
@Slf4j
@Component
public class FlowAggregateService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private VersionEntryRepository versionEntryRepository;

    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private FlowNodeRepository flowNodeRepository;

    @Autowired
    private FlowLineRepository flowLineRepository;

    @Autowired
    private VersionFlowRepository versionFlowRepository;

    @Autowired
    private VersionFlowNodeRepository versionFlowNodeRepository;

    @Autowired
    private VersionFlowLineRepository versionFlowLineRepository;

    @Autowired
    private FlowValidator flowValidator;

    @Autowired
    private DomainEventPublisher domainEventPublisher;


    @Transactional(rollbackFor = Exception.class)
    public void create(FlowAggregate flowAgg) {
        // 查询流程名称是否重复
        flowRepository.checkDuplicateName(flowAgg.getName());

        // 新增流程
        Flow flow = new Flow(flowAgg.getName(), flowAgg.getDescription());
        // 初始化版本号
        flow.initVersion();
        flowRepository.save(flow);

        // 保存流程节点
        flowNodeRepository.saveAll(FlowConverter.convertNewFlowNodes(flow.getId(), flowAgg.getNodes()));

        // 保存流程边
        flowLineRepository.saveAll(FlowConverter.convertNewFlowLines(flow.getId(), flowAgg.getLines()));

        // 发送创建流程领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.FLOW_CREATED_EVENT, flow.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(FlowAggregate flowAgg) {
        // 查询并判断流程是否存在
        Flow flow = flowRepository.findByIdWithEx(flowAgg.getId());

        // 查询流程名称是否重复
        flowRepository.checkDuplicateName(flowAgg.getName(), flowAgg.getId());

        // 修改流程
        flow.change(flowAgg.getName(), flowAgg.getDescription());
        flowRepository.save(flow);

        // 更新流程节点
        flowNodeRepository.deleteByFlowId(flow.getId());
        flowNodeRepository.saveAll(FlowConverter.convertNewFlowNodes(flow.getId(), flowAgg.getNodes()));

        // 保存流程边
        flowLineRepository.deleteByFlowId(flow.getId());
        flowLineRepository.saveAll(FlowConverter.convertNewFlowLines(flow.getId(), flowAgg.getLines()));

        // 发送更新流程领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.FLOW_UPDATED_EVENT, flow.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long flowId) {
        // 判断流程是否存在，如果不存在则抛出异常
        flowRepository.checkFlowExist(flowId);

        // 判断流程是否被入口引用, 如果被引用则抛出异常
        entryRepository.checkRefByEntry(flowId);

        // 判断流程是否被版本入口引用，如果被引用则抛出异常
        versionEntryRepository.checkRefByVersionEntry(flowId);

        // 删除流程
        flowRepository.deleteById(flowId);
        flowNodeRepository.deleteByFlowId(flowId);
        flowLineRepository.deleteByFlowId(flowId);

        // 删除版本流程及其节点和边
        Optional<List<VersionFlow>> versionFlowsOp = versionFlowRepository.findByFlowId(flowId);
        versionFlowsOp.ifPresent(versionFlows -> {
            if (CollectionUtils.isNotEmpty(versionFlows)) {
                return;
            }

            versionFlows.forEach(versionFlow -> {
                versionFlowRepository.deleteById(versionFlow.getId());
                versionFlowNodeRepository.deleteByVersionFlowId(versionFlow.getId());
                versionFlowLineRepository.deleteByVersionFlowId(versionFlow.getId());
            });
        });

        // 发送删除流程领域事件
        domainEventPublisher.publish(new ConfigChangeDomainEvent(DomainEventEnum.FLOW_DELETED_EVENT, flowId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long flowId) {
        // 查询并判断流程是否存在, 如果不存在，则抛出异常
        Flow flow = flowRepository.findByIdWithEx(flowId);

        // 查询并判断流程节点是否存在, 如果不存在，则抛出异常
        List<FlowNode> nodes = flowNodeRepository.findFlowNodeByFlowIdWithEx(flowId);

        // 查询并判断流程边是否存在, 如果不存在，则抛出异常
        List<FlowLine> lines = flowLineRepository.findFlowLineByFlowIdWithEx(flowId);

        // 校验节点引用的项(规则, action)是否都已发布,如果没有，则抛出异常
        flowNodeRepository.checkFlowNodeActive(nodes);

        // 校验流程是否合法
        flowValidator.validFlow(nodes, lines);

        // 新增版本流程
        VersionFlow versionFlow = new VersionFlow(flowId, flow.getName(), flow.getDescription(), flow.getVersion());
        versionFlow.active();
        versionFlowRepository.save(versionFlow);

        // 新增版本节点
        versionFlowNodeRepository.saveAll(FlowConverter.convertNewVersionFlowNodes(versionFlow.getId(), nodes));

        // 新增版本边
        versionFlowLineRepository.saveAll(FlowConverter.convertNewVersionFlowLines(versionFlow.getId(), lines));

        // 更新流程版本状态
        flow.versioned();
        flowRepository.save(flow);

        // 发送版本流程激活领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_FLOW_ACTIVED_EVENT, versionFlow.getFlowId(), versionFlow.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long flowId, Integer version) {
        // 查询并判断 version 版本流程是否存在， 如果不存在，则抛出异常
        VersionFlow versionFlow = versionFlowRepository.findByFlowIdAndVersionWithEx(flowId, version);

        // 判断 version 版本流程是否入口使用, 如果使用则抛出异常
        entryRepository.checkRefByEntry(flowId, version);

        // 判断 version 版本流程是否被版本入口使用, 如果使用则抛出异常
        versionEntryRepository.checkRefByVersionEntry(flowId, version);

        // 删除版本流程及其节点和边
        versionFlowRepository.deleteById(versionFlow.getId());
        versionFlowNodeRepository.deleteByVersionFlowId(versionFlow.getId());
        versionFlowLineRepository.deleteByVersionFlowId(versionFlow.getId());

        // 发送版本流程删除领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_FLOW_DELETED_EVENT, versionFlow.getFlowId(), versionFlow.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Long flowId, Integer version) {
        // 查询并判断 version 版本流程是否存在， 如果不存在，则抛出异常
        VersionFlow versionFlow = versionFlowRepository.findByFlowIdAndVersionWithEx(flowId, version);

        // 判断 version 版本流程是否处于发布状态，如果处于发布状态则抛出异常
        if (StatusEnum.isActive(versionFlow.getStatus())) {
            log.error("version flow has been actived. flowId:{}. version:{}", flowId, version);
            throw new CustomBusinessException(ErrorCode.FLOW_ALREADY_ACTIVED);
        }

        // 查询并判断版本流程节点是否存在, 如果不存在，则抛出异常
        List<VersionFlowNode> nodes = versionFlowNodeRepository.findVersionFlowNodeByFlowIdWithEx(versionFlow.getId());

        // 查询并判断版本流程边是否存在, 如果不存在，则抛出异常
        List<VersionFlowLine> lines = versionFlowLineRepository.findVersionFlowLineByFlowIdWithEx(versionFlow.getId());

        // 校验节点引用的项(规则, action)是否都已发布,如果没有，则抛出异常
        versionFlowNodeRepository.checkVersionFlowNodeActive(nodes);

        // 校验流程是否合法
        flowValidator.validVersionFlow(nodes, lines);

        versionFlow.active();
        versionFlowRepository.save(versionFlow);

        // 发送版本流程发布领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_FLOW_ACTIVED_EVENT, versionFlow.getFlowId(), versionFlow.getVersion()
        ));
    }

    @Transactional(rollbackFor = Exception.class)
    public void inactive(Long flowId, Integer version) {
        // 查询并判断 version 版本流程是否存在， 如果不存在，则抛出异常
        VersionFlow versionFlow = versionFlowRepository.findByFlowIdAndVersionWithEx(flowId, version);

        // 判断 version 版本流程是否处于下架状态，如果处于下架状态则抛出异常
        if (StatusEnum.isInactive(versionFlow.getStatus())) {
            log.error("version flow has been inactived. flowId:{}. version:{}", flowId, version);
            throw new CustomBusinessException(ErrorCode.FLOW_ALREADY_INACTIVED);
        }

        // 判断 version 版本流程是否被入口使用, 如果使用则抛出异常
        entryRepository.checkRefByEntry(flowId, version);

        // 判断 version 版本流程是否被其他已发布版本入口使用, 如果使用则抛出异常
        versionEntryRepository.checkRefByActiveVersionEntry(flowId, version);

        // 下架版本流程
        versionFlow.inactive();
        versionFlowRepository.save(versionFlow);

        // 发送版本流程下架领域事件
        domainEventPublisher.publish(new VersionConfigChangeDomainEvent(
                DomainEventEnum.VERSION_FLOW_INACTIVED_EVENT, versionFlow.getFlowId(), versionFlow.getVersion()
        ));
    }



}
