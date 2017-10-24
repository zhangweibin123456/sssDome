<!DOCTYPE html>
<html>
	<head>
		<meta charset="gbk" />
		<title></title>
	<meta charset="gbk"> 
<title>菜鸟教程(runoob.com)</title> 
<script type="text/ecmascript" src="js/commons/jquery.min.js">
	</script>
	<script type="text/javascript" >
		

		
		
	
	
	
	 function ajaxJson(){
		
		$.ajax({
				type : "post",
				url : "user/findAll",
				data : JSON.stringify({
					"page":"0",
					"size":"2"
				}),
				async : true,
				contentType : 'application/json;charset=UTF-8',
				success : function(msg) {
					if (msg.driCard!= null) {
						alert('我的消息1');
					}else{
						alert('我的消息2');
					}
				}
			})
		
	}
	
		
	</script>
	</head>
	<body>
		
<p>点击这个段落修改它的背景颜色。</p>
<p>点击一下按钮再点击这个段落( click 事件被移除 )。</p>

<button onclick="ajaxJson()">移除 click 事件句柄</button>
	</body>
</html>
