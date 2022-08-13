package com.lantin.web.remote.interceptor;


import com.lantin.common.utils.EncryptUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


@Component
public class LivelinkFeignInterceptor implements RequestInterceptor {

	@Value("${feign.client.livelink.secretKey}")
	private String secretKey;

	@Override
	public void apply(RequestTemplate template) {

		Map<String, Collection<String>> queries = template.queries();
		String sign = getLivelinkSign(queries);
		template.query("sig", Collections.singleton(sign));
		System.out.println(new String(template.body()));
	}

	private String getLivelinkSign(Map<String, Collection<String>> queries) {
		TreeMap<String, String> signNeedParams = new TreeMap<>();
		for (Map.Entry<String, Collection<String>> entry : queries.entrySet()) {
			String name = entry.getKey();
			Collection<String> valueC = entry.getValue();
			// apiName不参与签名
			if ("apiName".equals(name)) {
				continue;
			}
			String value = valueC.isEmpty() ? "" : valueC.iterator().next();
			signNeedParams.put(name, value);
		}
		StringBuilder sb = new StringBuilder();
		signNeedParams.values().forEach(value -> sb.append(value).append("+"));
		sb.append(secretKey);
		return EncryptUtils.getMD5(sb.toString());
	}

	public static void main(String[] args) {



	}

}
