package com.lantin.common.utils;

import com.google.gson.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Gan Luanqing
 * @date 2022/03/01 11:25 周二
 */
public class GsonUtil {
	private final static Gson gson;
	private final static Gson underScoreGson;
	// 支持解析java8 时间类
	final static JsonSerializer<LocalDateTime> localDateTimeSerializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateUtils.FORMATTER_DATE_TIME));
	final static JsonSerializer<LocalDate> localDateSerializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateUtils.FORMATTER_DATE));
	final static JsonSerializer<LocalTime> localTimeSerializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateUtils.FORMATTER_TIME));
	final static JsonDeserializer<LocalDateTime> localDateTimeDeserializer = (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateUtils.FORMATTER_DATE_TIME);
	// (json, typeOfT, context) -> LocalDateTime.parse(json.toString(), DateUtils.FORMATTER_DATE_TIME);
	final static JsonDeserializer<LocalDate> localDateDeserializer = (json, typeOfT, context) -> LocalDate.parse(json.getAsString(), DateUtils.FORMATTER_DATE);
	final static JsonDeserializer<LocalTime> localTimeDeserializer = (json, typeOfT, context) -> LocalTime.parse(json.getAsString(), DateUtils.FORMATTER_TIME);

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.enableComplexMapKeySerialization()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.registerTypeAdapter(LocalDateTime.class, localDateTimeSerializer)
				.registerTypeAdapter(LocalDate.class, localDateSerializer)
				.registerTypeAdapter(LocalTime.class, localTimeSerializer)
				.registerTypeAdapter(LocalDateTime.class, localDateTimeDeserializer)
				.registerTypeAdapter(LocalDate.class, localDateDeserializer)
				.registerTypeAdapter(LocalTime.class, localTimeDeserializer);
		gson = gsonBuilder.create();
		underScoreGson = gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
	}

	/**
	 * @param json      jsonStr
	 * @param beanClass 目标类
	 */
	public static <T> T fromJson(String json, Class<T> beanClass) {
		return gson.fromJson(json, beanClass);
	}

	/**
	 * @param json      jsonStr
	 * @param beanClass 目标类
	 */
	public static <T> T fromJsonWithUnderScores(String json, Class<T> beanClass) {
		return underScoreGson.fromJson(json, beanClass);
	}

	/**
	 * 转换为JSON树 带下划线
	 *
	 * @param data 元数据
	 * @return json对象
	 */
	public static JsonElement toJsonTreeWithUnderScores(Object data) {
		return underScoreGson.toJsonTree(data);
	}

	/**
	 * 转换为JSON树
	 *
	 * @param data 元数据
	 * @return json对象
	 */
	public static JsonElement toJsonTree(Object data) {
		return gson.toJsonTree(data);
	}

	/**
	 * POJO对象转为json字符串
	 *
	 * @param src 元数据
	 * @return json字符串
	 */
	public static <T> String toJsonStr(T src) {
		return gson.toJson(src);
	}

	/**
	 * POJO对象转为json字符串 下划线模式
	 *
	 * @param src 元数据
	 * @return 下划线json字符串
	 */
	public static <T> String toJsonStrWithUnderScores(T src) {
		return underScoreGson.toJson(src);
	}
}
