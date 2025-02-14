package com.rays.exclude;

public class TestAutoWire {

	private UserService userService;

	private AnotherService anotherService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAnotherService(AnotherService anotherService) {
		this.anotherService = anotherService;
	}

	public void performOperations() {
		userService.greet();
		anotherService.doSomething();
	}
	
}
