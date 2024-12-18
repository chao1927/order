package org.order.common.config;

import com.alibaba.fastjson2.JSON;
import org.order.common.Result;
import org.order.common.exception.CustomBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author chaobo
 * @date 2024/11/13
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBusinessException.class)
    @ResponseBody
    public ResponseEntity<Result<String>> handleCustomBusinessException(CustomBusinessException ex) {
        // todo 异常结果优化
        Result<String> errorResponse = Result.failure(JSON.toJSONString(ex));
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    // 可以添加更多的异常处理方法
}
