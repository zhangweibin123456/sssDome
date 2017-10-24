package com.service;

import com.bean.SysUserPO;

public interface SysUserService {

	
	public SysUserPO queryLogin(String userName,String passWord);
	
	
}
