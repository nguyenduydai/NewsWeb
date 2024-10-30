package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.User;

public interface IUserDao extends GenericDao<User>{
	User findByUserNameAndPasswordAndStatus(String userName,String password,Integer status);
}
