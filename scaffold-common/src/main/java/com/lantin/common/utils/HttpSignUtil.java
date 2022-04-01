package com.lantin.common.utils;

import org.springframework.util.DigestUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HttpSignUtil {


	public static String getSign(Map<String, String> queryMap,boolean useSecret,String secret) {
		Map<String, String> sortedMap = new TreeMap<>(queryMap);
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			value = value == null ? "" : value;
			value = URLEncoder.encode(value, StandardCharsets.UTF_8);
			// System.out.println(value);
			sb.append(key).append("=").append(value).append("&");
		}
		String strToEncrypt = sb.toString();
		if (strToEncrypt.length() > 0) {
			strToEncrypt = strToEncrypt.substring(0, strToEncrypt.length() - 1);
		}
		// System.out.println(strToEncrypt);
		return DigestUtils.md5DigestAsHex(String.format("%s%s", strToEncrypt, useSecret ? secret : "").getBytes(StandardCharsets.UTF_8));
	}

	public static void main(String[] args) {
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_id","5402");
		queryMap.put("uid","3508041");
		queryMap.put("ts","1647851374432");
		queryMap.put("appkey","klnMm4IwB0wVKd5M");
		queryMap.put("name","昵称12");
		String md5Sign = getSign(queryMap, true, "NMl2lj4eJK2plFiXJ1Di3ECFW8zykCVg");
		System.out.println(md5Sign);
	}

}
