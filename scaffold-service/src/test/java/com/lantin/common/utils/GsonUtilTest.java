package com.lantin.common.utils;

import com.lantin.web.domain.JsonData;
import com.lantin.web.domain.JsonSerializeTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gan Luanqing
 * @date 2022/03/01 11:39 周二
 */
class GsonUtilTest {
	@Test
	public void test() {

		JsonData jsonData = new JsonData();
		// jsonData.setDate(LocalDate.now());
		jsonData.setDateTime(LocalDateTime.now());
		// jsonData.setLocalTime(LocalTime.now());
		jsonData.setMyName("zhangsan");
		jsonData.setAllNum(25);
		System.out.println(jsonData);

		// json转换 驼峰

		// String jsonJsonStr = GsonUtil.toJsonStr(jsonData);
		// System.out.println(jsonJsonStr);
		// JsonData jsonData1 = GsonUtil.fromJson(jsonJsonStr, JsonData.class);
		// System.out.println(jsonData1);

		// json转换 下划线
		String jsonJsonStr = GsonUtil.toJsonStrWithUnderScores(jsonData);
		System.out.println(jsonJsonStr);
		JsonData jsonData1 = GsonUtil.fromJsonWithUnderScores(jsonJsonStr, JsonData.class);
		System.out.println(jsonData1);

		// JsonElement jsonElement = GsonUtil.toJsonTree(jsonData);
		// GsonUtil.toJsonTreeWithUnderScores(jsonData);
		// System.out.println("jsonElement toString:" + jsonElement.toString());
		// System.out.println("jsonElement getAsString:" + jsonElement.getAsString());
		// JsonElement jsonElement1 = GsonUtil.toJsonTreeWithUnderScores(jsonData);
		// System.out.println(jsonElement1);
	}

	@Test
	public void test2() {

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("myName", "zjanmgsan");
		dataMap.put("myAge", 25);
		dataMap.put("dateTime", LocalDateTime.now());
		System.out.println(dataMap);

		String jsonJsonStr = GsonUtil.toJsonStr(dataMap);
		System.out.println("JsonStr:" + jsonJsonStr);
		Map<String, Object> jsonData1 = GsonUtil.fromJson(jsonJsonStr, HashMap.class);
		System.out.println(jsonData1);
	}

	@Test
	public void test3() throws Exception {
		//     null字段测试
		JsonData jsonData = new JsonData();
		jsonData.setMyName("lisi");
		String s = GsonUtil.toJsonStr(jsonData);
		System.out.println(s);
		String s1 = JsonUtils.fastObj2json(jsonData);
		System.out.println(s1);
	}

	@Test
	public void test4() {
		JsonData jsonData = new JsonData();
		jsonData.setMyName("lisi");
		String s = JsonUtils.jacksonObj2json(jsonData);
		System.out.println(s);
		String s1 = JsonUtils.jacksonObj2json(null);
		System.out.println(s1);
		List<JsonData> listData = new ArrayList<>();
		listData.add(new JsonData() {{
			setMyName("zhangasn");
		}});
		listData.add(new JsonData() {{
			setMyName("lisi");
		}});
		listData.add(new JsonData() {{
			setMyName("wangwu");
		}});

		String s2 = JsonUtils.jacksonObj2json(listData);
		System.out.println(s2);

		List list1 = JsonUtils.jacksonParseJson(s2, List.class);
		List<JsonData> list2 = JsonUtils.jacksonParseJsonArray(s2, JsonData.class);
		System.out.println("list1:"+list1);
		System.out.println("list2:"+list2);

		String emptyJson= null;

		List list3 = JsonUtils.jacksonParseJson(emptyJson, List.class);
		List<JsonData> list4 = JsonUtils.jacksonParseJsonArray(emptyJson, JsonData.class);
		System.out.println("list3:"+list3);
		System.out.println("list4:"+list4);


	}

	@Test
	public void testBoolean() throws Exception {
		JsonData data = new JsonData();
		data.setMyName("zhangsan");
		data.setIsSuccess(true);

		String strFast = JsonUtils.fastObj2json(data);
		System.out.println("fastjson:"+strFast);
		String strJackson = JsonUtils.jacksonObj2json(data);
		System.out.println("jackson:"+strJackson);
		String strGson = GsonUtil.toJsonStr(data);
		System.out.println("gson:"+strGson);


		String sourceStr = "{\"myName\":\"zhangsan\",\"isSuccess\":true}";

	}

	@Test
	public void testBoolean1() throws Exception {
		JsonSerializeTest data = new JsonSerializeTest();
		data.setSuccess(true);
		String strFast = JsonUtils.fastObj2json(data);
		System.out.println("fastjson:"+strFast);
		String strJackson = JsonUtils.jacksonObj2json(data);
		System.out.println("jackson:"+strJackson);
		String strGson = GsonUtil.toJsonStr(data);
		System.out.println("gson:"+strGson);
	}
}