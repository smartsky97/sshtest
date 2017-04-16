package com.zjt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjt.entity.Users;
import com.zjt.entity.UsersDAO;
import com.zjt.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersDAO userDao;

	@Override
	public List<Users> getAllUser() {
		List<Users> us = new ArrayList<Users>();
		us = userDao.findAll();
		return us;
	}

}
