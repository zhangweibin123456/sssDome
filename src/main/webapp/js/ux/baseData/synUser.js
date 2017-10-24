$package('XHY.synUser');
XHY.synUser = function(){
	var _box = null;
	//Grid 工具类
	var Utils = {
		getCheckedRows : function(){
			return $("#data-list").datagrid('getChecked');			
		},
		checkSelect : function(rows){//检查grid是否有勾选的行, 有返回 true,没有返回true
			var records =  rows;
			if(records && records.length > 0){
				return true;
			}
			XHY.alert('警告','未选中记录.','warning');  
			return false;
			
		},
		checkSelectOne : function(rows){//检查grid是否只勾选了一行,是返回 true,否返回true
			var records = rows;
			if(!Utils.checkSelect(records)){
				return false;
			}
			if(records.length == 1){
				return true;
			}
			XHY.alert('警告','只能选择一行记录.','warning');  
			return false;
		}
	};
	
	var _this = {	
		   //绑定用户
			saveUserRelation:function(){			 				 
		    var records = Utils.getCheckedRows();
		    if (Utils.checkSelectOne(records)){  
			    var userId =  $('#sysUserIds').combobox('getValue');
			    var uname =   $('#sysUserIds').combobox('getText');
			    if(uname==null||uname==""){
			    	XHY.alert("提示","请选择系统用户");
			    	return false;
			    }				    
			    	var data = {}; //后台要传的数据				    	 
					var arr = [];
					arr.push('userId=' + userId);
					arr.push('uname=' + uname);
					$.each(records, function(i, record) {
						arr.push('id=' + record.id);
						arr.push('name=' + record.name);	
						arr.push('fullName=' + record.fullName);	 
						arr.push('isAdmin=' + record.isAdmin);							 					
					});
					var data = arr.join("&");
					XHY.ajaxJson('saveUserRelation.do',data,function(result){				 
					    var message = result;
						if(message.success){								 
							 XHY.alert('提示',message.msg);  
							 $('#data-list').datagrid('reload');
							 $("#sysUserIds").combobox({
									url:'../synUser/loadsysUserList.do',
									valueField:'id',
									textField:'uname',
									multiple:false,
									formatter:function(row){
									  var s = "<option class='selectId' style='vertical-align: middle'  id='selectId_"+row.id+"' value='"+row.id+"'>"+row.uname+"</option>";
									  return s;  
									}					 
								});
						}else{						 
							XHY.alert('提示',message.msg);  
						}						   
					});
		    }	 					 
	},
			
		   //查询未绑定的泰比用户			
	           PrincipalListDataGrid:function(){
	        	 $("#userRes" ).css("display", "block");
	  			 $("#userRes" ).css("height", "30px");
				 $("#data-list").datagrid({   
		    		    title:'用户绑定列表',
			   			url:'getPrincipalList.do',
			   			columns : [ [
						{field:'id',checkbox:true},
					    {field:'name',title : '用户名称',align : 'center',width : 180,sortable : false},
					    {field : 'fullName',title : '全称',align : 'center',width : 120, sortable : false}, 
						{
							field : 'isAdmin',
							title : '是否管理员',
							align : 'center',
							width : 200,
							sortable : false,
							formatter:function(value,row,index){
								if(value == 0){
									return "否";
								}
								if(value == 1){
									return "是";
								}
							}
						} ] ],
		    		          toolbar:[
						         {
							         id : 'btnBind',
									 text : '用户绑定',
									 iconCls: 'icon-save',
									 btnType : 'add',
									 handler:function(){
										 _this.saveUserRelation();	 						
									 }
								},
								{
							         id : 'backBind',
									 text : '返回',
									 btnType : 'add',
									 iconCls:'icon-back',
									 handler:function(){
										 $("#userRes" ).css("display", "none");
							  			 $("#userRes" ).css("height", "0px");
										 XHY.synUser.init();	 						
									 }
								}
								]
		    		 });				
			},
			
		      //删除绑定关系
			removeUserbBind:function(){
				var records = Utils.getCheckedRows();
			    if (Utils.checkSelectOne(records)){
			    	   $.messager.confirm('确认','您确定要解除这个绑定关系?',function(r){  
						    if (r){
						    	 var data ={'id':records[0].id};	
									XHY.ajaxJson('deleteUserBind.do',data,function(result){				 
									    var message = result;
										if(message.success){								 
											 XHY.alert('提示',message.msg);  
											 $('#data-list').datagrid('reload');
										}else{						 
											XHY.alert('提示',message.msg);  
										}						   
									});
						    }			    
					     });   
			    }    
			},
			
		config:{			
			dataGrid : {
				title : '用户已绑定列表',
				url : 'dataList.do',		 
	                    columns:[[
				          {field:'id',checkbox:true},
	    		             {field:'name', title:'泰比用户名',  width:180,sortable: false},
	    		             {field:'fullName', title:'泰比用户全名',  width:180,sortable: false},
	    		             {field:'isAdmin', title:'是否管理员',  width:100,sortable: false,
	    		            	 formatter:function(value,row,index){
										if(value == 0){
											return "否";
										}
										if(value == 1){
											return "是";
										}
									}},
	    		             {field:'uname', title:'扫描系统用户',  width:100,sortable: false},
	    		             {field:'creator', title:'操作人',  width:130,sortable: false},
	    		             {field:'creatorDate', title:'操作时间',  width:130,sortable: false}		    		           
	    		          ]],
				toolbar : [ 
				  {
					id : 'btnadd',
					text : '绑定用户',
					btnType : 'add',
					handler:function(){
						_this.PrincipalListDataGrid();										
				    }	
				},
				{
					 id : 'btnreMoveBind',
					 text : '解除绑定',
					 btnType : 'remove',
				     handler:function(){ //选择绑定数据,批量
				    	 _this.removeUserbBind();						    						    	
					 }
				}
				
			  ]
			}	
			
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
			$("#sysUserIds").combobox({
				url:'../synUser/loadsysUserList.do',
				valueField:'id',
				textField:'uname',
				multiple:false,
				formatter:function(row){
				  var s = "<option class='selectId' style='vertical-align: middle'  id='selectId_"+row.id+"' value='"+row.id+"'>"+row.uname+"</option>";
				  return s;  
				}					 
			});
		},
		
		
		
		
		
		
		
		
	};
	return _this;
}();

$(function(){
	XHY.synUser.init();	  
});		