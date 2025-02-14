package com.rays.service;

import java.util.List;

import com.rays.dto.UserDto;

public interface UserServiceInt {

	public long add(UserDto dto);

	public void update(UserDto dto);

	public void delete(long id);

	public UserDto findByLogin(String login);

	public UserDto findByPK(long pk);

	public UserDto authenticate(String login, String password);

	public List search(UserDto dto);

	public List search(UserDto dto, int pageNo, int pageSize);


	
}
