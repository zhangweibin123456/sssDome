package com.service;

import java.util.List;

import com.bean.UserPO;
import com.model.UserModel;

public interface UserService {

	public List<UserPO> findAll(UserModel userModel);
	
	public UserPO save(UserPO userPO);
	
	public UserPO login(UserModel userModel);
	
}
