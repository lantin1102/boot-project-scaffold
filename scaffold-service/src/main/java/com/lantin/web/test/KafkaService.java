package com.lantin.web.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;



	// @PostConstruct
	// void initSend(){
	//
	// 	kafkaTemplate.send("abc-topic","hello");
	//
	// }



}
