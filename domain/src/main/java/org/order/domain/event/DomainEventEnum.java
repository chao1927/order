package org.order.domain.event;

import lombok.Getter;
import lombok.ToString;

/**
 * 领域数据类型枚举
 *
 * @author chaobo
 * @date 2024/11/26
 */
@Getter
@ToString
public enum DomainEventEnum {

    BIZ_DATA_ARRIVED_EVENT(1, "业务数据到达"),

    PARAM_CREATED_EVENT(101, "变量已创建"),
    PARAM_UPDATED_EVENT(102, "变量已修改"),
    PARAM_DELETED_EVENT(103, "变量已删除"),
    VERSION_PARAM_ACTIVED_EVENT(111, "版本变量已发布"),
    VERSION_PARAM_INACTIVED_EVENT(112, "版本变量已下架"),
    VERSION_PARAM_DELETED_EVENT(113, "版本变量已删除"),

    RULE_CREATED_EVENT(201, "规则已创建"),
    RULE_UPDATED_EVENT(202, "规则已修改"),
    RULE_DELETED_EVENT(203, "规则已删除"),
    VERSION_RULE_ACTIVED_EVENT(211, "版本规则已发布"),
    VERSION_RULE_INACTIVED_EVENT(212, "版本规则已下架"),
    VERSION_RULE_DELETED_EVENT(213, "版本规则已删除"),

    ACTION_CREATED_EVENT(301, "action已创建"),
    ACTION_UPDATED_EVENT(302, "action已修改"),
    ACTION_DELETED_EVENT(303, "action已删除"),
    VERSION_ACTION_ACTIVED_EVENT(311, "版本action已发布"),
    VERSION_ACTION_INACTIVED_EVENT(312, "版本action已下架"),
    VERSION_ACTION_DELETED_EVENT(313, "版本action已删除"),

    FLOW_CREATED_EVENT(401, "流程已创建"),
    FLOW_UPDATED_EVENT(402, "流程已修改"),
    FLOW_DELETED_EVENT(403, "流程已删除"),
    VERSION_FLOW_ACTIVED_EVENT(411, "版本流程已发布"),
    VERSION_FLOW_INACTIVED_EVENT(412, "版本流程已下架"),
    VERSION_FLOW_DELETED_EVENT(413, "版本流程已删除"),

    ENTRY_CREATED_EVENT(501, "入口已创建"),
    ENTRY_UPDATED_EVENT(502, "入口已修改"),
    ENTRY_DELETED_EVENT(503, "入口已删除"),
    VERSION_ENTRY_ACTIVED_EVENT(511, "版本入口已发布"),
    VERSION_ENTRY_INACTIVED_EVENT(512, "版本入口已下架"),
    VERSION_ENTRY_DELETED_EVENT(513, "版本入口已删除");

    ;

    private final Integer code;

    private final String name;

    DomainEventEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
