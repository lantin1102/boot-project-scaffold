package com.lantin.framework.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gan Luanqing
 * @date 2021/11/29 19:04 周一
 */
@Slf4j
@Component
public class CoreInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("核心拦截器执行了" + request.getRequestURI());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
