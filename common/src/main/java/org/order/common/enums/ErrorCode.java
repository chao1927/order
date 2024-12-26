package org.order.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 全局异常编码
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Getter
@ToString
public enum ErrorCode {

    // todo 重新对错误编码进行整理

    // 4XXX 表示参数异常
    INVALID_PARAM(400, "Invalid parameters."),

    // 5XXX 表示业务的错误
    INTERNAL_SERVER_ERROR(500, "Internal server error."),

    // 51XX 表示业务数据相关错误



    // 52XX 表示变量相关错误
    // 变量名称重复
    PARAM_NAME_DUPLICATION(5101, "param name duplication."),

    // 变量表达式错误
    PARAM_EXPRESSION_ERROR(5102, "param expression error."),

    // 变量没找到
    PARAM_NOT_FOUND(5103, "param not found."),

    // 变量名称已存在
    PARAM_NAME_ALREADY_EXIST(5104, "param name already exist."),


    VERSION_PARAM_NOT_FOUND(5103, "version param not found."),

    VERSION_PARAM_ALREADY_ACTIVED(5103, "version param has been actived."),

    VERSION_PARAM_ALREADY_INACTIVED(5103, "version param has been inactivated."),

    VERSION_PARAM_ALREADY_DELETED(5103, "version param has been deleted."),

    PARAM_EXPRESSION_VALIDATE_FAILED(5103, "param expression validate failed."),

    // 53XX 表示规则相关错误
    RULE_NAME_DUPLICATION(5201, "rule name duplication."),

    RULE_NOT_FOUND(5201, "rule not found."),

    // 54XX 表示Action相关错误

    // 55XX 表示流程相关错误

    // 56XX 表示入口相关错误

    // 57XX 表示任务相关错误

    // 58XX ...

    PARAM_REFERENCED_BY_RULE(5103, "param has been referenced by rule."),

    RULE_ITEM_IS_EMPTY(5201, "rule item is empty."),

    RULE_REFERENCED_BY_RULE(5201, "rule referenced by rule."),

    ACTION_NOT_FOUND(5401, "action not found."),

    ACTION_NAME_DUPLICATE(5401, "action name duplication."),

    ACTION_ALREADY_ACTIVED(5401, "action already actived."),

    ACTION_ALREADY_INACTIVATED(5401, "action has been inactivated."),

    FLOW_NAME_DUPLICATE(5501, "flow name duplication."),

    FLOW_NOT_FOUND(5501, "flow not found."),

    ENTRY_NAME_DUPLICATE(5601, "entry name duplication."),

    ENTRY_NOT_FOUND(5601, "entry not found."),

    FLOW_ALREADY_ACTIVED(5501, "flow already actived."),

    FLOW_ALREADY_INACTIVED(5501, "version flow has been inactived."),

    ENTRY_ALREADY_ACTIVED(5601, "entry already actived."),

    ENTRY_ALREADY_INACTIVED(5601, "entry already inactived."),

    FLOW_NODE_TYPE_ERROR(5501, "flow node type error."),

    ACTION_REFERENCED_BY_FLOW(5401, "action reference by flow."),

    RULE_REFERENCED_BY_FLOW(5201, "rule reference by flow"),


    FLOW_REFERENCE_BY_ENTRY(5601, "flow reference by entry."),

    RULE_ALREADY_ACTIVED(5201, "rule already actived."),

    RULE_ALREADY_INACTIVED(5201, "rule already inactived."),

    FLOW_LINE_IS_EMPTY(5601, "flow line is empty."),

    FLOW_NODE_IS_EMPTY(5601, "flow node is empty."),

    FLOW_NOT_ACTIVE(5601, "flow not active."),

    RULE_NOT_ACTIVE(5201, "rule not active."),

    ACTION_NOT_ACTIVE(5401, "action not active."),

    RULE_ITEM_TYPE_ERROR(5201, "rule item type error."),

    PARAM_NOT_ACTIVE(5101, "param not active."),



    WRITE_VALUE_ERROR(5101, "write value error."),
    BIZ_NOT_FOUND(5101, "biz not found.");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}