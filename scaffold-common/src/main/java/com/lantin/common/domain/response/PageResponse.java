package com.lantin.common.domain.response;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantin.common.constant.BaseConstants;
import com.lantin.common.domain.base.BaseResponse;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 0:07 周日
 */
@Data
public class PageResponse<T> extends BaseResponse {

	private List<T> data;

	private Page<T> page;

	public PageResponse(List<T> data) {
		this(BaseConstants.COMMON_SUCCESS_CODE, BaseConstants.COMMON_SUCCESS_MSG, data);
	}

	public PageResponse(Page<T> page) {
		this(BaseConstants.COMMON_SUCCESS_CODE, BaseConstants.COMMON_SUCCESS_MSG, page.getRecords());
		this.page = page;
	}

	public PageResponse() {
		this(BaseConstants.COMMON_SUCCESS_CODE, BaseConstants.COMMON_SUCCESS_MSG, Collections.emptyList());
	}

	public PageResponse(Integer code, String message) {
		this(code, message, Collections.emptyList());
	}

	public PageResponse(Integer code, String message, List<T> data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
