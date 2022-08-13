package com.lantin.web.remote;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lantin.common.utils.JsonUtils;
import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

class LivelinkApiTest extends BaseSpringBootTest {

	@Autowired
	private LivelinkApi livelinkApi;

	@Test
	void submitFlow() {
		String code = "orHO%2F%2BI3bcF3M1HUn7btRLV4ctGv2R%2BnZwfiMqCV%2Fa5LyEE%2FUO0oQ7K4hL3tTHiW";
		LivelinkRequestParam param = LivelinkRequestParam.create()
				.apiName("ApiRequest")
				.actId("2899")
				.gameId("hyrz")
				.code(code);

		Map<String, Object> stringObjectMap = JsonUtils.fastJson2map(JsonUtils.fastObj2json(param));
		LivelinkRequestBody body = new LivelinkRequestBody();
		body.setFlowId("56w22o9x");
		body.setSerialCode(UUID.randomUUID().toString().replace("-",""));

		JsonObject jsonObject = livelinkApi.submitFlow(stringObjectMap,body);
		JsonElement apiName = jsonObject.get("apiName");
		String s = apiName.toString();

		String asString = apiName.getAsString();
		System.out.println("toString :"+s);
		System.out.println("get as string:"+asString);
		System.out.println(jsonObject);
	}

	@Test
	public void test111(){

		Type type = new TypeReference<LivelinkRequestBody>() {
		}.getType();

		System.out.println(type);

	}



	@AfterEach
	public void afterTest() throws InterruptedException {
		Thread.sleep(1000*60);
	}


}