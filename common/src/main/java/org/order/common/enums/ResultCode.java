package org.order.common.enums;

/**
 * @author chaobo
 * @date 2024/11/9 15:43
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),


    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),


    /**
     * 资源未找到
     */
    NOT_FOUND(404, "资源未找到"),


    /**
     * 服务器错误
     */
    FAILURE(500, "服务器错误");



    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
