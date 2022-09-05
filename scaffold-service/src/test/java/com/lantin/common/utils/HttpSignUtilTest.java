package com.lantin.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class HttpSignUtilTest {

	@Test
	public void test(){

		ExecutorService executorService = Executors.newFixedThreadPool(100);




		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_base_id","103496,105667");
		String sign = HttpSignUtil.getSign(queryMap, "8f3550e0c04211e79ddafe210a2e3379");
		System.out.println(sign);

	}
	@Test
	void getSign() {
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("role_id","40697113608918");
		queryMap.put("cdkey","我是傻逼");
		queryMap.put("ts", String.valueOf(System.currentTimeMillis()));
		queryMap.put("server_id","10268");
		System.out.println(queryMap.get("ts"));
		String sign = HttpSignUtil.getSignWithOutUrlEncode(queryMap, "36521u54p3cc8");
		System.out.println(sign);

	}
	@Test
	public void getGameIds(){
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("game_base_id","183,102216");
		queryMap.put("appkey","ZM7LjTpy8uJnowNK");
		queryMap.put("ts", String.valueOf(System.currentTimeMillis()));
		String sign = HttpSignUtil.getSign(queryMap, "PkJq6CwQrqJk4oX2pqEu7rJghxBGcgfR");

		System.out.println(sign);

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
		queryMap.put("game_id","2270");
		queryMap.put("uid","3508041");
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
		String signCode = HttpSignUtil.getSignWithOutUrlEncode(queryMap, secret);
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
		String signCode = HttpSignUtil.getSignWithOutUrlEncode(queryMap, secret);
		queryMap.put("sign",signCode);
		System.out.println("sign:"+signCode);
		System.out.println(queryMap);
	}

	@Test

	public void liveLinkFlow(){
		Map<String, String> queryMap = new HashMap<>();
		String openId = "efc2c1a13a4e4cf6816ca68c320ed779";
		String encKey = "5cf8c22d98f7b475";
		HashMap<String, String> map = new HashMap<>();
		map.put("userid",openId);
		String userJson = JsonUtils.fastObj2json(map);
		queryMap.put("actId","2897");
		queryMap.put("gameId","lv");
		// queryMap.put("apiName","ApiRequest");
		queryMap.put("code",Base64Utils.encodeToString(EncryptUtils.encryptWithAES(userJson,encKey)));
		String code = queryMap.get("code");
		System.out.println(URLEncoder.encode(code));
		queryMap.put("livePlatId","bilibili");
		queryMap.put("logintype","qq");
		String nonce = UUID.randomUUID().toString().substring(0, 8);
		queryMap.put("nonce", nonce);
		System.out.println("nonce:"+nonce);
		String t = String.valueOf(System.currentTimeMillis()/1000);
		queryMap.put("t",t);
		System.out.println("t="+t);
		queryMap.put("v","2.0");
		String signSecret = "0420e6119e71bcf7";
		String signCode = HttpSignUtil.getLiveLinkSign(queryMap, signSecret);
		System.out.println("sign:"+signCode);
	}
}