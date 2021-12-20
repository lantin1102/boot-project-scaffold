package com.lantin.web.domain.activity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Gan Luanqing
 * @date 2021/12/19 14:05 周日
 */
@Data
public class UserReceiveRecordDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String recordId;

	private String prizeId;

	private LocalDateTime receiveTime;

	private Long uid;
}
