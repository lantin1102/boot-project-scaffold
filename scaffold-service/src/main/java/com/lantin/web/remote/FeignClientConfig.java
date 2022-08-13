package com.lantin.web.remote;


import com.lantin.web.remote.interceptor.LivelinkFeignInterceptor;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {


	@Value("${feign.client.livelink.baseUrl}")
	String baseUrl;


	@Bean
	LivelinkApi livelinkApi(LivelinkFeignInterceptor livelinkFeignInterceptor) {

		Slf4jLogger logger = new Slf4jLogger("api_client");

		return Feign.builder()
				.logger(logger)
				.requestInterceptor(livelinkFeignInterceptor)
				.logLevel(Logger.Level.FULL)
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.target(LivelinkApi.class, baseUrl);
	}

}
