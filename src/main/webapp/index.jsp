<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body style="background-color:#66B3FF">
<div style="width: 500px;height: 200px;margin: 200px 450px ;">
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label for="userName" class="col-sm-2 control-label">用 户 名</label>
			<div class="col-sm-8 col-md-6"  >
				<input type="text" class="form-control" id="firstname" placeholder="请输入用户名">
			</div>
		</div>
		<div class="form-group">
			<label for="passWord" class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
			<div class="col-sm-8 col-md-6">
				<input type="password" class="form-control" id="lastname" placeholder="请输入密码">
			</div>
		</div>
			<div class="form-group">
			<label for="verificationCode" class="col-sm-2 control-label">验 证 码</label>
			<div class="col-sm-4 col-md-3">
			<input type="text" class="form-control" id="verificationCode" placeholder="请输入验证码" >
			</div>
			<img id="yanzhen" src="driverControler/acquirerandomCode"  class="img-rounded" title="更换图片" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-5 col-md-4">
				<button type="submit" class="btn btn-success">登录</button>
				<button type="button" class="btn btn-success" id="clear">清空</button>
			</div>
			
		</div>
	</form>
	</div>
</body>
</html>
