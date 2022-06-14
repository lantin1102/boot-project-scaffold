package com.lantin.web.service.test;

import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

class AsyncServiceTest extends BaseSpringBootTest {

	@Autowired
	AsyncService asyncService;

	@Test
	void serviceA()  {

		System.out.println("开始 at"+ ZonedDateTime.now()+"...");
		asyncService.serviceA(9);
		System.out.println("结束 at"+ZonedDateTime.now()+"...");
	}

	@AfterEach
	public void afterTest() throws InterruptedException {
		Thread.sleep(1000*60);
	}
}