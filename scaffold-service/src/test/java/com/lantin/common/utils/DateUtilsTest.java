package com.lantin.common.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Date;

class DateUtilsTest {

	@Test
	void startDateTimeOfDay() {
		Date date = DateUtils.startDateTimeOfDay(new Date());
		System.out.println(date);
		System.out.println(date.getTime());
	}

	@Test
	void calculateTime(){
		String time = "04:00";
		LocalTime parse = LocalTime.parse(time);
		System.out.println(parse);
	}

	@Test
	void  calculateWeek(){
		String str = "2022-04-04 00:00:00";
		// Date date = DateUtils.parseDate(str);
		Date date = new Date();
		int actualWeekInYear = DateUtils.getActualWeekNum(date);
		System.out.println(actualWeekInYear);

		int calibratedWeekNum = DateUtils.getCalibratedWeekNum(date, 2, "00:00");
		System.out.println(calibratedWeekNum);
	}


}