package com.lantin.web.domain.account.dto;

import com.lantin.common.domain.base.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 21:01 周六
 */
@Data
public class AccountDto extends BaseDto {

	private Integer id;

	private String username;

	private Double money;

	private String idNumber;

	private LocalDateTime ctime;

	private LocalDateTime mtime;
}
