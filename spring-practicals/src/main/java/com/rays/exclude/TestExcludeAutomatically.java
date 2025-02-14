package com.rays.exclude;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestExcludeAutomatically {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("exclude.xml");

		TestAutoWire testAutowire = context.getBean(TestAutoWire.class);

		testAutowire.performOperations();

	}

}
