package com.lantin.framework.config;

import com.lantin.framework.interceptor.CoreInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 21:44 周日
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	CoreInterceptor coreInterceptor;

	/*依次添加拦截器*/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(coreInterceptor)
				.addPathPatterns("/**");

	}
}
