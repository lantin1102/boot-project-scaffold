package com.lantin.common.exception;

import com.lantin.common.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 19:03 周日
 */

@Getter
@Setter
public class BusinessException extends RuntimeException {

	protected Integer errorCode;

	protected String errorMsg;

	public BusinessException() {
	}

	public BusinessException(ErrorCode errorCode) {
		this(errorCode.getCode(), errorCode.getMessage());
	}

	public BusinessException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode.getCode();
		this.errorMsg = errorCode.getMessage();
	}

	public BusinessException(int code, String msg) {
		this.errorCode = code;
		this.errorMsg = msg;
	}

	@Override
	public String getMessage() {
		return errorMsg;
	}
}
