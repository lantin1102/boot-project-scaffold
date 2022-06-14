package com.lantin.web.service.test;

import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.test.TestCount1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class TestCount1ServiceTest extends BaseSpringBootTest {

	@Autowired
	TestCount1Service service;

	@Test
	void incrCount() throws InterruptedException {

		TestCount1 before = service.getById(1);
		log.info("before count is {}",before.getCount());
		// System.out.println("before:"+before.getCount());
		try {
			service.incrCount(1);
		} catch (Exception ignore) {
			log.error("catch error when inc",ignore);
		}

		TestCount1 after = service.getById(1);
		log.info("after count is {}",after.getCount());
		Thread.sleep(3000);
		 after = service.getById(1);
		log.info("after2 count is {}",after.getCount());
	}
}