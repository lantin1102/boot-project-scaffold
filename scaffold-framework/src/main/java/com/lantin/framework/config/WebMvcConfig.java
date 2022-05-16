package com.lantin.framework.config;

import com.lantin.common.utils.DateUtils;
import com.lantin.framework.interceptor.CoreInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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
		registry.addInterceptor(coreInterceptor).addPathPatterns("/**");

	}

	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// 	registry.addResourceHandler("/static/")
	// }

	/**
	 * 全局处理接收时间参数的问题
	 * 非requestBody注解走的是 RequestMappingHandlerAdapter绑定参数
	 *
	 * @param registry 格式化注册器
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(String source) {

				if (!StringUtils.hasText(source)) {
					return null;
				}
				return LocalDateTime.parse(source, DateUtils.FORMATTER_DATE_TIME);
			}
		});
		registry.addConverter(new Converter<String, Timestamp>() {
			@Override
			public Timestamp convert(String source) {
				if (!StringUtils.hasText(source)) {
					return null;
				}
				return Timestamp.valueOf(source);
			}
		});
		registry.addConverter(new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				if (StringUtils.hasText(source)) {
					return null;
				}
				try {
					return new SimpleDateFormat(DateUtils.STANDARD_DATE).parse(source);
				} catch (ParseException e) {
					throw new RuntimeException("date format error with : \"" + source + "\"");
				}
			}
		});
		registry.addConverter(new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String source) {
				if (!StringUtils.hasText(source)) {
					return null;
				}
				return LocalDate.parse(source);
			}
		});
		registry.addConverter(new Converter<String, LocalTime>() {
			@Override
			public LocalTime convert(String source) {
				if (!StringUtils.hasText(source)) {
					return null;
				}
				return LocalTime.parse(source, DateUtils.FORMATTER_TIME);
			}
		});
	}
}
