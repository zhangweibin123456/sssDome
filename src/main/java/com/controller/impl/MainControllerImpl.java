package com.controller.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.SysUserPO;
import com.controller.MainController;
import com.model.BaseResponse;
import com.service.SysUserService;
import com.utils.SessionUtils;

@RestController
@RequestMapping("/main")
public class MainControllerImpl implements MainController {

	@Autowired
	private SysUserService sysUserService;
	

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@RequestMapping("toLogin")
	public BaseResponse toLogin(String userName,String passWord, HttpServletRequest request, HttpServletResponse response) {
		BaseResponse baseResponse=new BaseResponse();
		if (StringUtils.isEmpty(userName)) {
			baseResponse.setStatus("999");
			baseResponse.setMessage("用户名不能为空！");
			return baseResponse;
		}

		if (StringUtils.isEmpty(passWord)) {
				baseResponse.setStatus("999");
				baseResponse.setMessage("密码不能为空！");
				return baseResponse;
		}

		String msg = "用户登录日志:";

		SysUserPO sysUserPO = sysUserService.queryLogin(userName, passWord);

		if (sysUserPO == null) {
			// 记录错误登录日志
			logger.info("{}[{}] 账号或者密码输入错误.",msg,userName);
			baseResponse.setStatus("999");
			baseResponse.setMessage("用户名或密码错误！");
			return baseResponse;
		}
		
		
		//设置User到Session
		SessionUtils.setUser(request, sysUserPO);

		return baseResponse;
	}

}
