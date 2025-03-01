package com.rays.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

/*@Repository*/
/* @Primary */
public class UserDAOJDBCImpl implements UserDAOInt {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public long add(UserDTO dto) {

		String sql = "insert into user values(?, ?, ?, ?, ?)";

		int pk = jdbcTemplate.update(sql, dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getLogin(),
				dto.getPassword());
		return pk;
	}

	public void update(UserDTO dto) {

		String sql = "update user set first_name = ?, last_name = ?, login = ?, password = ? where id = ?";

		int i = jdbcTemplate.update(sql, dto.getFirstName(), dto.getLastName(), dto.getLogin(), dto.getPassword(),
				dto.getId());

	}

	public void delete(long id) {

		String sql = "delete from user where id = ?";

		Object[] params = { id };

		int i = jdbcTemplate.update(sql, params);
	}

	public UserDTO findByLogin(String login) {

		String sql = "select id, first_name, last_name, login, password from user where login = ?";

		Object[] params = { login };
		UserDTO user = jdbcTemplate.queryForObject(sql, params, new UserMapper());
		return user;
	}

	public UserDTO findByPK(long pk) {

		String sql = "select id, first_name, last_name, login, password from user where id = ?";

		Object[] params = { pk };
		List list = jdbcTemplate.query(sql, params, new UserMapper());

		UserDTO dto = null;

		if (list.size() > 0) {
			dto = (UserDTO) list.get(0);
		}
		return dto;
	}

	public UserDTO authenticate(String login, String password) {
		try {
			String sql = "select id, first_name, last_name, login, password from user where login = ? and password = ?";
			Object[] params = { login, password };
			UserDTO user = jdbcTemplate.queryForObject(sql, params, new UserMapper());
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List search(UserDTO dto) {
		String sql = "select id, first_name, last_name, login, password from user";
		List l = jdbcTemplate.query(sql, new UserMapper());
		return l;
	}

	public List search(UserDTO dto, int pageNo, int pageSize) {
		String sql = "select id, first_name, last_name, login, password from user limit " + pageNo + "," + pageSize;
		List l = jdbcTemplate.query(sql, new UserMapper());
		return l;
	}

	public UserDTO auth(String login, String password) {
		return null;
	}

}