package com.lantin.web.controller;


import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.mq.service.KafkaMqService;
import com.lantin.web.mq.service.MqMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq/kafka")
public class KafkaMessageController {

	@Autowired
	private KafkaMqService kafkaMqService;

	@PostMapping("/send")
	public CommonResponse<Void> send(@RequestBody MqMessageDTO messageDTO) {

		kafkaMqService.send(messageDTO);
		return CommonResponse.success();
	}

}
