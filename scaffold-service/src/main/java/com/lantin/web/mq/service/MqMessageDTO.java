package com.lantin.web.mq.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * describe:
 *
 * @author wanghaibin
 * @date 2018/03/29
 */
@Data
@NoArgsConstructor
public class MqMessageDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 2857901514761279036L;
	private String key; // 主要用来作为 kafka 的 key
	private String action;
	private String data;

	public MqMessageDTO(String action, String data) {
		this.action = action;
		this.data = data;
	}

	public MqMessageDTO(String key, String action, String data) {
		this(action, data);
		this.key = key;
	}
}
