package com.lantin.web.domain.account;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lantin.common.domain.base.BasePo;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author Gan Luanqing
 */
@Data
@ToString(callSuper = true)
@TableName("account")
public class Account extends BasePo {

	private String username;

	private Double money;

	private String ext;
}
