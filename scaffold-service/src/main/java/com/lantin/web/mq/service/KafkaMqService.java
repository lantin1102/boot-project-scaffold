package com.lantin.web.mq.service;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMqService {

	@Value("${spring.kafka.activity.producer.topic}")
	String produceTopic;

	@Autowired
	private KafkaTemplate<String,String> template;

	public void send(MqMessageDTO messageDTO) {
		template.send(produceTopic, messageDTO.getKey(), JSON.toJSONString(messageDTO));
	}
}
