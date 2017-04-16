package com.zjt.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zjt.service.UserService;
@Controller
public class UserAction extends BaseAction{
	@Autowired
	private UserService usersvc;

	public void getAllUser(){
		System.out.println("查询所有的用户");
		writeJson(usersvc.getAllUser());
	}
}
