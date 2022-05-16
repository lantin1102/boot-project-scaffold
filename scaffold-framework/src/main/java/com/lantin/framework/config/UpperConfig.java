package com.lantin.framework.config;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Date;


@Configuration
@EnableConfigurationProperties({ConfigAProperties.class,ConfigBProperties.class})
public class UpperConfig {

	private ConfigAProperties configAProperties;
	private ConfigBProperties configBProperties;

	@Autowired
	WebApplicationContext webApplicationContext;

	// 构造函数注入
	public UpperConfig(ConfigAProperties configAProperties,ConfigBProperties configBProperties){
		this.configAProperties = configAProperties;
		this.configBProperties = configBProperties;
	}

	@Bean
	UpperClass upperClass(){
		WebMvcConfigurationSupport bean = webApplicationContext.getBean(WebMvcConfigurationSupport.class);

		System.out.println(configAProperties.name);
		System.out.println(configAProperties.value);
		System.out.println(configBProperties.name);
		System.out.println(configBProperties.value);

		return new UpperClass();
	}


	class UpperClass{

	}

	public static void main(String[] args) throws InterruptedException {

		new Thread(new Task1()).start();

		Thread.sleep(1000*60*20);

	}
	static class Task1 implements Runnable{
		@SneakyThrows
		@Override
		public void run() {
			while (true){
				while (true){
					System.out.println("Thread"+Thread.currentThread().getName()+"开始执行 at"+new Date());
					Thread.sleep(2000L);
					System.out.println("do service");
					Thread.sleep(1000L);
					System.out.println("Thread"+Thread.currentThread().getName()+"执行成功 at"+new Date());
				}
			}
		}
	}
}
