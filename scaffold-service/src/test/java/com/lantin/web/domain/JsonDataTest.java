package com.lantin.web.domain;

import com.lantin.common.utils.FieldNamingUtil;
import com.lantin.common.utils.JsonUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

class JsonDataTest {



	@Test
	public void test(){
		JsonData jsonData = new JsonData();
		jsonData.setDate(LocalDate.now());
		jsonData.setDateTime(LocalDateTime.now());
		jsonData.setMyName("zhangsan");
		jsonData.setLocalTime(LocalTime.now());
		jsonData.setAllNum(32);
		jsonData.setRight(true);
		jsonData.setIsSuccess(false);

		String s = JsonUtils.jacksonObj2json(jsonData);
		System.out.println(s);

		Map<String, Object> stringObjectMap = JsonUtils.fastJson2map(s);
		System.out.println(stringObjectMap);
		Map<String, Object> paramMap = FieldNamingUtil.convertKeysToUnderScore(stringObjectMap);
		System.out.println(paramMap);
	}

}