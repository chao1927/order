package org.order.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.order.common.enums.ResultCode;

import java.io.Serializable;

/**
 * @author chaobo
 * @date 2024/11/9 15:40
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 状态描述信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回的时间戳
     */
    private Long timestamp;

    public Result(ResultCode code, T data, Long timestamp) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
        this.timestamp = timestamp;
    }

    /**
     * 成功的返回结果
     *
     * @param data 返回的数据
     * @param <T>  数据类型
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data, System.currentTimeMillis());
    }

    /**
     * 成功的返回结果，无数据
     *
     * @param <T> 数据类型
     * @return Result
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS, null, System.currentTimeMillis());
    }

    /**
     * 失败的返回结果
     *
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> failure(T data) {
        return new Result<>(ResultCode.FAILURE, data, System.currentTimeMillis());
    }

    /**
     * 失败的返回结果，带状态码和消息
     *
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> failure() {
        return new Result<>(ResultCode.FAILURE, null, System.currentTimeMillis());
    }

    /**
     * 返回其它的状态码和数据
     *
     * @param resultCode 其它状态码
     * @param data 数据
     * @param <T> 数据类型
     * @return Result
     */
    public static <T> Result<T> of(ResultCode resultCode, T data) {
        return new Result<>(resultCode, data, System.currentTimeMillis());
    }

    /**
     * 返回其它的状态码
     *
     * @param resultCode 其它状态码
     * @param <T> 数据类型
     * @return Result
     */
    public static <T> Result<T> of(ResultCode resultCode) {
        return new Result<>(resultCode, null, System.currentTimeMillis());
    }



}
