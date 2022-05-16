package com.lantin.web.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

// @Component
@Slf4j
public class FirstTask {



	@Scheduled(cron = "0/5 * * * * *")
	public void run(){
		log.info("定时任务执行了");
	}
}
