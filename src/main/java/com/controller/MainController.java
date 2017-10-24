package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.BaseResponse;
import com.model.SysUserVO;

public interface MainController {

	public BaseResponse toLogin(String userName,String passWord,HttpServletRequest request,HttpServletResponse response);
}
