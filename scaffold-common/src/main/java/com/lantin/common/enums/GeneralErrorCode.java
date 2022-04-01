package com.lantin.common.enums;

/**
 * @author Gan Luanqing
 * @date 2021/11/30 17:19 周二
 */
public enum GeneralErrorCode implements ErrorCode {
	/**
	 * 通用错误码枚举类
	 */
	SYSTEM_WRONG(500, "系统内部异常"),
	REQUEST_PARAM_INVALID(400, "请求参数非法"),
	NO_BEAN_FOUND(444, "找不到BEAN")
	;

	GeneralErrorCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	private final Integer code;
	private final String message;

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
