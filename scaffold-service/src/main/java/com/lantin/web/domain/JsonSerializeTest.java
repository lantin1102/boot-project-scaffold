package com.lantin.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Gan Luanqing
 * @date 2022/03/01 16:12 周二
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonSerializeTest implements Serializable {
	boolean isSuccess;
	// boolean success;
	Boolean isRight ;
	Boolean right ;

	Integer isMan;
	int isAdult;
}
