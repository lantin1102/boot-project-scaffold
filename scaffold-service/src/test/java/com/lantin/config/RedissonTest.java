package com.lantin.config;


import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedissonTest extends BaseSpringBootTest {

	@Autowired
	RedissonClient redissonClient;

	@Test
	public void test(){
		redissonClient.getBucket("abc").delete();
		redissonClient.getBucket("abc").set("hello work");
		String abc = redissonClient.getBucket("abc").get().toString();


		System.out.println(abc);

		redissonClient.getBucket("edc").set("whello world");


		String edc = redissonClient.getBucket("edc").get().toString();
		System.out.println(edc);

	}

	@Test
	public void test1(){

		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			String tpl = "test.key%s";
			String format = String.format(tpl, i + 1);
			redissonClient.getBucket(format).set(i+1,120, TimeUnit.SECONDS);

		}
		System.out.println("cost:"+(System.currentTimeMillis()-s));
	}

}
