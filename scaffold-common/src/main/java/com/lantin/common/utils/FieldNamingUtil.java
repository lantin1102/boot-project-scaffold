package com.lantin.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldNamingUtil {

	private static final Pattern camelCasePattern = Pattern.compile("[A-Z]");
	private static final Pattern underScorePattern = Pattern.compile("_(\\w)");


	public static Map<String, Object> convertKeysToUnderScore(Map<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<>();
		paramMap.forEach((k, v) -> map.put(camelCaseToUnderScore(k), v));
		return map;
	}

	public static String camelCaseToUnderScore(String source) {
		Matcher matcher = camelCasePattern.matcher(source);
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			matcher.appendReplacement(result, '_' + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(result);
		return result.toString();
	}

	public static String underScoreToCamel(String source) {
		Matcher matcher = underScorePattern.matcher(source);
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			matcher.appendReplacement(result, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(result);
		return result.toString();
	}

	public static void main(String[] args) {

		String str = "i_user_chance_fool";
		String s = underScoreToCamel(str);
		System.out.println(s);
		String s1 = camelCaseToUnderScore(s);
		System.out.println(s1);
	}
}
