package com.lantin.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.account.Account;
import org.junit.jupiter.api.Test;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 20:13 周六
 */
public class CommonTest {


	@Test
	public void test() throws JsonProcessingException {
		Account account = new Account();
		CommonResponse response = new CommonResponse(account);
		String s = JSON.toJSONString(response);
		System.out.println(s);
		ObjectMapper objectMapper = new ObjectMapper();

		String s1 = objectMapper.writeValueAsString(response);

		System.out.println(s1);
	}

	@Test
	public void numberTest() {
		int b = -1 << 5;
		// -1^其他数可以用 ~其他数来标示
		int a = ~b;
		// ~(-1<< x) 可以快速计算x位的二进制数能表示的最大十进制数为多少
		// -1<<-1 表示在当前的数字类型所能表示的最小数的值
		// 例如 -1<<-1 在int 类型 表示 -2147483648 相当于 -1<<31
		System.out.println(-1 << -1);
		System.out.println(a);
		System.out.println(b);
	}
}
