package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.User;

public interface IUserService {
	User findByUserNameAndPasswordAndStatus(String userName,String password,Integer status);
}
