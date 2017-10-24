$package('XHY.UserRole');
XHY.UserRole = function(){
	var _box = null;
	var _this = {
		openAddRole:function(record){
			$("#roleIds").combobox('clear'); //清空选择框
			$(".selectId").attr('checked',false); //checkbox 取消选中
			_box.handler.edit(function(result){
				$.each(result.data.roleIds,function(i,roleId){
					$("#selectId_"+roleId).attr("checked", true);
				});
			});
		},
		config:{
			action:{
				save:'addUserRole.do',
				getId:'getUser.do'
			},
			event:{
				save:function(){
					var stime = $("#startTime").val();
					var etime = $("#endTime").val();
					if(stime > etime){
						alert("开始时间 不能 大于结束时间 !");
						return;
					}
					//判断当前用户是否在用户组中
					var record = _box.utils.getCheckedRows();
					data = {'id':record[0]['id']};
					$.post('isInGroup.do',data,function(result){
						if(result.result){//ture则在用户组中
							XHY.confirm('提示','当前用户已经添加到权限组中，如果保存会把用户从权限组移除！\n您确定要继续保存吗？',function(r){
								if(r){
									_box.handler.save();
								}
							});
						}else{
							_box.handler.save();
						}
					});
				}
			},
  			dataGrid:{
  				title:'人员列表',
	   			url:'userList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'uname',title:'登陆账号',width:150,sortable: false},
						{field:'nickName',title:'昵称',width:150,sortable: false},
						{field:'state',title:'状态',width:60,align:'center',sortable: false,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							if(value == 0){
								return "可用";
							}
							if(value == 1){
								return "禁用";
							}
						}},
						{field:'createTime',title:'创建时间',align:'center',width:130,sortable: false},
						{field:'loginCount',title:'登陆次数',align:'right',width:60,sortable: false},
						{field:'loginTime',title:'登陆时间',align:'center',width:130,sortable: false},
						{field:'roleStr',title:'角色',width:380,sortable: false}
				]],
				toolbar:[
					{id:'btnedit',iconCls:'icon-edit',text:'授权',btnType:'authRole',handler:function(){
							var selected = _box.utils.getCheckedRows();
							if ( _box.utils.checkSelectOne(selected)){
								_this.openAddRole(selected);
							}
						}
					}
				]
			}
		},
		init:function(){
			$("#roleIds").combobox({
				url:'../sysRole/loadRoleList.do?type=1',
				valueField:'id',
				textField:'roleName',
				multiple:true,
				formatter:function(row){
				  var s = "<span><input type='checkbox' class='selectId' style='vertical-align: middle' id='selectId_"+row.id+"'>"+row.roleName+"<span>"
				  return s;  
				},
				onSelect:function(record){
					$("#selectId_"+record.id).attr("checked", true);
				},
				onUnselect:function(record){
					$("#selectId_"+record.id).attr("checked", false);
				}
			});
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	XHY.UserRole.init();
});		