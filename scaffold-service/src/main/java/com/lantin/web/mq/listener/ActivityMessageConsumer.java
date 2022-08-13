package com.lantin.web.mq.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActivityMessageConsumer {


	@KafkaListener(topics = {"${spring.kafka.activity.consumer.topic}"}, containerFactory = "activityKafkaListenerContainerFactory")
	public void listenActivity(ConsumerRecord<String, String> record) {

		log.info("on activity message:{},topic:{}", record.value(), record.topic());

	}

	@KafkaListener(topics = {"${spring.kafka.game-login.consumer.topic}"}, containerFactory = "gameLoginKafkaListenerContainerFactory")
	public void listenGameLogin(ConsumerRecord<String, String> record) {
		log.info("on gameLogin message:{},topic:{}", record.value(), record.topic());

	}

}
