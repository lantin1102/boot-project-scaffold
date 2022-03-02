package com.lantin.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Gan Luanqing
 * @date 2021/12/04 19:54 周六
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonData {

	String myName;
	Integer allNum;

	LocalDateTime dateTime;

	LocalDate date;

	LocalTime localTime;

	Boolean isSuccess;

	boolean isRight;

}
