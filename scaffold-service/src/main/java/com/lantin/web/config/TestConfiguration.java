package com.lantin.web.config;


import com.lantin.web.domain.DemoA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TestConfiguration {


	@Value("${reply.summary.flushRealtimeToSnapshotIntervalMillis:60000}")
	private Long configMillis;

	@Value("${comment.lock.comment.flush.tpl:comment.lazy.flush.%s}")
	private String configTpl;

	@Autowired
	private Environment environment;

	@Bean("demoATest1")
	DemoA demoA(){
		System.out.println(configMillis);
		System.out.println(configTpl);
		environment.getProperty("my.name");
		return new DemoA();
	}


}
