package com.lantin.framework.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config.a")
public class ConfigAProperties {

	String name = "A";
	int value = 10;
}
