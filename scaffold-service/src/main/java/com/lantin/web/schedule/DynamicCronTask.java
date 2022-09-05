package com.lantin.web.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

// @Component("dynamicCronTask")
@Slf4j
public class DynamicCronTask implements SchedulingConfigurer {

	private String cron;

	private static final String defaultCron = "0/5 * * * * *";

	DynamicCronTask() {
		cron = defaultCron;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(() -> log.info("dynamic Cron Task is running"),
				triggerContext -> {
					CronTrigger cronTrigger = new CronTrigger(cron);
					log.info("cron trigger use cron expr:[{}]",cron);
					return cronTrigger.nextExecutionTime(triggerContext);
				}
		);
	}
}
