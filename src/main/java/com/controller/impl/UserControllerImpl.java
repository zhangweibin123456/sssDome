package com.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.UserPO;
import com.commons.AppStateStore;
import com.controller.UserController;
import com.model.UserModel;
import com.service.UserService;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	@Resource(name = "appStateStore")
	private AppStateStore appStateStore;

	@RequestMapping("/findAll")
	public List<UserPO> findAll(@RequestBody UserModel userModel) {
		List<UserPO> userList = userService.findAll(userModel);
		return userList;

	}

	@Override
	@RequestMapping(value="/save", method = RequestMethod.POST)  
	public UserPO save(@RequestBody UserPO userPO) {
		UserPO save=userService.save(userPO);
		return save;
	}

	@Override
	@RequestMapping(value="/login", method = RequestMethod.POST)  
	public UserPO login(@RequestBody UserModel userModel) {
		//从sion中获取验证码
	String randomCode=	(String) appStateStore.getAttribute("randomCode");
		
	//userModel.
	
	
		
		return null;
	}

}
