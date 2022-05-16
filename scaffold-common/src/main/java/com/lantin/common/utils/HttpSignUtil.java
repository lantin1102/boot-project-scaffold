package com.lantin.common.utils;

import org.springframework.util.DigestUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HttpSignUtil {

	/**
	 * 对参数map不作urlencode，可用于已经encode过的参数map加签
	 *
	 * @param queryMap 参数map
	 * @param useSecret 是否使用密钥
	 * @param secret 密钥
	 * @return
	 */
	public static String getSignWithOutUrlEncode(Map<String, String> queryMap, boolean useSecret, String secret) {
		Map<String, String> sortedMap = new TreeMap<>(queryMap);
		String strToEncrypt = getSignString(sortedMap, false, false);
		return digestSignCode(strToEncrypt, useSecret, secret);
	}

	private static String digestSignCode(String strToEncrypt, boolean useSecret, String secret) {
		if (strToEncrypt.length() > 0) {
			strToEncrypt = strToEncrypt.substring(0, strToEncrypt.length() - 1);
		}
		String format = String.format("%s%s", strToEncrypt, useSecret ? secret : "");
		System.out.println(format);
		return DigestUtils.md5DigestAsHex(format.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * @param queryMap       查询字符串map
	 * @param useSecret      是否使用secret
	 * @param secret         secret
	 * @param encodeBlankToPlus 如果为true 使用原生urlencode将空格编码为加号'+' 否则encode为'%20'
	 * @return sign
	 */
	public static String getSign(Map<String, String> queryMap, boolean useSecret, String secret, boolean encodeBlankToPlus) {
		Map<String, String> sortedMap = new TreeMap<>(queryMap);
		String strToEncrypt = getSignString(sortedMap, encodeBlankToPlus, true);
		return digestSignCode(strToEncrypt, useSecret, secret);
	}

	private static String getSignString(Map<String, String> sortedMap, boolean encodeBlankToPlus, boolean useUrlEncode) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (value == null) {
				value = "";
			}
			if (useUrlEncode) {
				value = urlEncode(value, encodeBlankToPlus);
			}
			sb.append(key).append("=").append(value).append("&");
		}
		return sb.toString();
	}

	private static String urlEncode(String str, boolean encodeBlankToPlus) {
		String encodedStr = URLEncoder.encode(str, StandardCharsets.UTF_8);
		if (encodeBlankToPlus) {
			return encodedStr;
		}
		String replace = encodedStr.replace("+", "%20");
		System.out.println(replace);
		return replace;
	}

	public static void main(String[] args) {
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_id", "5402");
		queryMap.put("uid", "3508041");
		queryMap.put("ts", "1647851374432");
		queryMap.put("appkey", "klnMm4IwB0wVKd5M");
		queryMap.put("name", "昵称12");
		String md5Sign = getSign(queryMap, true, "NMl2lj4eJK2plFiXJ1Di3ECFW8zykCVg", true);
		System.out.println(md5Sign);
	}

}
