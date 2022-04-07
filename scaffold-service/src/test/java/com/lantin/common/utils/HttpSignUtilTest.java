package com.lantin.common.utils;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

class HttpSignUtilTest {

	@Test
	void getSign() {
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_base_id","105233");
		// queryMap.put("game_id","7625");
		// queryMap.put("sdk_type","1");
		// queryMap.put("mid","3508041");
		// queryMap.put("uid","3508041");
		queryMap.put("ts","1648465099332");
		// queryMap.put("timestamp","1648465099332");
		queryMap.put("appkey","ZM7LjTpy8uJnowNK");
		// queryMap.put("type","json");
		// queryMap.put("name","昵称12");
		String signCode = HttpSignUtil.getSign(queryMap, true, "PkJq6CwQrqJk4oX2pqEu7rJghxBGcgfR",true);
		queryMap.put("sign",signCode);
		System.out.println(queryMap);

	}


	@Test
	public void reserveGame(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_id","1370");
		queryMap.put("uid","27515436");
		queryMap.put("platform","mobile");
		queryMap.put("timestamp",String.valueOf(System.currentTimeMillis()));
		queryMap.put("appkey","vYQLG9eGfdroVKV4");
		String signCode = HttpSignUtil.getSign(queryMap, true, "dyf5gx0NLJNJjTmAQsT3Qq9jZpXy8hYP",true);
		System.out.println("sign:"+signCode);
		queryMap.put("sign",signCode);
		System.out.println(queryMap);
	}
	@Test
	public void queryReserve(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("mid","27515435");
		queryMap.put("timestamp",String.valueOf(System.currentTimeMillis()));
		queryMap.put("appkey","vYQLG9eGfdroVKV4");
		String signCode = HttpSignUtil.getSign(queryMap, true, "dyf5gx0NLJNJjTmAQsT3Qq9jZpXy8hYP",true);
		queryMap.put("sign",signCode);
		System.out.println("sign:"+signCode);
		System.out.println(queryMap);
	}

	@Test
	public void riskEstimate(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("uid","3508041");
		queryMap.put("referer","https://www.bilibili.com/");
		queryMap.put("action_time","1649325341620");
		queryMap.put("activity_id","5894861864028416");
		queryMap.put("client_ip","10.24.109.222");
		// queryMap.put("platform","web");
		queryMap.put("choose","bili");
		queryMap.put("behavior","exchangeGift");
		queryMap.put("user_agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.82 Safari/537.36");
		queryMap.put("game_id","1");
		queryMap.put("username","3508041");
		queryMap.put("scene","activity");
		// queryMap.put("buvid","");
		queryMap.put("appkey","0c6f484ca0d51f9c69ee");
		queryMap.put("ts","1649325341625");
		String secret = "21b7abb4d8d360cf0b49e14d1bac7ea0";
		String signCode = HttpSignUtil.getSignWithOutUrlEncode(queryMap, true, secret);
		queryMap.put("sign",signCode);
		System.out.println("sign:"+signCode);
		System.out.println(queryMap);
	}
	@Test
	public void riskEstimate1(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("uid","27515433");
		queryMap.put("referer","https%3A%2F%2Fwww.bilibili.com%2F");
		queryMap.put("action_time","1649324460336");
		queryMap.put("buvid","");
		queryMap.put("activity_id","5894861864028416");
		queryMap.put("client_ip","10.24.109.222");
		queryMap.put("choose","bili");
		queryMap.put("behavior","exchangeGift");
		queryMap.put("user_agent","Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F99.0.4844.82%20Safari%2F537.36");
		queryMap.put("game_id","1");
		queryMap.put("username","3508041");
		queryMap.put("scene","activity");
		queryMap.put("appkey","0c6f484ca0d51f9c69ee");
		queryMap.put("ts","1649324460340");
		queryMap.put("platform","web");
		String secret = "21b7abb4d8d360cf0b49e14d1bac7ea0";
		String signCode = HttpSignUtil.getSignWithOutUrlEncode(queryMap, true, secret);
		queryMap.put("sign",signCode);
		System.out.println("sign:"+signCode);
		System.out.println(queryMap);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// int weekOfYear = Calendar.WEEK_OF_YEAR;
		// int year = Calendar.YEAR;
		// int date = Calendar.DATE;
		// int i = Calendar.getInstance().get(Calendar.DATE);
		// int i1 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		//
		// System.out.println(i);
		// System.out.println(i1);
		//
		// System.out.println(weekOfYear);
		// System.out.println(year);
		// LocalDateTime now = LocalDateTime.now();
		String decode = URLDecoder.decode("Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F99.0.4844.82%20Safari%2F537.36");

		System.out.println(decode);

	}
}