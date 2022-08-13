package com.lantin.web.remote;


import com.google.gson.JsonObject;
import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;


public interface LivelinkApi {


	@RequestLine("POST /livelink")
	@Headers({"Content-Type: application/json"})
	JsonObject submitFlow(@QueryMap(encoded = true) Map<String, Object> paramMap, LivelinkRequestBody body);

}
