package com.rays.instance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServiceLocator {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("instance.xml");

		ServiceLocator locator = (ServiceLocator) context.getBean("locator");

		ServiceLocator s = locator.getInstance();

	}
	
}
