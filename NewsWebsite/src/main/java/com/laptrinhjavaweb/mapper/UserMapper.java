package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.Role;
import com.laptrinhjavaweb.model.User;

public class UserMapper implements RowMapper<User>{
	public User mapRow(ResultSet rs) {
		User u=new User();
		try {
			u.setId(rs.getLong("id"));
			u.setUserName(rs.getString("username"));
			u.setFullName(rs.getString("fullname"));
			u.setPassword(rs.getString("password"));
			u.setStatus(rs.getInt("status"));
			try {
	            Role role=new Role();
	            role.setCode(rs.getString("code"));
	            role.setName(rs.getString("name"));
	            u.setRole(role);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
