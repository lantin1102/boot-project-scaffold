package com.lantin.common.utils;

import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SnowFlakeIdWorkerTest extends BaseSpringBootTest {

	@Autowired
	SnowFlakeIdWorker idWorker;

	@Test
	void nextId() {
		long l = idWorker.nextId();
		System.out.println(l);
	}
}