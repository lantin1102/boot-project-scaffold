package com.lantin.framework.config;


import com.lantin.common.utils.SnowFlakeIdWorker;
import com.lantin.framework.config.mybatisplus.IdWorkerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

	@Autowired
	IdWorkerProperties idWorkerProperties;

	@Bean
	SnowFlakeIdWorker snowFlakeIdWorker(){
		return new SnowFlakeIdWorker(idWorkerProperties.getDataCenterId(), idWorkerProperties.getWorkId());
	}
}
