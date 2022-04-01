package com.lantin.web.controller.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lantin.web.domain.DemoA;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class DemoControllerTest {

	@Test
	void testTime() throws JsonProcessingException {
		DemoA demoA = new DemoA();
		demoA.setDate(LocalDate.now());
		demoA.setDateTime(LocalDateTime.now());
		demoA.setLocalTime(LocalTime.now());
		ObjectMapper objectMapper = new ObjectMapper();

		String s = objectMapper.writeValueAsString(demoA);
		System.out.println(s);
	}
}