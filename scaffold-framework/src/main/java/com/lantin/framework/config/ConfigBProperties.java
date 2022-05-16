package com.lantin.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config.b")
public class ConfigBProperties {

	String name = "b";
	int value = 20;


}
