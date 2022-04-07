package com.lantin.mock.web.service;

import com.lantin.web.service.StaticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(value = PowerMockRunner.class)
@PrepareForTest({StaticService.class})
public class DemoTest2 {


	@Test
	public void test(){
		System.out.println(113213);
	}
}
