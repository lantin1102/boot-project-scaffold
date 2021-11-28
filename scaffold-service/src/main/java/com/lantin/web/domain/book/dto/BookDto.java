package com.lantin.web.domain.book.dto;

import com.lantin.common.domain.base.BaseDto;
import lombok.Data;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 23:50 周六
 */
@Data
public class BookDto extends BaseDto {


	private Integer id;
	private String type;
	private String name;
	private String description;
}
