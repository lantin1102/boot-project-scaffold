package com.lantin.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

class HttpSignUtilTest {

	@Test
	public void test(){
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
	}
	@Test
	void getSign() {
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("sdk_type","1");
		queryMap.put("game_base_id","97");
		queryMap.put("ts","1648465099332");
		queryMap.put("appkey","ZM7LjTpy8uJnowNK");
		String signCode = HttpSignUtil.getSign(queryMap, true, "PkJq6CwQrqJk4oX2pqEu7rJghxBGcgfR",true);
		System.out.println("sign:"+signCode);
		queryMap.put("sign",signCode);
		System.out.println(queryMap);
	}


	@Test
	public void reserveGame(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_id","1370");
		queryMap.put("uid","2751543699");
		queryMap.put("timestamp","1649313061187");
		queryMap.put("appkey","vYQLG9eGfdroVKV4");
		String signCode = HttpSignUtil.getSign(queryMap, true, "dyf5gx0NLJNJjTmAQsT3Qq9jZpXy8hYP",true);
		System.out.println("sign:"+signCode);
		queryMap.put("sign",signCode);
		System.out.println(queryMap);
	}

	@Test
	public void loginRecord(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_id","0");
		queryMap.put("uid","26778824");
		queryMap.put("ts","1648465099332");
		queryMap.put("appkey","klnMm4IwB0wVKd5M");
		String sign = HttpSignUtil.getSign(queryMap, true, "NMl2lj4eJK2plFiXJ1Di3ECFW8zykCVg",true);
		System.out.println("sign:"+sign);
	}
	@Test
	public void gameLastLogin(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_id","1524,113");
		queryMap.put("uid","26778824");
		queryMap.put("ts","1648465099332");
		queryMap.put("appkey","klnMm4IwB0wVKd5M");
		String sign = HttpSignUtil.getSign(queryMap, true, "NMl2lj4eJK2plFiXJ1Di3ECFW8zykCVg",true);
		System.out.println("sign:"+sign);
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

	@Test

	public void liveLinkFlow(){
		Map<String, String> queryMap = new HashMap<>();
		String openId = "3b942b39b840495e86a11eb5744951f0";
		String encKey = "5cf8c22d98f7b475";
		HashMap<String, String> map = new HashMap<>();
		map.put("userid",openId);
		String userJson = JsonUtils.fastObj2json(map);
		queryMap.put("actId","2583");
		// queryMap.put("apiName","ApiRequest");
		queryMap.put("code",Base64Utils.encodeToString(EncryptUtils.encryptWithAES(userJson,encKey)));
		System.out.println(queryMap.get("code"));
		queryMap.put("gameId","lgamem");
		queryMap.put("livePlatId","bilibili");
		queryMap.put("logintype","qq");
		queryMap.put("nonce","3NCDMt");
		queryMap.put("t","1655892961");
		queryMap.put("v","2.0");
		String signSecret = "0420e6119e71bcf7";
		String signCode = HttpSignUtil.getLiveLinkSign(queryMap, signSecret);
		String sign = HttpSignUtil.getSign(queryMap, signSecret);
		System.out.println("sign:"+signCode);
		System.out.println("common sign:"+sign);

		String x = "   ";

		boolean b = StringUtils.hasText(x);
		boolean b1 = StringUtils.hasLength(x);
		System.out.println(b1);
	}
}