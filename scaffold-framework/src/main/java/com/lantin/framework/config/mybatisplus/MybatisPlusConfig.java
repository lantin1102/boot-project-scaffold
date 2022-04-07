package com.lantin.framework.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 17:59 周六
 */
@Configuration
@MapperScan("com.lantin.web.mapper")
@EnableConfigurationProperties(IdWorkerProperties.class)
public class MybatisPlusConfig {

	@Autowired
	private IdWorkerProperties idWorkerProperties;

	/**
	 * 添加 MP的分页插件拦截器
	 *
	 * @return PaginationInnerInterceptor
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return interceptor;
	}

	/**
	 * 添加一个自定义的id生成器 ，自定义机器id和数据中心id MP的idworker会使用这里注册到spring的生成器
	 *
	 * @return A defaultIdentifierGenerator with custom workId and dataCenterId
	 */
	@Bean
	public IdentifierGenerator identifierGenerator() {
		return new DefaultIdentifierGenerator(idWorkerProperties.getWorkId(), idWorkerProperties.getDataCenterId());
	}

}
