package com.lantin.web.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.lantin.common.utils.DateUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * redisson 定制化配置
 * 主要用于支持jackson java8时间类型序列化
 *
 */

@Configuration
public class RedissonConfig {


	@Bean(destroyMethod = "shutdown")
	public RedissonClient redissonClient(@Value("${spring.redis.redisson.file}") Resource configFile) throws IOException {

		// Config config = new Config();
		// config.useSingleServer()
		//         .setAddress(address)
		//         .setPassword(password)
		//         .setTimeout(timeout)
		//         .setDatabase(database)
		//         .setConnectTimeout(connectTimeout);
		//  也可以使用读取外部位置文件的方式
		// URL resource = RedissonConfig.class.getClassLoader().getResource("config/redisson-config.yml");
		Config config = Config.fromYAML(configFile.getInputStream());
		ObjectMapper om = getOm();
		config.setCodec(new JsonJacksonCodec(om));
		return Redisson.create(config);
	}

	private ObjectMapper getOm(){
		ObjectMapper mapper = new ObjectMapper();
		// // Date类型格式化
		// mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// java8 新的时间类 格式化
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		//  注意 同一个时间类型不能使用两个格式化格式 后一个会覆盖前一个
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.FORMATTER_DATE_TIME));
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.FORMATTER_DATE_TIME));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateUtils.FORMATTER_DATE));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateUtils.FORMATTER_DATE));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateUtils.FORMATTER_TIME));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateUtils.FORMATTER_TIME));
		//  自己写的时间模块注册进ObjectMapper
		mapper.registerModule(javaTimeModule);
		return mapper;
	}


}
