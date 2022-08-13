package com.lantin.framework.handler;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;


// yaml文件 解析器

/**
 * 自定义解析规则，引入第三方文件解析器
 */
public class YamlPropertySourceResolver implements PropertySourceFactory {


	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
		factoryBean.setResources(resource.getResource());
		Properties properties = factoryBean.getObject();
		return (name != null ? new PropertiesPropertySource(name, properties) : new PropertiesPropertySource(resource.getResource().getFilename(), properties));
	}
}
