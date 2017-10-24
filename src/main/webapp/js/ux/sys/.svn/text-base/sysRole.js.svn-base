$package('XHY.sysRole');
XHY.sysRole = function(){
	var _box = null;
	var _this = {
		menu: $('#menu-tree'),
		meta:$('#meta-tree'),
		buildTreeData:function(nodes){//把菜单选中的项添加到form中
			$.each(nodes,function(i,note){
				var id = note.attributes.id;
				var type = note.attributes.type;
				var $id = $("<input type='hidden' name='menuIds' class='c_menus'>");
				if(type == 0){
					$id.attr('name','menuIds');
				}else if(type == 1){
					$id.attr('name','btnIds');
				}
				$id.val(id);
				_box.form.edit.append($id);
			});
		},
		buildMetaTreeData:function(nodes){//把元数据选中的项添加到form中
			$.each(nodes,function(i,note){
				var id = note.attributes.id;
				var type = note.attributes.type;//字段的父id（即，表）
				var $id = $("<input type='hidden' name='columnIds' class='c_menus'>");
				if(type == 0){
					$id.attr('name','tableIds');
				}else {
					$id.attr('name','columnIds');
				}
				$id.val(id+"_"+type);
				_box.form.edit.append($id);
			});
		},
		setTreeValue:function(id){//设置菜单选中项
			var node = _this.menu.tree("find",id);
			if(node && node.target){
				//判断是否选择或者半选状态 
				if($(node.target).find(".tree-checkbox0")[0]){
					_this.menu.tree('check',node.target);
				}
			}
		},
		setMetaValue:function(id){//设置元数据选中项
			var node = _this.meta.tree("find",id);
			if(node && node.target){
				//判断是否选择或者半选状态
				if($(node.target).find(".tree-checkbox0")[0]){
					_this.meta.tree('check',node.target);
				}
			}
		},
		clearTreeData:function(){//清除选中状态
			$(".tree-checkbox1",_this.menu).removeClass("tree-checkbox1").addClass("tree-checkbox0")//0：未选中、1：选中、2：半选中、
			$(".tree-checkbox2",_this.menu).removeClass("tree-checkbox2").addClass("tree-checkbox0");
			$(".tree-checkbox1",_this.meta).removeClass("tree-checkbox1").addClass("tree-checkbox0")
			$(".tree-checkbox2",_this.meta).removeClass("tree-checkbox2").addClass("tree-checkbox0");
			$('.c_menus').remove();
		},
		save:function(){
			//菜单项
			var checknodes = _this.menu.tree('getChecked');
			var innodes = _this.menu.tree('getChecked','indeterminate');
			_this.buildTreeData(checknodes);
			_this.buildTreeData(innodes);
			//元数据项
			var checknodes2 = _this.meta.tree('getChecked');
			var innodes2 = _this.meta.tree('getChecked','indeterminate');
			_this.buildMetaTreeData(checknodes2);
			_this.buildMetaTreeData(innodes2);
			_box.handler.save();
		},
		config:{
			event:{
				add:function(){
					_this.clearTreeData();
					_this.meta.tree("collapseAll");
					_box.handler.add();
				},
				edit:function(){
					_this.clearTreeData();
					_this.menu.tree("collapseAll");
					_this.meta.tree("collapseAll");
					_box.handler.edit(function(result){
						var btnIds  = result.data.btnIds;
						var menuIds  = result.data.menuIds;
						var columnIds  = result.data.columnIds;
						$.each(btnIds,function(i,id){
							_this.setTreeValue("btn_"+id);
						});
						
						$.each(menuIds,function(i,id){
							_this.setTreeValue("menu_"+id);
						});
						$.each(columnIds,function(i,id){
							_this.setMetaValue(id);
						});
					});
				},
				save:function(){
					//判断是否被禁用帐号
					var state = $("input[name='state']",_box.form.edit).val();
					if(state != 0){
						XHY.confirm("提示","禁用角色将会自动解除关联的用户授权,是否确定?",function(r){
							if(r){
								_this.save();
							}
						});
					}else{
						_this.save();
					}
				}
			},
  			dataGrid:{
  				title:'角色列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'roleName',title:'角色名称',width:250,sortable: false},
						{field:'state',title:'状态',width:80,align:'center',sortable: false,styler:function(value,row,index){
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
						{field:'createTime',title:'创建时间',width:125,sortable: true},
						{field:'updateTime',title:'修改时间',width:125,sortable: false},
//						{field:'roleType',title:'角色分类',width:100,sortable: false,
//							formatter:function(value,row,index){
//								if (value == 1) {
//									return "公务员";
//								}
//								if (value == 2) {
//									return "党员";
//								}
//								if (value == 3) {
//									return "人才";
//								}
//								if(value == 4){
//									return "审核";
//								}
//							}
//						},
//						{field:'type',title:'角色类型',width:100,sortable:false,
//							formatter:function(value,row,index){
//								if (value == 1) {
//									return "永久";
//								}
//								if (value == 2) {
//									return "临时";
//								}
//							}
//						},
						{field:'descr',title:'角色描述',width:300,sortable: false}
						
				]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
			_this.menu.tree({
				url:'../sysMenu/getMenuTree.do',
				checkbox:true,
				onLoadSuccess:function(){
					_this.menu.tree("collapseAll");
				}
			});
			_this.meta.tree({
				url:'getMetaTree.do',
				checkbox:true,
				onLoadSuccess:function(){
					_this.meta.tree("collapseAll");
				}
			});
		}
	}
	return _this;
}();

$(function(){
	XHY.sysRole.init();
});		