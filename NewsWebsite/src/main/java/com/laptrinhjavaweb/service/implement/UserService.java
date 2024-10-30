package com.laptrinhjavaweb.service.implement;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDao;
import com.laptrinhjavaweb.model.User;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDao userDAO;
	@Override
	public User findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		// TODO Auto-generated method stub
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
