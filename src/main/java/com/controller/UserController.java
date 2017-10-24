package com.controller;

import java.util.List;

import com.bean.UserPO;
import com.model.UserModel;

public interface UserController {
	public List<UserPO> findAll(UserModel userModel);
	
	public UserPO save(UserPO userPO);
	
	public UserPO login(UserModel userModel);
}
