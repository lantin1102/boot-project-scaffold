package com.lantin.framework.handler;


import com.lantin.common.domain.response.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 17:20 周六
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(Exception.class)
	public CommonResponse<?> frameworkException(Exception e) {

		return CommonResponse.failure("系统异常");
	}
}
