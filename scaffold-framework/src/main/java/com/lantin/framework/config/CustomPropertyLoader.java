package com.lantin.framework.config;


import com.lantin.framework.handler.YamlPropertySourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;


@Configuration
@PropertySources({
		@PropertySource({"classpath:config/app.properties"}),
		@PropertySource(value = "classpath:config/my.yml", factory = YamlPropertySourceResolver.class)
})
public class CustomPropertyLoader {

	@Autowired
	private Environment environment;

	@PostConstruct
	void init() {

		String property = environment.getProperty("app.name");

		System.out.println(property);
	}
}
