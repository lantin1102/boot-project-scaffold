package com.lantin.web.domain.book.dto;

import com.lantin.common.domain.base.BaseDto;
import lombok.Data;

import java.io.Serial;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 23:50 周六
 */
@Data
public class BookDto extends BaseDto {
	@Serial
	private static final long serialVersionUID = -1L;
	private Integer id;
	private String type;
	private String name;
	private String description;
}
