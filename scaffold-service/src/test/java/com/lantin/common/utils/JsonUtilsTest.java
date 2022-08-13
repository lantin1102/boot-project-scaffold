package com.lantin.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {

	@Test
	void jsonToMap() {
	}

	@Test
	void fastJson2map() {

		String jsonStr = "{\"code\":0,\"name\":\"成功\"}";


		JsonTestDTO jsonTestDTO = JsonUtils.jacksonParseJson(jsonStr, JsonTestDTO.class);

		System.out.println(jsonTestDTO);


		String s = JsonUtils.jacksonObj2json(jsonTestDTO);
		System.out.println("jackson:"+s);
		String s1 = JsonUtils.fastObj2json(jsonTestDTO);
		System.out.println("fastjson:"+s1);

		String s2 = GsonUtil.toJsonStr(jsonTestDTO);
		System.out.println("gson:"+s2);


		JsonTestDTO jsonTestDTO1 = new JsonTestDTO();

		jsonTestDTO1.setCode(11);

		String s3 = JSON.toJSONString(jsonTestDTO1);

		System.out.println(s3);


		JsonTestDTO jsonTestDTO2 = JSON.parseObject(s3, JsonTestDTO.class);

		System.out.println(jsonTestDTO2);

	}

	@Data
	static class JsonTestDTO {

		String name;

		Integer code;

		public boolean success() {
			return code.equals(0);
		}

	}
}