package com.lantin.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2021/07/16/21:57 周五
 *
 * @author Lantin
 */
public class ServletUtils {

	public static ServletRequestAttributes getServletAttribute() {
		return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	}

	public static HttpServletRequest getRequest() {
		return getServletAttribute().getRequest();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}
}
