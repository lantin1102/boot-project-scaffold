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
}
