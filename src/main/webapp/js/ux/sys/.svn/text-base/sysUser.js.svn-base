$package('XHY.sysUser');
XHY.sysUser = function(){
	
		var _box = null;
	var Grid = $('#data-list');
	var _this = {
		updatePwdAction:'updatePwd.do',
		editWin:function(){
			return $("#edit-win");
		},
		editPwdForm:function(){
			return $("#pwdForm");
		},
		clearSearchForm:function() {
			return $("#searchForm");
		},
		editPwdWin:function(){
			return $("#edit-pwd-win");
		},
		savePwd:function(){
			//XHY.progress();//缓冲条
			if(_this.editPwdForm().form('validate')){
				_this.editPwdForm().attr('action',_this.updatePwdAction);
				XHY.saveForm(_this.editPwdForm(),function(data){
					//XHY.closeProgress();//关闭缓冲条				 
					XHY.alert('提示',data.msg);  
					_this.editPwdWin().dialog('close');
				});
			 }
		},
		uploadWin:function(){
			return $("#sysUserImportDlg");
		},
		initForm:function(){
			//修改密码
			_this.editPwdWin().find("#btn-pwd-submit").click(function(){
				_this.savePwd();
			});
			_this.editPwdWin().find("#btn-pwd-close").click(function(){	
				$.messager.confirm('确定','你确定你要关闭窗口?',function(r){  
				    if (r){  
				     	_this.editPwdWin().dialog('close');
				    }  
				});
			});
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
  			dataGrid:{
  				title:'人员列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'uname',title:'登陆账号',width:100,sortable: false},
						{field:'nickName',title:'昵称',width:150,sortable: false},
						{field:'email',title:'邮箱',width:150,sortable: false},
						{field:'phone',title:'手机号码',width:120,sortable: false},
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
						{field:'createTime',title:'创建时间',width:130,sortable: true},
//						{field:'orgName',title:'经管单位',width:120,sortable: false},
						{field:'loginCount',title:'登陆次数',align:'right',width:80,sortable: false},
						{field:'loginTime',title:'登陆时间',width:130,sortable: false}
				]],
				toolbar:[
					{id:'btnadd',text:'添加',btnType:'add'},
					{id:'btnedit',text:'编辑',btnType:'edit'},
					{id:'btnedit',text:'密码修改',btnType:'editPwd',iconCls:'icon-edit',handler:function(){
							var selected = _box.utils.getCheckedRows();
							if ( _box.utils.checkSelectOne(selected)){
								_this.editPwdForm().resetForm();
								_this.editPwdForm().find("input[name='email']").val(selected[0].email);
								_this.editPwdForm().find("input[name='id']").val(selected[0].id);
								_this.editPwdForm().find("input[name='uname']").val(selected[0].uname);
								_this.editPwdWin().window('open'); 
							}
						}},
					{id:'btndelete',text:'删除',btnType:'remove'}
//                  {id:'btnexport',text:'导出',btnType:'export',iconCls:'icon-reload',handler:function(){window.location=urls['msUrl']+"/sysUser/export.do";}},
//					{id:'btnimport',text:'导入',btnType:'import',iconCls:'icon-reload',handler:function(){
//						//alert(555)
//						_this.uploadWin().window('open');
//						_this.uploadWin().find("#sysUserImport").uploadify({
//						    height : '25',
//						    width : '100',
//						    'swf' : urls['msUrl']+'/js/uploadify/uploadify.swf',
//						    'uploader' : urls['msUrl']+'/sysUser/import.do?var='+(new Date()).getTime(),
//							'cancelImg' : urls['msUrl']+'/js/uploadify/uploadify-cancel.png',
//							'debug' : false,
//							'fileObjName' : 'file',
//							'buttonClass' :null,
//							'buttonText' : '上传文件',
//							'buttonImage' : null,
//							'fileTypeDesc' : 'xls|xlsx  Files',
//						    'fileTypeExts' : '*.xls; *.xlsx',
//							onUploadSuccess : function(file, data, response) {
//								XHY.alert("提示", data.msg, null);
//								_this.uploadWin().window('close');
//							}
//						});
//					}}
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
	 XHY.sysUser.init();
	    $("#add").bind('click',function() { // 添加用户
	        XHY.sysUser.config.event.add();
	    });
	    $("#edit").bind('click',function() { // 修改用户
	        XHY.sysUser.config.event.edit();
	    });
});