package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bean.SysUserPO;
import com.repo.SysUserRepository;
import com.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserRepository sysuserRepository;

	@Override
	public SysUserPO queryLogin(String userName, String passWord) {
		Assert.notNull(userName, "[Assertion failed] - this argument [userName] is required; it must not be null");
		Assert.notNull(passWord, "[Assertion failed] - this argument [passWord] is required; it must not be null");
		SysUserPO po = sysuserRepository.findByUserNameAndPassWord(userName, passWord);
		return po;
	}

}
