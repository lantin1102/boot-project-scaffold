package com.lantin.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created on 2021/06/10/18:17 周四
 * 时间工具类
 *
 * @author Lantin
 */
public class DateUtils {
	public static final String DATE_LONG = "yyyy-MM-dd HH:mm:ss";
	public static final String ISO_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_SHORT = "yyyy-MM-dd";
	public static final String TIME = "HH:mm:ss";

	public static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern(DATE_LONG);
	public static final DateTimeFormatter FORMATTER_ISO_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern(ISO_LOCAL_DATE_TIME);
	public static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern(DATE_SHORT);
	public static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern(TIME);

	/**
	 * Date转日期字符串,默认"yyyy-MM-dd HH:mm:ss"格式
	 *
	 * @param date Date时间
	 * @return java.lang.String
	 */
	public static String formDateTime(Date date) {

		return formDateTime(date, DATE_LONG);
	}

	/**
	 * 按照指定的格式将Date转为日期字符串
	 *
	 * @param date    Date时间
	 * @param pattern 想要的格式
	 * @return java.lang.String
	 */
	public static String formDateTime(Date date, String pattern) {
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
		return parseDate(date, DATE_LONG);
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
}
