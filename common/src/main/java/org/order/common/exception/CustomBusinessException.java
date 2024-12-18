package org.order.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.order.common.enums.ErrorCode;

/**
 * 业务异常
 *
 * @author chaobo
 * @date 2024/11/13
 */
@Getter
@ToString
public class CustomBusinessException extends RuntimeException {

    private final int errorCode;

    private final String errorMessage;

    public CustomBusinessException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public CustomBusinessException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

}
