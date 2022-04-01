package com.lantin.common.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

class HttpSignUtilTest {

	@Test
	void getSign() {
		Map<String, String> queryMap = new HashMap<>();
		// queryMap.put("game_base_id","102216");
		queryMap.put("game_id","7625");
		// queryMap.put("sdk_type","1");
		// queryMap.put("mid","3508041");
		queryMap.put("uid","3508041");
		queryMap.put("ts","1648465099332");
		// queryMap.put("timestamp","1648465099332");
		queryMap.put("appkey","klnMm4IwB0wVKd5M");
		// queryMap.put("type","json");
		// queryMap.put("name","昵称12");
		String signCode = HttpSignUtil.getSign(queryMap, true, "NMl2lj4eJK2plFiXJ1Di3ECFW8zykCVg");
		queryMap.put("sign",signCode);
		System.out.println(queryMap);

	}

	public static void main(String[] args) {
		int weekOfYear = Calendar.WEEK_OF_YEAR;
		int year = Calendar.YEAR;
		int date = Calendar.DATE;
		int i = Calendar.getInstance().get(Calendar.DATE);
		int i1 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		System.out.println(i);
		System.out.println(i1);

		System.out.println(weekOfYear);
		System.out.println(year);
		LocalDateTime now = LocalDateTime.now();

	}
}