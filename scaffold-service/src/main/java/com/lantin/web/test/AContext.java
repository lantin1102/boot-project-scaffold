package com.lantin.web.test;


public class AContext {


	private Initializer getInitializer(){
		// 方法引用 返回一个实现类
		return this::selfInitialize;
	}

	private int selfInitialize(String contextStr,Integer num){
		System.out.println("do sth with"+contextStr);
		// return contextStr;
		return num;
	}


	public static void main(String[] args) {


		AContext aContext = new AContext();

		Initializer initializer = aContext.getInitializer();

		initializer.onStartUp("dfg",10);
	}
}
