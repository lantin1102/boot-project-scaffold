package com.lantin.framework.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.lantin.common.utils.DateUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 时间类型序列化配置
 * datetime格式  yyyy-MM-dd HH:mm:ss
 * date格式 yyyy-MM-dd
 * time格式 HH:mm:ss
 *
 * @author Gan Luanqing
 * @date 2021/11/30 2:20 周二
 */
@Configuration
public class DateFormatConfig {

	/**
	 * 自定义jackson对jdk8新的时间类的参数绑定和返回值序列化
	 * <p>
	 * x-xxx-form-urlencoding方式的请求走 Model Attribute method processor
	 * <p>
	 * 被@RequestBody注解的参数走 Request Response Body Method Processor
	 * 然后使用jackson解析
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return builder -> builder
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.FORMATTER_DATE_TIME))
				.serializerByType(LocalDate.class, new LocalDateSerializer(DateUtils.FORMATTER_DATE))
				.serializerByType(LocalTime.class, new LocalTimeSerializer(DateUtils.FORMATTER_TIME))
				.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.FORMATTER_DATE_TIME))
				.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateUtils.FORMATTER_DATE))
				.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateUtils.FORMATTER_TIME));
	}
}
