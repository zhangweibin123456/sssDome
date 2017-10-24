$package('XHY.sysUserGroup');
XHY.sysUserGroup = function(){
	var _box = null;
	var _this = {
		addUserAction:'addUser.do',
		addUserForm:function(){
			return $("#addUserForm");
		},
		addUserWin:function(){
			return $("#addUser-win");
		},
		//保存用户组数据
		saveUserGroup:function(){
			XHY.progress();//缓冲条
			if(_this.addUserForm().form('validate')){
				_this.addUserForm().attr('action',_this.addUserAction);
				//获取添加到组的所有用户
				var uu = $("#users").children();
				var str = "";
				$.each(uu,function(i,u){
					str += "&userIds="+u.value;
				});
				//获取组的id
				var id = $("input[name=groupId]").val();
				var data = "&groupId="+id + str;
				$.post(_this.addUserAction,data,function(result){
					XHY.closeProgress();//关闭缓冲条
					_this.addUserWin().dialog('close');
					_box.handler.refresh();
				});
				
			 }
		},
		initForm:function(){
			_this.addUserWin().dialog({
				buttons:[
				         	//保存用户
				    {text:'保存',handler:_this.saveUserGroup},
				    		//
				    {text:'取消',handler:function(){
				    	$.messager.confirm('Confirm','您确定要关闭本窗口吗?',function(r){  
						    if (r){  
						     	_this.addUserWin().dialog('close');
						    }  
						});
				    }}
				]
			});
			
			$("#orgType").change(function(){
				var selType = $("#orgType").val();
				if(selType==1||selType==3){
					$("#tree1").tree({
						url:'../basOrg/getOrgTree.do',
						lines:true,
						onLoadSuccess:function(){  //当数据加载成功之后触发
//							$("#tree1").tree('collapseAll');//折叠
						},
						onLoadError:function(){
							alert('初始化部门树时出现错误！');
						},
						onClick:function(node){
							_this.getUserListByOrgId(node);
						}
					});
				}else{
					$("#tree1").tree({
						url:'../commyOrganization/getOrgTree.do',
						lines:true,
						onLoadSuccess:function(){  //当数据加载成功之后触发
//							$("#tree1").tree('collapseAll');//折叠
						},
						onLoadError:function(){
							alert('初始化部门树时出现错误！');
						},
						onClick:function(node){
							_this.getUserListByCommyOrgId(node);
						}
					});
				}
			});
			
		},
		getUserListByOrgId:function(node){
			//当单击部门树的某一个部门时，获取该部门下的所有人员
			$("#users2").empty();//清空列表
			var data = {"id":node.id};
			$.getJSON('getUserListByOrgId.do',data,function(result){
				$.each(result,function(i,opt){
					$("#users2").append("<option value="+opt.id+">"+opt.uname+"</option>");
				});
			});
		},
		getUserListByCommyOrgId:function(node){
			//当单击部门树的某一个部门时，获取该部门下的所有人员
			$("#users2").empty();//清空列表
			var data = {"id":node.id};
			$.getJSON('getUserListByCommyOrgId.do',data,function(result){
				$.each(result,function(i,opt){
					$("#users2").append("<option value="+opt.id+">"+opt.uname+"</option>");
				});
			});
		},
		initTree:function(){
			$("#tree1").tree({
				url:'../basOrg/getOrgTree.do',
				lines:true,
				onLoadSuccess:function(){  //当数据加载成功之后触发
//					$("#tree1").tree('collapseAll');//折叠
				},
				onLoadError:function(){
					alert('初始化部门树时出现错误！');
				},
				onClick:function(node){
					_this.getUserListByOrgId(node);
				}
			});
		},
		config:{
			event:{
				add:function(){
					_box.handler.add();
				},
				edit:function(){
					_box.handler.edit();
				},
				save:function(){
					_box.handler.save();
				}
			},
  			dataGrid:{
  				title:'用户权限组列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'groupName',title:'权限组名称',width:200,sortable: false},
						{field:'createTime',title:'创建时间',width:130,sortable: false},
						{field:'updateTime',title:'更新时间',width:130,sortable: false},
						{field:'descr',title:'权限组描述',width:200,sortable: false},
						{field:'users',title:'权限组用户',width:350,sortable:false}
				]],
				toolbar:[
				    {id:'btnadd',text:'添加组',btnType:'add'},
					{id:'btnedit',text:'编辑组',btnType:'edit'},
					{id:'btndelete',text:'删除组',btnType:'remove'},
					{id:'btnadduser',text:'添加用户',iconCls:'icon-add',btnType:'addUser',handler:function(){
							var selected = _box.utils.getCheckedRows();
							if ( _box.utils.checkSelectOne(selected)){
								//清空列表
								$("#users").empty(); 
								$("#users2").empty(); 
								$("#tree1").tree('collapseAll');
								//再加载该权限组下的用户
								var id = selected[0].id;
								$("input[name=groupId]").val(id); //设置组的id到隐藏域中
								$("#groupName").text(selected[0].groupName);
								var data = '&id='+id;
								$.getJSON('getUserListByGroupId.do',data,function(result){
									$.each(result,function(i,opt){
										$("#users").append("<option value="+opt.id+">"+opt.uname+"</option>");
									});
								});
								_this.addUserWin().dialog('open');
							}
						}
					}
				]
			}
		},
		init:function(){
			_this.initForm();
			_box = new YDataGrid(_this.config); 
			_box.init();
			_this.initTree();
		}
	}
	return _this;
}();

$(function(){
	XHY.sysUserGroup.init();
	
	$("#btn-add").bind("click",function(){ //添加
		var node = $("#users2").children(":selected"); //获取选中的用户
		var users = $("#users");
		var groupId = $("input[name=groupId]").val();
		var param = "&groupId="+groupId;
		//把选择的人员加入到用户组中
		$.each(node,function(i,opt){
			//判断如果用户已经加入，则不再加入
			var flag = true;
			$.each($("#users").children(),function(i,oo){
				if(opt.value == oo.value){
					flag = false;
				}
			});
			if(flag){
				param +="&userIds="+opt.value;
			}
		});
		//判断这些用户是否在其他权限组中，如果不在则添加，否则弹出提示。
		$.getJSON('getUserIsAddGroup.do',param,function(result){
			var userNames = "";
			$.each(result,function(i,opt){
				if(opt.isAddGroup == 0){ //未添加
					users.append("<option value="+opt.id+">"+opt.uname+"</option>");
				}else{
					userNames += opt.uname + ",";
				}
			});
			if(userNames.length>0){
				userNames = userNames.substring(0, userNames.length-1);//去掉尾部的“,”
				XHY.alert("提示",userNames + "用户不能同时添加到两个权限组中！");
			}
		});
	});
	$("#btn-remove").bind('click',function(){ //移除
		var node = $("#users").children(":selected"); //返回的是选中的数组option元素
		$.each(node,function(i,no){
			node.remove("option[value="+no.value+"]");
		});
	});
	function getAllOptions(){
		var node = $("#users").children();
		$.each(node,function(i,opt){
			//opt.value 得到值，opt.text 得到文本
			alert(opt.text);
		});
	}
});		