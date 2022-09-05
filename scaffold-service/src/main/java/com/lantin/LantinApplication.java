package com.lantin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 17:16 周六
 */


@SpringBootApplication
@EnableCaching
public class LantinApplication {

	public static void main(String[] args) {
		SpringApplication.run(LantinApplication.class, args);
	}

}
