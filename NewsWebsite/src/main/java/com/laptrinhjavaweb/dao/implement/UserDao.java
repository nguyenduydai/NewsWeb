package com.laptrinhjavaweb.dao.implement;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDao;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.News;
import com.laptrinhjavaweb.model.User;

public class UserDao extends AbstractDao<User> implements IUserDao  {

	@Override
	public User findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<User> users= query(sql.toString(),new UserMapper(),userName,password,status);
		return users.isEmpty() ? null : users.get(0);
	}

}
