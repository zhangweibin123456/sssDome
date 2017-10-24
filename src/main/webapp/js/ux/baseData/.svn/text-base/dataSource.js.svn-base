$package('XHY.dataSource');
XHY.dataSource = function(){
	var _box = null;
	var Grid = $('#data-list');
	var _this = {
		editWin:function(){
			return $("#edit-win");
		},	 
		clearSearchForm:function() {
			return $("#searchForm");
		},
		initForm:function(){		 
			_this.clearSearchForm().find("#btn-clearsearch").click(function() {
				$('input').attr("value","");
				return false;
			});
		},
		initWin:function(){
//			$("#deptRoleId").combobox({
//				url:'../sysDeptVersion/getDeptRoleTree.do',
//				valueField:'id',
//				textField:'deptRoleName'
//			});
		},
        reloadSelTree: function(selValue) {
        	$("#selTree").combotree({
				url:urls['msUrl'] + '/basOrg/getDicOrgNoDeptTreeByRoleType.do?roleType='+selValue,
				//checkbox:true,
				animate:true,
				lines:true, //表示树线s
				onLoadSuccess:function(){  //当数据加载成功之后触发
//					$('#selTree').combotree('tree').tree("collapseAll"); ;//折叠
					//为每个节点增加desc描述
					$(".tree-node").each(function(i) {
						//这里的this是dom对象,$(this)为jquery对象
						var tn = $('#selTree').tree('getNode', this);
						$(this).attr("title", tn.text);
					});
				}
			});
        },
		initSelTree:function(){
			$("#selTree").combotree({
				url:urls['msUrl'] + '/basOrg/getDicOrgNoDeptTree.do',
				//checkbox:true,
				animate:true,
				lines:true, //表示树线
//			    onBeforeExpand:function(node,param){
//			    	$("#selTree").combotree('options').url = urls['msUrl']+"/basOrg/getDicOrgNoDeptTree.do?id=" + node.id;                       
////                	alert(node.state);
//                	node.state="open";
//                },
				onLoadSuccess:function(){  //当数据加载成功之后触发
//					$('#selTree').combotree('tree').tree("collapseAll"); ;//折叠
					//为每个节点增加desc描述
					$(".tree-node").each(function(i) {
						//这里的this是dom对象,$(this)为jquery对象
						var tn = $('#selTree').tree('getNode', this);
						$(this).attr("title", tn.text);
					});
				}
			});
	
			$("#commyTree").combotree({
				url: urls['msUrl'] + '/commyOrganization/getOrgTree.do',
				//checkbox:true,
				animate:true,
				lines:true, //表示树线s
				onLoadSuccess:function(){  //当数据加载成功之后触发
//					$('#commyTree').combotree('tree').tree("collapseAll"); ;//折叠
					//为每个节点增加desc描述
					$(".tree-node").each(function(i) {
						//这里的this是dom对象,$(this)为jquery对象
						var tn = $('#commyTree').tree('getNode', this);
						$(this).attr("title", tn.text);
					});
				}
			});
		},
		// 清空所有input数据
        resetValue: function() {
            $("input").attr("value", "");
        },
        collapseAllInputTree: function() {
//            $("#selTree").combotree("tree").tree("collapseAll");
//            $("#commyTree").combotree("tree").tree("collapseAll");
        },
		config:{
			event:{
				add:function(){
					_this.resetValue();
					_this.collapseAllInputTree(); // 折叠所有下拉树
					_box.handler.add();
//					$('#DW').attr('class','fitem');
				},
				edit:function(){
					_this.resetValue();
					_this.collapseAllInputTree(); // 折叠所有下拉树
					var record = Grid.datagrid('getChecked');
					if (record.length == 0) {
						XHY.alert('警告','未选中记录.','warning');  
						  return false;	
					}
					if (record.length > 1) {
						XHY.alert('警告','一次只能编辑一条记录.','warning');  
						  return false;	
					}
					var data ={'id':record[0].id};	
					XHY.getById('getId.do',data,function(result){
						XHY.closeProgress();
                        _box.form.edit.form('load', result.data);
                        _box.win.edit.dialog('open');
					});
				}
			},
  			 
			dataGrid : {
				title : '数据源管理',
				url : 'dataList.do',
				frozenColumns:[[  
				            	{field:'id',checkbox:true},
				            	{field:'dsName',title : '数据源名称',align : 'center',width : 180,sortable : false}
	   					      ]],
				columns : [ [ 
			    {
			    	 field : 'dsType',
			    	 title : '数据库类型',
			    	 align : 'center',
			    	 width : 120,
			    	 sortable : false}, 
				{
					field : 'dbDriver',
					title : '数据库驱动',
					align : 'center',
					width : 200,
					sortable : false
				},{
					field : 'dbIp',
					title : '服务器地址',
					align : 'center',
					width : 170,
					sortable : false
				}, {
					field : 'dbPort',
					title : '端口号',
					align : 'center',
					width : 100,
					sortable : false
				},{
					field : 'dbInstance',
					title : '数据库名称',
					align : 'center',
					width : 125,
					sortable : false
				},{
					field : 'dbName',
					title : '用户名',
					align : 'center',
					width : 100,
					sortable : false
				},{
					field : 'dbPwd',
					title : '密码',
					align : 'center',
					width : 100,
					sortable : false
				} ] ],
				toolbar : [ {
					id : 'btnadd',
					text : '添加',
					btnType : 'add'
				},{
					id : 'btn',
					text : '修改',
					btnType : 'edit' 
				},{
					id : 'btndelete',
					text : '删除',
					btnType : 'remove'
					 
				} 
				]
			}	
		},
		init:function(){
			_this.initWin();
			_this.initForm();
			_box = new YDataGrid(_this.config); 
			_box.init();
			_this.initSelTree();
		}
	};
	return _this;
}();

$(function(){
	 XHY.dataSource.init();
	    $("#add").bind('click',function() { // 添加数据源
	        XHY.dataSource.config.event.add();
	    });
	    $("#edit").bind('click',function() { // 修改数据源
	        XHY.dataSource.config.event.edit();
	    });
});