package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDto;

@Service
public class UserServiceImpl implements UserServiceInt {

	@Autowired
	private UserDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(UserDto dto) {

		long pk = dao.add(dto);
		/*
		 * if (dto.getLogin().equals("admin")) { throw new
		 * RuntimeException("User with login 'admin' not allowed."); }
		 */
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDto dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	public UserDto findByLogin(String login) {
		return dao.findByLogin(login);
	}

	@Transactional(readOnly = true)
	public UserDto findByPK(long pk) {
		return dao.findByPK(pk);
	}

	@Transactional(readOnly = true)
	public UserDto authenticate(String login, String password) {
		UserDto user = dao.authenticate(login, password);
		return user;
	}

	@Transactional(readOnly = true)
	public List search(UserDto dto) {
		return dao.search(dto);
	}

	@Transactional(readOnly = true)
	public List search(UserDto dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}
}