package com.lantin.framework.handler;


import com.lantin.common.domain.response.CommonResponse;
import com.lantin.common.enums.GeneralErrorCode;
import com.lantin.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 17:20 周六
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 业务异常处理
	 */
	@ExceptionHandler(BusinessException.class)
	public CommonResponse<?> businessExceptionHandler(BusinessException e, HttpServletRequest request) {
		return CommonResponse.failure(e);
	}

	/**
	 * 参数校验异常处理
	 * 主要处理以下几个类
	 * BindException:valid校验实体类
	 * MissingServletRequestParameterException:@RequestParam未传参
	 * MethodArgumentTypeMismatchException:参数类型不匹配
	 * HttpRequestMethodNotSupportedException:请求方式不匹配
	 */
	@ExceptionHandler({BindException.class,
			MethodArgumentTypeMismatchException.class,
			MissingServletRequestParameterException.class,
			HttpRequestMethodNotSupportedException.class,
			ConstraintViolationException.class})
	public CommonResponse<?> paramValidationExceptionHandler(Exception e, HttpServletRequest request) {
		String msg;
		if (e instanceof BindException exception) {
			msg = exception.getBindingResult().getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.joining(","));
		} else if (e instanceof MissingServletRequestParameterException exception) {
			msg = String.format("参数[%s]不能为空", exception.getParameterName());
		} else if (e instanceof MethodArgumentTypeMismatchException exception) {
			msg = String.format("参数[%s]类型错误", exception.getName());
		} else if (e instanceof HttpRequestMethodNotSupportedException exception) {
			msg = exception.getMessage();
		} else if (e instanceof ConstraintViolationException exception){
			msg = exception.getConstraintViolations().stream()
					.map(ConstraintViolation::getMessage)
					.collect(Collectors.joining(","));
		}else {
			msg = "必填参数缺失";
		}
		return CommonResponse.failure(GeneralErrorCode.REQUEST_PARAM_INVALID.getCode(), msg);
	}

	/**
	 * 框架级别异常及其他未知异常
	 */
	@ExceptionHandler(Exception.class)
	public CommonResponse<?> exceptionHandler(Exception e, HttpServletRequest request) {
		return CommonResponse.failure(GeneralErrorCode.SYSTEM_WRONG.getCode(), e.getMessage());
	}

	// private void printApiLog(HttpServletRequest request, CommonResponse<?> resp, Exception e) {
	// 	String methodInvoke = ControllerContext.getContext().getMethodInvoke();
	// 	String logStr = LogHelper.getApiLog(request, JsonUtil.renderToString(resp), methodInvoke, System.currentTimeMillis() - ControllerContext.getContext().getRequestBegin());
	// 	log.error(logStr, e);
	// }
}
