package com.lantin.web.domain.book;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lantin.common.domain.base.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 23:40 周六
 */
@Data
@TableName("book")
public class Book extends BasePo {

	private String type;
	private String name;
	private String description;
}
