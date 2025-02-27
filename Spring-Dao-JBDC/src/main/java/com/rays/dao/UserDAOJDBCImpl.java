package com.rays.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rays.dto.UserDto;

@Repository
public class UserDAOJDBCImpl implements UserDAOInt{

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource = null;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	@Override
	public long add(UserDto dto) {
		String sql = "insert into user values(?, ?, ?, ?, ?)";

		int pk = jdbcTemplate.update(sql, dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getLogin(),
				dto.getPassword());
		return pk;
	}

	@Override
	public void update(UserDto dto) {
		String sql = "update user set first_name = ?, last_name = ?, login = ?, password = ? where id = ?";

		int i = jdbcTemplate.update(sql, dto.getFirstName(), dto.getLastName(), dto.getLogin(), dto.getPassword(),
				dto.getId());

		
	}

	@Override
	public void delete(long id) {
		String sql = "delete from user where id = ?";

		Object[] params = { id };

		int i = jdbcTemplate.update(sql, params);
	}

	@Override
	public UserDto findByLogin(String login) {
		String sql = "select id, first_name, last_name, login, password from user where login = ?";

		Object[] params = { login };
		UserDto user = jdbcTemplate.queryForObject(sql, params, new UserMapper());
		return user;
	}

	@Override
	public UserDto authenticate(String login, String password) {
		try {
			String sql = "select id, first_name, last_name, login, password from user where login = ? and password = ?";
			Object[] params = { login, password };
			UserDto user = jdbcTemplate.queryForObject(sql, params, new UserMapper());
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public UserDto findByPK(long pk) {
		String sql = "select id, first_name, last_name, login, password from user where id = ?";

		Object[] params = { pk };
		List list = jdbcTemplate.query(sql, params, new UserMapper());

		UserDto dto = null;

		if (list.size() > 0) {
			dto = (UserDto) list.get(0);
		}
		return dto;
	}

	@Override
	public List search(UserDto dto) {
		String sql = "select id, first_name, last_name, login, password from user";
		List l = jdbcTemplate.query(sql, new UserMapper());
		return l;
	}

	@Override
	public List search(UserDto dto, int pageNo, int pageSize) {
		String sql = "select id, first_name, last_name, login, password from user limit " + pageNo + "," + pageSize;
		List l = jdbcTemplate.query(sql, new UserMapper());
		return l;
	}

}
