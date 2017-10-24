$package('XHY.main');
//主页
XHY.main = function(){
	var entereditForm = $("#edit-win");
	return {
		treeSelect:function(){
			var _this = $(this);
			var title=_this.text();
			var url=_this.attr('href');
			XHY.main.addTab(title,url);
			return false;
		},
		treeInit:function(){
			var  $items =  $('#tree-box').find(".menu-item");
			var  $itemThrees =  $('#tree-box').find(".threeMenu");
			$items.bind('click',this.treeSelect);
			$itemThrees.bind('click',this.treeSelect);
		},
		addTab:function(_title,_url){
			var boxId = '#tab-box';
			if($(boxId).tabs('exists',_title) ){
				var tab = $(boxId).tabs('getTab',_title);
				var index = $(boxId).tabs('getTabIndex',tab);
				$(boxId).tabs('select',index);
				if(tab && tab.find('iframe').length > 0){  
					 var _refresh_ifram = tab.find('iframe')[0];  
				     _refresh_ifram.contentWindow.location.href=_url;  
			    } 
			}else{		
				var _content ="<iframe scrolling='auto' frameborder='0' src='"+_url+"' style='width:100%; height:100%'></iframe>";
				$(boxId).tabs('add',{
					    title:_title,
					    content:_content,
					    closable:true});
				
			}
		},
		menuClick:function(){
			$('.menu-item1').click(
					function(){
						$(this).next(".sslist").animate({height: 'toggle', opacity: 'toggle'}, "slow");
					});
		},
		menuHover:function(){
			//菜单鼠标进入效果
			$('.menu-item').hover(				
				function () {
					$(this).stop().animate({ paddingLeft: "40px" }, 200,function(){
						$(this).addClass("black");
					});
				}, 
				function () {
					$(this).stop().animate({ paddingLeft: "15px" },function(){
						$(this).removeClass("black");
					});
				}
			);
			
			//菜单鼠标进入效果
			$('.menu-item1').hover(				
				function () {
					$(this).stop().animate({ paddingLeft: "40px" }, 200,function(){
						$(this).addClass("black");
					});
				}, 
				function () {
					$(this).stop().animate({ paddingLeft: "15px" },function(){
						$(this).removeClass("black");
					});
				}
			);
			
		},
		
//		infoHover:function(){
//			//菜单鼠标进入效果
//			$('.dbinfo').hover(
//				function () {
//					$(this).stop().animate({ paddingLeft: "25px" }, 200,function(){
//						$(this).addClass("orange");
//					});
//				}, 
//				function () {
//					$(this).stop().animate({ paddingLeft: "15px" },function(){
//						$(this).removeClass("orange");
//					});
//				}
//			);
//			
//		},
		modifyPwd:function(){
			var pwdForm = $("#pwdForm");
			if(pwdForm.form('validate')){
				var parentId =$('#search_parentId').val();
				$("#edit_parentId").val(parentId)
				XHY.saveForm(pwdForm,function(data){
					$('#modify-pwd-win').dialog('close');
				    pwdForm.resetForm();
				});
			 }
		},
		loadTodo:function(){
			//alert(111);
			$.post('dataList.do',null,function(data){
				if(data.msg){
					XHY.alert("提示", data.msg);
				}
				//循环展开查找到的部门
				$.each(data.rows,function(i,node){
					// 在滚动处最前面添加一条消息
					$('#list').prepend("<li id='gundong" + i +"'>" + node.infoTitle + "</li>");
					// 首页最新消息处使用
					var str1 = "<div class='todo-title' style='float:left; width:290px;padding-left: 20px;'>"+node.infoTitle+"</div>";
					var str2 = "<div class='todo-date'  style='float:right;width: 140px;padding-right: 25px;'>"+node.createTime+"</div>";
					var str3 = "<div class='todo-logo'  style='float:right;width:13px;height:16px;padding-right: 20px;background:url(images/logo/main_db_logo.jpg) no-repeat'></div>";
					var str = str1 + str3 + str2;
					var str4 = "<div id='easyui-todo" + i +"' class='menu-item-db'>"+str+"</div>";
					$("#easyui-todo").append(str4);
					//菜单鼠标进入效果
					$('#easyui-todo'+i).hover(
						function () {
							$(this).stop().animate({ paddingLeft: "25px" }, 200,function(){
								$(this).addClass("orange");
							});
						}, 
						function () {
							$(this).stop().animate({ paddingLeft: "15px" },function(){
								$(this).removeClass("orange");
							});
						}
					);
					$("#easyui-todo"+i).click(function(){
						$('#edit-win').dialog('open');
						$("#createBy").val(node.createBy);
						$("#infoTitle").val(node.infoTitle);
						$("#infoContent").val(node.infoContent);
						$("#infoTowards").val(node.infoTowards);
						$("#createTime").val(node.createTime);
					});
					
					$("#gundong"+i).click(function(){
						$('#edit-win').dialog('open');
						$("#createBy").val(node.createBy);
						$("#infoTitle").val(node.infoTitle);
						$("#infoContent").val(node.infoContent);
						$("#infoTowards").val(node.infoTowards);
						$("#createTime").val(node.createTime);
						var _url = urls['msUrl'] + '/basInformation/updateScroll.do';
						var data = {
							'url' : window.location.href,
							'id' : node.id
						};
						XHY.ajaxJson(_url, data, function(result) {
							XHY.closeProgress();
							if(result.success) {
								entereditForm.form('load',result.data);
							} else {
								XHY.alert('提示',result.msg);
							}
						});
					});
				});
				
			});
			
		},
		
		loadTodo1:function(){
			$.post('noticeList.do',null,function(data){
				if(data.msg){
					XHY.alert("提示", data.msg);
				}
				//循环展开查找到的部门
				$.each(data.rows,function(i,node){
					// 首页最新公告处使用
					var str1 = "<div class='todo-title' style='float:left; width:290px;padding-left: 20px;'>"+node.infoTitle+"</div>";
					var str2 = "<div class='todo-date'  style='float:right;width: 140px;padding-right: 25px;'>"+node.createTime+"</div>";
					var str3 = "<div class='todo-logo'  style='float:right;width:13px;height:16px;padding-right: 20px;background:url(images/logo/main_db_logo.jpg) no-repeat'></div>";
					var str = str1 + str3 + str2;
					var str4 = "<div id='note-todo" + i +"' class='menu-item-db'>"+str+"</div>";
					$("#note-todo").append(str4);
					//菜单鼠标进入效果
					$('#note-todo'+i).hover(
						function () {
							$(this).stop().animate({ paddingLeft: "25px" }, 200,function(){
								$(this).addClass("orange");
							});
						}, 
						function () {
							$(this).stop().animate({ paddingLeft: "15px" },function(){
								$(this).removeClass("orange");
							});
						}
					);
					$("#note-todo"+i).click(function(){
						$('#edit-win').dialog('open');
						$("#createBy").val(node.createBy);
						$("#infoTitle").val(node.infoTitle);
						$("#infoTowards").val(node.infoTowards);
						$("#createTime").val(node.createTime);
						$("#infoContent").html(node.infoContent);

					});
					
				});
				
			});
			
		},
		
		loadScrollNews:function(){
			$.post('dataList.do',null,function(data){
				$('#list').empty();
				//循环展开查找到的部门
				$.each(data.rows,function(i,node){
					// 在滚动处最前面添加一条消息
					$('#list').prepend("<li id='gundong" +i +"'>" + node.infoTitle + "</li>");
					
					$("#gundong"+i).click(function(){
						$('#edit-win').dialog('open');
						$("#createBy").val(node.createBy);
						$("#infoTitle").val(node.infoTitle);
						$("#infoContent").val(node.infoContent);
						$("#infoTowards").val(node.infoTowards);
						$("#createTime").val(node.createTime);
						var _url = urls['msUrl'] + '/basInformation/updateScroll.do';
						var data = {
							'url' : window.location.href,
							'id' : node.id
						};
						XHY.ajaxJson(_url, data, function(result) {
							XHY.closeProgress();
							if(result.success) {
								entereditForm.form('load',result.data);
							} else {
								XHY.alert('提示',result.msg);
							}
						});
					});
				});
			});
		},
		init:function(){
			var addFlg = false;
			this.treeInit();
			this.menuHover();
			this.menuClick();
			this.loadTodo();
			this.loadTodo1();
//			$('#wrap').layout('collapse','north');
			/*2015-3-13修改*/
//			$('#wrap').layout('collapse','north');
//			var northPanel = $('#wrap').layout('panel','north');
//			northPanel.panel({
//				onCollapse:function(){
//					
//					var p = $('#wrap').layout('panel','expandNorth');
//					p.panel('setTitle', '扫描校对系统');
//					var header = p.panel('header');
//					header.css('background',"url('images/logo/taskbar.jpg')");
//					header.children().css({"color":"white","padding-top":"3px;"});
//					if(!addFlg){
////						header.children().eq(0).before("<div style='float:left;margin-right:5px;margin-left:5px;background:url(images/dh.png);width:18px;height:16px;'></div>");
//						header.children().eq(0).before("<div style='float:left;margin-right:5px;margin-left:5px;width:18px;height:16px;'></div>");
//						addFlg = true;
//					}
//				}
//			});
//			var northPanel = $('#wrap').layout('panel','north');
//			northPanel.panel({
//				onCollapse:function(){
//					
//					var p = $('#wrap').layout('panel','expandNorth');
//					p.panel('setTitle', '扫描校对系统');
//					var header = p.panel('header');
//					header.css('background',"url('images/logo/taskbar.jpg')");
//					header.children().css({"color":"white","padding-top":"3px;","float":"left"});
//					var content = "<div style='float:left;margin-left:200px;color:#fff;text-align:center'>当前用户：<span id='uname' class='orange'>"+uname+"</span>" +
//							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 当前时间：<span id='todayT'></span>" +
//							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//							"<span class='help'><a href='${msUrl}/help/help.chm'>帮助中心</a></span>" +
//							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//							"<span class='support'><a href='javascript:void(0);'>技术咨询</a></span>" +
//							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//							"<span class='modify-pwd-btn'><a href='javascript:void(0);'>修改密码</a></span>" +
//							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//							"<span class='logout'><a href='${msUrl}/logout.shtml'>退出登录</a></span></div>";
//					if(!addFlg){
////						header.children().eq(0).before("<div style='float:left;margin-right:5px;margin-left:5px;background:url(images/dh.png);width:18px;height:16px;'></div>");
//						header.children().eq(0).before("<div style='float:left;margin-right:5px;margin-left:5px;width:18px;height:16px;'></div>");
//						header.children().eq(1).after(content);
//						addFlg = true;
//					}
//				}
//			});
			//技术支持绑定事件
			$('.support').click(function(){
				$('#support-win').dialog('open');
			});
			$('#btn-support-close').click(function(){
				$('#support-win').dialog('close');
			});
			//修改密码绑定事件
			$('.modify-pwd-btn').click(function(){
				$('#modify-pwd-win').dialog('open');
			});
			$('#btn-pwd-close').click(function(){
				$('#modify-pwd-win').dialog('close');
			});
			$('#btn-pwd-submit').click(this.modifyPwd);
//			$('#ccc').css("cursor","pointer");
//			$('#ccc').click(function(){
//				$('#wrap').layout('collapse','north');
//				var northPanel = $('#wrap').layout('panel','north');
//				northPanel.panel({
//					onCollapse:function(){
//						
//						var p = $('#wrap').layout('panel','expandNorth');
//						p.panel('setTitle', '扫描校对系统');
//						var header = p.panel('header');
//						header.css('background',"url('images/logo/taskbar.jpg')");
//						header.children().css({"color":"white","padding-top":"3px;"});
//						if(!addFlg){
////							header.children().eq(0).before("<div style='float:left;margin-right:5px;margin-left:5px;background:url(images/dh.png);width:18px;height:16px;'></div>");
//							header.children().eq(0).before("<div style='float:left;margin-right:5px;margin-left:5px;width:18px;height:16px;'></div>");
//							addFlg = true;
//						}
//					}
//				});
//			});
			
			// 文字滚动实现
			$('.tabs').eq(0).before("<div style='position:absolute;left:10px;top:5px;width:600px;z-index:1;'><div class='info'></div><div id='scrollDiv'><ul id='list'> "+
				"</ul></a></div></div>");
			
			// 给首页导航上面赋值
			var today = new Date();   
		    var day = today.getDate();   
		    var month = today.getMonth() + 1;   
		    var year = today.getYear();   
		    var date = year + "-" + month + "-" + day;   
		    //alert(date)
		    $("todayTime").text = date;  

			// 每五分钟进行一次滚动消息查询
		    setInterval(this.loadScrollNews,1000*60*5);
		}
	};
}();

$(function(){
	XHY.main.init();
});		