package com.lantin.web.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Gan Luanqing
 * @date 2021/12/04 19:54 周六
 */
@Data
public class DemoA {

	LocalDateTime dateTime;

	LocalDate data;

	LocalTime localTime;

	public static void main(String[] args) {
		switchString(null);
	}


	static void switchString(String str){
		switch (str){
			case "sth":
				System.out.println("sth");
				break;
			case "null":
				System.out.println("null");
				break;
			default:
				System.out.println("什么都没干");
		}
	}
}
