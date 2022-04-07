package com.lantin.web.tx;

public class Parent {


	public static void main(String[] args) {

		Child child = new Child();
		Class<? extends Child> childClass = child.getClass();

		Parent parent = new Parent();
		Class<? extends Parent> parentClass = parent.getClass();

		boolean assignableFrom = Parent.class.isAssignableFrom(childClass);
		System.out.println(assignableFrom);
		System.out.println(parentClass.isAssignableFrom(childClass));

	}
}


class Child extends Parent{

}
