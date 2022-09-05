package com.lantin.web.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class CacheServiceTest {



	@Test
	public void test() throws InterruptedException {

		new Thread(()-> {
			try {
				getResult("task_15");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();
		new Thread(()-> {
			try {
				getResult("task_15");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

	@AfterEach
	public void after() throws InterruptedException {
		TimeUnit.MINUTES.sleep(5);
	}

	private String getResult(String key) throws InterruptedException {
		String result = "";
		String lockKey = key+"lock";
		synchronized (lockKey) {
			System.out.println("start_get"+key);
			TimeUnit.SECONDS.sleep(3);
			result += key + "result";
			System.out.println(result);
			return result;
		}

	}

}