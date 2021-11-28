package com.lantin.web.domain.book.vo;

import com.lantin.common.domain.base.BaseVo;
import lombok.Data;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 23:48 周六
 */
@Data
// @ApiModel("书籍信息Vo")
public class BookVo extends BaseVo {

	//@ApiModelProperty("书籍id")
	private Integer id;
	//@ApiModelProperty("书籍类型")
	private String type;
	//@ApiModelProperty("书籍名称")
	private String name;
	//@ApiModelProperty("书籍描述")
	private String description;
}
