package com.lantin.common.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lantin.common.utils.JsonUtils;

import java.io.Serializable;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 19:53 周六
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVo implements Serializable {


	@Override
	public String toString() {
		try {
			return JsonUtils.obj2json(this);
		} catch (Exception ignored) {
		}
		return "";
	}
}
