package com.rays.bean;

public class UserBean {

	private String login = null;
	private String passward = null;

	public UserBean(String login, String password) {
		this.login = login;
		this.passward = password;
	}
	
	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) { 
		this.passward = passward;
	}
	
}
