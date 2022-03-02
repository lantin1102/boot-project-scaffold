package com.lantin.web.controller.demo;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Gan Luanqing
 * @date 2022/02/16 18:15 周三
 */
class DateUtilTest {

	@Test
	public void test() throws ParseException {
		String fromDay = "2022-01-01 00:00:00";
		String toDay = "2021-12-20 23:59:59";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsefrom = simpleDateFormat.parse(fromDay);
		Date parseTo = simpleDateFormat.parse(toDay);

		int intervalDays = getIntervalDays(parsefrom, parseTo);

		System.out.println(intervalDays);
	}

	public static int getIntervalDays(Date fDate, Date tDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(tDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;
	}

	@Test
	public void test11() {
		// List<Object> finalPrize = new ArrayList<>();
		// finalPrize.add(1);
		// finalPrize.add(2);
		// finalPrize.add(3);
		//
		// ArrayList<Object> pool2 = new ArrayList<>();
		// pool2.add(5);
		//
		// List<Object> pool3;
		// List<Object> pool4;
		// pool3 = finalPrize;
		//
		// pool3.add(4);
		// finalPrize.clear();
		// finalPrize.addAll(pool2);
		long convert = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		System.out.println(convert);
	}

	@Test
	public void test113() {
		// List<Integer> userList = new ArrayList<>();
		// userList.add(1);
		// userList.add(2);
		// userList.add(4);
		// userList.add(5);
		// userList.add(6);
		//
		// List<Integer> collect = userList.stream().filter(i -> i % 2 == 0)
		// 		.collect(Collectors.toList());

		int i = 9;
		i = i>>1;
		System.out.println(i);
		int b = 0b1111>>1;
		System.out.println(b);
	}
}