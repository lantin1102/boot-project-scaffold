package com.lantin.common.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lantin.common.serialier.CustomTimeStampSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 0:15 周日
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaseResponse implements Serializable {

	protected Integer code;

	protected String message;
	/**
	 * 序列化为long 时间戳
	 */
	@JsonSerialize(using = CustomTimeStampSerializer.class)
	protected LocalDateTime ts;

	protected String requestId;

	public BaseResponse() {
		this.ts = LocalDateTime.now();
	}
}
