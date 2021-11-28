package com.lantin.config;

import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 23:16 周日
 */
public class RedissonConfigTest extends BaseSpringBootTest {

	@Autowired
	RedissonClient redissonClient;

	@Test
	public void test(){

		System.out.println(redissonClient);
	}
}
