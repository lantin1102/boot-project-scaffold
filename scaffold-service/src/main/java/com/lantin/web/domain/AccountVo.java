package com.lantin.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Gan Luanqing
 * @date 2021/12/12 0:51 周日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountVo implements Serializable {
	/**
	 * 主键id
	 */
	private Integer id;

	/**
	 * 用户姓名
	 */
	private String username;

	/**
	 * 账户余额
	 */
	private Double money;

	private String ext;
}
