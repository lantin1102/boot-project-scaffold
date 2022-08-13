package com.lantin.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lantin.common.utils.SnowFlakeIdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.*;
import java.util.ArrayList;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 20:21 周日
 */
public class IdWorkerTest {

	@Test
	public void test3(){
		long id = IdWorker.getId();

		System.out.println(id);
	}

	@Test
	public void test() throws InterruptedException {

		long id = IdWorker.getId();

		System.out.println(id);
		ArrayList<Object> objects = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			String idStr = IdWorker.getIdStr();
			objects.add(idStr);
			Thread.sleep(10);

		}

		System.out.println(objects);
	}
	@Test
	public void test1(){
		long id = IdWorker.getId();
		String timeId = IdWorker.getTimeId();
		String millisecond = IdWorker.getMillisecond();
		System.out.println(id);
		System.out.println(timeId);
		System.out.println(millisecond);

		LocalDate now = LocalDate.now();
		LocalTime min = LocalTime.MIN;
		LocalDateTime of = LocalDateTime.of(now, min);
		Instant instant = of.toInstant(ZoneOffset.of("+08:00"));
		long l1 = instant.toEpochMilli();
		System.out.println("毫秒"+l1);
		long epochSecond = instant.getEpochSecond();
		of.atZone(ZoneId.systemDefault()).toEpochSecond();
		System.out.println(of);
		System.out.println(epochSecond);

		LocalDateTime now1 = LocalDateTime.now();

		int second = now.atStartOfDay().getSecond();
		System.out.println(second);
		long l = now.atStartOfDay().toEpochSecond(ZoneOffset.ofHours(8));
		System.out.println(l);

	}
	@Autowired
	IdentifierGenerator identifierGenerator;
	@Autowired
	SnowFlakeIdWorker idWorker;
	@Test
	public void test33(){
		Number number = identifierGenerator.nextId(new Object());
		System.out.println(number.longValue());

		System.out.println(String.valueOf(number.longValue()).length());

		System.out.println("5911899212548352".length());

		long l = idWorker.nextId();
		System.out.println(l);
		System.out.println(String.valueOf(l).length());
	}
}
