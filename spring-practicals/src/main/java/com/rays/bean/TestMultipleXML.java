package com.rays.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMultipleXML {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"UserBean.xml","Person.xml" });
		
		UserBean bean = (UserBean) context.getBean("user");
		Person person = (Person) context.getBean("personBean");
		
		System.out.println(bean.getLogin());
		System.out.println(bean.getPassward());

		System.out.println(person.getName());

		
	}
	
}
