package com.lantin.config;

import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Gan Luanqing
 * @date 2021/11/29 17:44 周一
 */
public class ThreadPoolTest extends BaseSpringBootTest {
	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Test
	public void test(){

		System.out.println(threadPoolTaskExecutor.getActiveCount());
		System.out.println(threadPoolTaskExecutor.getCorePoolSize());
		System.out.println(threadPoolTaskExecutor.getThreadNamePrefix());

		RejectedExecutionHandler handler = threadPoolTaskExecutor.getThreadPoolExecutor().getRejectedExecutionHandler();

		Assert.isTrue(handler instanceof ThreadPoolExecutor.CallerRunsPolicy);
	}
}
