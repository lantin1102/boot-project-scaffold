package com.lantin.web.controller;


import com.lantin.common.domain.response.CommonResponse;
import com.lantin.common.enums.GeneralErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {


	@Autowired
	ApplicationContext ac;


	@PostMapping("cron/control")
	public CommonResponse<Void> configCron(@RequestParam("taskName") String taskName,
	                                       @RequestParam("cron") String cron) {


		Object bean = ac.getBean(taskName);
		Class<?> aClass = bean.getClass();
		Field cronField = ReflectionUtils.findField(aClass, "cron");
		if (cronField==null){
			return CommonResponse.failure(GeneralErrorCode.NO_BEAN_FOUND);
		}
		cronField.setAccessible(true);
		ReflectionUtils.setField(cronField,bean,cron);
		return CommonResponse.success();
	}

}
