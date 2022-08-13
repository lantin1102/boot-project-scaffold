package com.lantin.web.remote;


import lombok.Data;

import java.io.Serializable;

@Data
public class LivelinkRequestBody implements Serializable {

	private String flowId;

	private String serialCode;

	// @Override
	// public String toString() {
	// 	return JsonUtils.fastObj2json(this);
	// }
}
