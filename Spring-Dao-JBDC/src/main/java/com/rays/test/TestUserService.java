package com.rays.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.rays.dto.UserDto;
import com.rays.service.UserServiceInt;

@Component("testUserService")
public class TestUserService {

	@Autowired
	public UserServiceInt service = null;

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		TestUserService test = (TestUserService) context.getBean("testUserService");

//		 test.testAdd();
//		 test.testUpdate();
//		 test.testDelete();
//		 test.testFindByPk();
//		 test.testAuth();
//		test.testSearch();

	}

	public void testAdd() {
		UserDto dto = new UserDto();
		dto.setId(3);
		dto.setFirstName("Pooja");
		dto.setLastName("Rathore");
		dto.setLogin("admin");
		dto.setPassword("pass1234");
		long pk = service.add(dto);
		System.out.println("Data Inserted... pk = " + pk);
	}

	public void testUpdate() {
		UserDto dto = new UserDto();
		dto.setId(1);
		dto.setFirstName("yashmita");
		dto.setLastName("rathore");
		dto.setLogin("yashmita@gmail.com");
		dto.setPassword("pass1234");
		service.update(dto);
		System.out.println("Data updated");
	}

	private void testDelete() {
		service.delete(1L);
	}

	public void testFindByPk() {
		UserDto dto = service.findByPK(1);
		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.println("\t" + dto.getPassword());
		} else {
			System.out.println("User ID not exist..!!!");
		}
	}

	public void testAuth() {
		UserDto dto = service.authenticate("admin", "pass1234");
		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.println("\t" + dto.getPassword());
		} else {
			System.out.println("User not exist");
		}
	}

	public void testSearch() {

		UserDto dto = new UserDto();

		List<UserDto> list = service.search(dto, 0, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			dto = (UserDto) it.next();
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.println("\t" + dto.getPassword());
		}
	}
}