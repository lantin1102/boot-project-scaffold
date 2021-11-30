package com.lantin.web.domain.book.vo;

import com.lantin.common.domain.base.BaseVo;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = "类型不能为空")
	private String type;
	//@ApiModelProperty("书籍名称")
	@NotBlank(message = "名称不能为空")
	private String name;
	//@ApiModelProperty("书籍描述")
	private String description;
}
