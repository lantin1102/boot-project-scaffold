package com.lantin.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// 方法引用转为函数式接口
public class FunctionCaster {

	public static  <T> Predicate<T> asPredicate(Predicate<T> p) {
		return p;
	}

	public static void main(String[] args) {

		List<String> stringList = new ArrayList<>();
		stringList.add("a");
		stringList.add("   ");
		stringList.add("c");
		stringList.add("  ");
		stringList.add("d");

		List<String> collect = stringList.stream()
				.filter(FunctionCaster.asPredicate(String::isBlank).negate())
				.toList();

		System.out.println(collect);


	}
}
