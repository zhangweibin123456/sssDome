<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>扫描识别系统- 用户登录</title>
<%@include file="/view/resource.jsp"%>
<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/user_login.css">
</head>
<body id="userlogin_body" height="100%">
	<form id="loginForm" action="main/toLogin" method="post"
		onsubmit="return false;">
		<div id="user_login_bj" align="center">
			<div id="user_logo"></div>
			<div id="user_login_t">
				<div id="user_login">
					<dl>
						<dd id="user_main" style="margin-top: 0px;">
							<ul>
								<li class="user_main">
									<div class="user_main_box">
										<ul style="height: 20px;"></ul>
										<ul>
											<li class="user_main_input"><input
												class="txtusernamecssclass easyui-validatebox"
												data-options="required:true" name="userName" id="userName"
												value="" maxlength="20"
												style='font-size: 14px; font-weight: bold;' /></li>
										</ul>
										<ul style="height: 18px;"></ul>
										<ul>
											<li class="user_main_input"><input
												class="txtpasswordcssclass easyui-validatebox"
												data-options="required:true,missingMessage:'密码不能为空.'"
												type="password" name="passWord" value="" id="passWord"
												style="font-size: 14px; font-weight: bold" /></li>
										</ul>
										<ul>
											<div style="height: 20px"></div>
											<div class="user_main_icon"></div>
											<input id="btnLogin" class="ibtn" style="border: 0px; position: relative; left: -30px; top: -5px;" type=image src="images/logo/dlan.png" />
										</ul>
									</div> 
								</li>
							</ul>
						</dd>
					</dl>
				</div>
				<div id="user_login_bottom"></div>
			</div>
		</div>
		<div></div>
	</form>
	<script type="text/javascript" src="js/ux/login.js"></script>
	<script>
		total = document.documentElement.clientHeight;
		colHeight = total - 134
				- document.getElementById("user_login_bj").offsetTop;
		;
		document.getElementById("user_login_bj").style.height = colHeight
				+ "px";

		var Sys = {};
		var ua = navigator.userAgent.toLowerCase();
		var s;
		var scan;
		(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
				.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
				.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
				.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
				.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

		if (Sys.chrome) {
			document.getElementById("topnav").style.display = "none";
		}
	</script>
</body>
</html>