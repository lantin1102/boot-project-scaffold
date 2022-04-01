package com.lantin.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created on 2021/06/10/18:17 周四
 * 时间工具类
 *
 * @author Lantin
 */
public class DateUtils {
	public static final String STANDARD_DATE = "yyyy-MM-dd HH:mm:ss";
	public static final String ISO_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE = "yyyy-MM-dd";
	public static final String TIME = "HH:mm:ss";

	public static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern(STANDARD_DATE);
	public static final DateTimeFormatter FORMATTER_ISO_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern(ISO_LOCAL_DATE_TIME);
	public static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern(DATE);
	public static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern(TIME);

	/**
	 * Date转日期字符串,默认"yyyy-MM-dd HH:mm:ss"格式
	 *
	 * @param date Date时间
	 * @return java.lang.String
	 */
	public static String formatDate(Date date) {

		return formatDate(date, STANDARD_DATE);
	}

	/**
	 * 按照指定的格式将Date转为日期字符串
	 *
	 * @param date    Date时间
	 * @param pattern 想要的格式
	 * @return java.lang.String
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} catch (Exception e) {
			String errorMsg = "can not format " + date
					+ " to pattern " + pattern;
			throw new IllegalArgumentException(errorMsg, e);
		}
	}

	/**
	 * 日期字符串转为日期，默认格式化 yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 日期字符串
	 * @return java.util.Date
	 */
	public static Date parseDate(String date) {
		return parseDate(date, STANDARD_DATE);
	}

	/**
	 * 将日期字符串解析为Date,按指定的格式化形式
	 *
	 * @param date   日期字符串
	 * @param format 格式化d额格式
	 * @return java.util.Date
	 */
	public static Date parseDate(String date, String format) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("can not parse " + date + " to Date");
		}
	}

	public static long dateTimeToMillis(Object dateObj) {
		long timestamp = 0;
		if (dateObj instanceof LocalDateTime localDateTime) {
			timestamp = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		}
		return timestamp;
	}

	public static LocalDate date2LocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date startDateTimeOfDay(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.HOUR_OF_DAY, 0);
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.MILLISECOND, 0);
		return instance.getTime();
	}

	public static Date endDateTimeOfDay(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.HOUR_OF_DAY, 23);
		instance.set(Calendar.SECOND, 59);
		instance.set(Calendar.MINUTE, 59);
		instance.set(Calendar.MILLISECOND, 0);
		return instance.getTime();
	}

	/**
	 * 返回某日期在一年中的实际周数
	 * @param date date
	 * @return 周数
	 */
	public static int getActualWeekNum(Date date) {
		Calendar c = Calendar.getInstance();
		// 默认为中国惯例 周一为一周的开始,其他时区 TODO
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		return calculateWeekNum(c);
	}

	/**
	 * 给定一周的开始时间，返回某日期的校准后周数
	 *
	 * @param date    日期
	 * @param weekDay 一周的哪一天算做开始 定义参考{@link Calendar#SUNDAY} {@link Calendar#MONDAY}
	 * @param timeStr 具体开始时间 "HH:mm"
	 * @return 校准后周数
	 */
	public static int getCalibratedWeekNum(Date date, int weekDay, String timeStr) {
		Objects.requireNonNull(date);
		Calendar c = Calendar.getInstance();
		// 默认使用中国惯例 周一为一周的开始,其他时区 TODO
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		int actualNum = calculateWeekNum(c);

		c.set(Calendar.DAY_OF_WEEK, weekDay);
		LocalTime localTime = LocalTime.parse(timeStr);
		c.set(Calendar.HOUR_OF_DAY, localTime.getHour());
		c.set(Calendar.MINUTE, localTime.getMinute());
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date calibratedWeekStart = c.getTime();
		// 周数校准
		if (date.before(calibratedWeekStart)) {
			actualNum--;
		}
		return actualNum;
	}
	private static int calculateWeekNum(Calendar c) {
		int month = c.get(Calendar.MONTH);
		int weekInYear = c.get(Calendar.WEEK_OF_YEAR);
		// 如果月份为12月并且周数为1 表明应该是这一年的第53周（Calender类将此周算为下一年的第一周，返回1）
		if (month >= 11 && weekInYear <= 1) {
			weekInYear += 52;
		}
		return weekInYear;
	}



	public static void main(String[] args) {


		// Date date = new Date();
		// Calendar c = Calendar.getInstance();
		// int firstDayOfWeek = c.getFirstDayOfWeek();
		// // c.setFirstDayOfWeek(Calendar.MONDAY);
		// c.setTime(date);
		//
		// System.out.println("目前一周的第一天："+firstDayOfWeek);
		// System.out.println(formatDate(date)+"是在第"+c.get(Calendar.WEEK_OF_YEAR)+"周");
		//
		//
		//
		// String  dateStr = "2022-03-26 23:59:59";
		// date = parseDate(dateStr);
		// c.setTime(date);
		// System.out.println(dateStr+"是在第"+c.get(Calendar.WEEK_OF_YEAR)+"周");
		//
		// date = parseDate(dateStr);
		// c.setTime(date);
		// System.out.println(dateStr+"是在第"+c.get(Calendar.WEEK_OF_YEAR)+"周");
		int weekDay = 2;
		String timeStr = "04:00";
		LocalTime time = LocalTime.parse(timeStr);
		System.out.println(time);
		String dateStr = "2022-04-04 00:00:00";
		System.out.println("当前时间：" + dateStr);
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		Date curDate = parseDate(dateStr);
		c.setTime(curDate);
		int i = c.get(Calendar.WEEK_OF_YEAR);
		System.out.println("cur date actual week:" + i);
		c.set(Calendar.DAY_OF_WEEK, weekDay);
		c.set(Calendar.HOUR_OF_DAY, time.getHour());
		c.set(Calendar.MINUTE, time.getMinute());
		c.set(Calendar.SECOND, time.getSecond());
		c.set(Calendar.MILLISECOND, 0);
		System.out.println("新一周的计算时间点" + DateUtils.formatDate(c.getTime()));
		Date calibratedWeekStartDate = c.getTime();
		if (curDate.before(calibratedWeekStartDate)) {
			i--;
		}
		System.out.println("校准后的周数" + i);
		// c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		// Date time1 = c.getTime();
		// System.out.println("周一时间"+time1);
		// System.out.println("周一时间"+startDateTimeOfDay(time1));
		// c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		// Date time2 = c.getTime();
		// System.out.println("周日时间"+time2);
		// System.out.println("周一时间"+endDateTimeOfDay(time2));

		//
		// int dayofWeek = c.get(Calendar.DAY_OF_WEEK);
		// int firstDayOfWeek = c.getFirstDayOfWeek();
		// System.out.println(firstDayOfWeek);
		//
		// System.out.println(dayofWeek);
		// System.out.println(time);
		//  dayofWeek = c.get(Calendar.DAY_OF_WEEK);
		// System.out.println(dayofWeek);
		//
		//
		// int minimum = c.getMinimum(Calendar.DAY_OF_WEEK);
		// int maximum = c.getMaximum(Calendar.DAY_OF_WEEK);
		// int amin = c.getActualMinimum(Calendar.DAY_OF_WEEK);
		// int amx = c.getActualMaximum(Calendar.DAY_OF_WEEK);
		//
		// System.out.println(minimum);
		// System.out.println(maximum);
		// System.out.println(amin);
		// System.out.println(amx);
		Date now = new Date();
		int calibratedWeekNum = getCalibratedWeekNum(now, Calendar.MONDAY, "00:00");
		System.out.println(calibratedWeekNum);
	}


}
