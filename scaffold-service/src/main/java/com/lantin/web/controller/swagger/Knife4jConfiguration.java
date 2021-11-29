package com.lantin.web.controller.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Gan Luanqing
 * @date 2021/11/30 1:45 周二
 */

@Configuration
public class Knife4jConfiguration {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.groupName("v1.0.0文档")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lantin.web"))
				.paths(PathSelectors.any())
				.build()
				.globalRequestParameters(globalRequestParameters())
				.protocols(new LinkedHashSet<>(Arrays.asList("HTTPS", "HTTP")));

	}

	private List<RequestParameter> globalRequestParameters() {
		RequestParameterBuilder builder = new RequestParameterBuilder().in(ParameterType.HEADER)
				.name("Cookie").required(false).query(parameter -> parameter.model(model -> model.scalarModel(ScalarType.STRING)));
		return Collections.singletonList(builder.build());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("RESTful APIs")
				.description("RESTful APIs接口文档")
				.termsOfServiceUrl("http://localhost:8080/term")
				.contact(new Contact("lantin", "www.baidu.com", "ylluanq@163.com"))
				.version("1.0.0")
				.build();
	}

}
