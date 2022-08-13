package com.lantin.web.mq.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActivityMessageConsumer {


	// 单个消费
	@KafkaListener(topics = {"${spring.kafka.activity.consumer.topic}"}, containerFactory = "activityKafkaListenerContainerFactory")
	public void listenActivity(ConsumerRecord<String,String > record) {
		log.info("on activity message:{},topic:{}", record.value(), record.topic());

	}


	// 批量消费
	@KafkaListener(topics = {"${spring.kafka.game-login.consumer.topic}"}, containerFactory = "gameLoginKafkaListenerContainerFactory")
	public void listenGameLogin(ConsumerRecords<String,String > records) {
		log.info("income size:{}",records.count());
		for (ConsumerRecord<String, String> record : records) {

			log.info("on gameLogin message:{},topic:{}", record.value(), record.topic());
		}
	}

}
