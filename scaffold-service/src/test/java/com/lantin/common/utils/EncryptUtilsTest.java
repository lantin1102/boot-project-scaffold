package com.lantin.common.utils;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;


public class EncryptUtilsTest {


	@Test
	public void test(){
		Provider[] providers = Security.getProviders();

		for (Provider provider : providers) {
			System.out.println(provider.getName());
		}
	}

	@Test
	public void test1(){

		// String uuid = UUID.randomUUID().toString().replace("-", "");
		String uuid = "a75f46c32979405d84b165c161a32f1c";
		String encode = encode(0, 3508041L, uuid);
		System.out.println("result:"+encode);
	}



	private static String encode(int x, Long uid, String joinId) {
		String first = uid.toString().substring(0, 1);
		int k = Integer.parseInt(first);
		String source = joinId + x * k;
		System.out.println(source);
		String base64 = Base64.getEncoder().encodeToString(source.getBytes(StandardCharsets.UTF_8));
		System.out.println(base64);
		return fixBase64(base64, uid);
	}
	private static String fixBase64(String fake, Long uid) {
		char[] firstFour = new char[4];
		fake.getChars(0, 4, firstFour, 0);
		String sub = fake.substring(4);
		if (uid % 2 == 0) {
			// swipe 2 4;
			char tmp = firstFour[1];
			firstFour[1] = firstFour[3];
			firstFour[3] = tmp;
		} else {
			// swipe 1 3
			char tmp = firstFour[0];
			firstFour[0] = firstFour[2];
			firstFour[2] = tmp;

		}
		return String.valueOf(firstFour).concat(sub);
	}
}