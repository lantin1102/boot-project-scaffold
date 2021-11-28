package com.lantin.web.domain.account.vo;

import com.lantin.common.domain.base.BaseVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 21:00 周六
 */
@Data
public class AccountVo extends BaseVo {

	private Integer id;

	private String username;

	private Double money;

	private String idNumber;

	private LocalDateTime ctime;

	private LocalDateTime mtime;

}
