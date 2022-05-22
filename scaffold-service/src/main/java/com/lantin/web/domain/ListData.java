package com.lantin.web.domain;


import lombok.Data;

import java.util.List;

@Data
public class ListData {

	private String name;

	private List<String> numbers;
}
