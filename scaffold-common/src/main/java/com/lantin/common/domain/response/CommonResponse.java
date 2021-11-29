package com.lantin.common.domain.response;

import com.lantin.common.domain.base.BaseResponse;
import com.lantin.common.enums.ErrorCode;
import com.lantin.common.exception.BusinessException;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import static com.lantin.common.constant.BaseConstants.*;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 19:49 周六
 */

@Data
@ApiModel(value = "通用返回封装", parent = BaseResponse.class)
public class CommonResponse<T> extends BaseResponse {

	private T data;

	public CommonResponse(int code, String message) {
		this(code, message, null);
	}

	public CommonResponse(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public CommonResponse(ErrorCode error) {
		this(error.getCode(), error.getMessage(), null);
	}

	public CommonResponse(T data) {
		this(COMMON_SUCCESS_CODE, COMMON_SUCCESS_MSG, data);
	}

	public static <T> CommonResponse<T> success() {
		return new CommonResponse<>(COMMON_SUCCESS_CODE, COMMON_SUCCESS_MSG);
	}

	public static <T> CommonResponse<T> success(T data) {
		return new CommonResponse<>(data);
	}

	public static <T> CommonResponse<T> failure(BusinessException e) {
		return new CommonResponse<>(e.getErrorCode(), e.getErrorMsg());
	}

	public static <T> CommonResponse<T> failure(Integer errorCode, String errorMsg) {
		return new CommonResponse<>(errorCode, errorMsg);
	}

	public static <T> CommonResponse<T> failure() {
		return new CommonResponse<>(COMMON_FAIL_CODE, COMMON_FAIL_MSG);
	}

	public static <T> CommonResponse<T> failure(String errorMessage) {
		return new CommonResponse<>(COMMON_FAIL_CODE, errorMessage);
	}

	public static <T> CommonResponse<T> failure(ErrorCode error) {
		return new CommonResponse<>(error.getCode(), error.getMessage());
	}

	public boolean success(CommonResponse<?> response) {
		return COMMON_SUCCESS_CODE.equals(response.getCode());
	}
}
