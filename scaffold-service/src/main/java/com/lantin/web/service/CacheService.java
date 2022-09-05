package com.lantin.web.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CacheService {

	@Cacheable(value = "stringName", key = "#name",unless = "#result.empty")
	public List<String> getNames(String name) {
		if (StringUtils.isEmpty(name)) {
			return new ArrayList<>();
		}
		return new ArrayList<>(List.of(name));

	}

}
