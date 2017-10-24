$package('XHY.subjectBind');
XHY.subjectBind = function(){
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
		dirtree:  $('#dirtree'),
		
		//重新设置data-list的值
			resetDataGrid:function(){	
			 $("#data-list").datagrid("uncheckAll");
			 $("#fileandDs" ).css("display", "block");
			 $("#fileandDs" ).css("height", "30px");
			 $("#data-list").datagrid({   
	    		    title:'数据绑定',
		   			url:'sujectList.do',
	    		   columns:[[
                             {field:'id',checkbox:true,name:'checkbox'},
	    		             {field:'subjectName', title:'项目名称',  width:180,sortable: false},
	    		             {field:'subjectDesc', title:'项目描述',  width:180,sortable: false},
	    		             {field:'creator', title:'操作人',  width:100,sortable: false},
	    		             {field:'creatorDate', title:'操作时间',  width:130,sortable: false},
	    		             {field:'note', title:'备注',  width:200,sortable: false}  
	    		          ]],
	    		          toolbar:[
					         {
						         id : 'btnBind',
								 text : '绑定',
								 btnType : 'add',
								 iconCls: 'icon-save',
                                 handler :function(){                               	 
                                	 _this.saveSubBind();
								 }	 
							},
							{
						         id : 'backBind',
								 text : '返回',
								 btnType : 'add',
								 iconCls:'icon-back',
								 handler:function(){
									 XHY.subjectBind.init();	
									 $("#data-list").datagrid("uncheckAll");
								 }
							}
							]
	    		 });
		},
	 	//绑定数据		
		saveSubBind:function(){			 
				//XHY.progress('Please waiting','Save ing...');//缓冲条
			    var records = Utils.getCheckedRows();
			    if (Utils.checkSelectOne(records)){
			        var dirId = $("#dirId").val(); //文件目录id
				    var dirName = $("#dirName").val(); //文件目录名称
				    var dsId =  $('#dataDsIds').combobox('getValue');
				    var dsName = $('#dataDsIds').combobox('getText');
				    if(dsName==null||dsName==""){
				    	XHY.alert("提示","请选择数据源");
				    	return false;
				    }				    
				    	var data = {}; //后台要传的数据
				    	var subjectName = 'subjectName'; // 项目名称
						var arr = [];
						arr.push('dirId=' + dirId);
						arr.push('dirName=' + dirName);
						arr.push('dsId=' + dsId);
						arr.push('dsName=' + dsName);
						$.each(records, function(i, record) {
							arr.push('subjectName=' + record[subjectName]);		
							//放置其他属性							
						});
						var data = arr.join("&");
						XHY.ajaxJson('saveSubBind.do',data,function(result){				 
							//var message = eval('(' + result+ ')');	
						    var message = result;
							if(message.success){								 
								 XHY.alert('提示',message.msg);  
								 $('#data-list').datagrid('reload');
								 $("#data-list").datagrid("uncheckAll");	
							}else{						 
								XHY.alert('提示',message.msg);  
							}						   
						});
			    }	 					 
		},
         //删除绑定关系
		removeSubBind:function(){
			var records = Utils.getCheckedRows();
		    if (Utils.checkSelectOne(records)){
		    	   $.messager.confirm('确认','您确定要解除这个绑定关系?',function(r){  
					    if (r){
					    	 var data ={'id':records[0].id};	
								XHY.ajaxJson('deleteSubBind.do',data,function(result){				 
								    var message = result;
									if(message.success){								 
										 XHY.alert('提示',message.msg);  
										 $('#data-list').datagrid('reload');
//										//$('#data-list').datagrid('clearSelections');
//										  $("input[type='checkbox'").each(function(){
//											  if ($(this).attr("checked")=='checked') {												  
////												  $(this).attr("checked","");
//												$(this).removeAttr("checked");
////												  alert($(this).attr("checked"));
//											  }										  									  									
//									      }); 
										  $("#data-list").datagrid("uncheckAll");										    
//										  var records1 = Utils.getCheckedRows();
//										  alert(records1.length);
									}else{						 
										XHY.alert('提示',message.msg);  
									}
									
								});
					       }			    
				     });   
		    }    
		},
		config:{
			event:{
				add:function(callback){
					var node=$('#searchForm #dataTypeId').combobox('getValue');
					_this.addDirDataWin.dialog('open');
					_this.addDirDataForm.resetForm();
					if(node!=''){
						_selDirType.combobox('setValue',node);
					}
				},
				//刷新Grid 数据
				refresh: function(){
					var param = $("#searchForm").serializeObject();
					$('#data-list').datagrid('reload',param);
					var newValue=$('#dataTypeId').combobox('getValue');
					$("#selDirTypeId").combobox('setValue',newValue);
				},
				//删除记录
				remove: function(){
					var records = Utils.getCheckedRows();
					if (Utils.checkSelect(records)){
						$.messager.confirm('确认','你确定要删除记录?',function(r){  
						    if (r){
						    	XHY.progress();
						    	var arr = [],idKey = 'dataId'; //主键名称
						    	$.each(records,function(i,record){
						    		arr.push('id='+record[idKey]);
						    	});
						    	var data = arr.join("&");
						   		XHY.deleteForm('delete.do',data,function(result){
									XHY.closeProgress();
									_this.config.event.refresh();
									//回调函数
									if(result){
										XHY.alert("提示", result.msg);
									}
								});
						    }  
						});
					}
				},				
			},			
  			dataGrid:{
  				title:'目录绑定列表',
	   			url:'dataList.do',
	   			idField:'id',
	   			columns:[[
						{field:'id',checkbox:true,name:'checkbox'},
						{field:'dirName',title:'文件目录',width:150,sortable: false},
						{field:'subjectName',title:'项目名称',width:150,sortable: false},
						{field:'dsName',title:'业务数据源',width:150,sortable: false},
						{field:'status',title:'状态',width:105,sortable: false},
						{field:'creatorDate',title:'校对时间',width:150,sortable: false},
						{field:'creator',title:'校对人',width:150,sortable: false}
				]],
				toolbar:[
				         {
					         id : 'btngxBind',
							 text : '模板绑定',
							 btnType : 'add',
						     handler:function(){ //点击后进入绑定页面
						    	var node=$('#dirtree').tree('getSelected'); //获取选中树节点						    
						 		if(node==null||node==''){
						 			XHY.alert("提示","请选择文件目录！");	
						 		}else{
						 			 //赋值
                                     $("#dirId").val(node.id);
                                     $("#dirName").val(node.text);                                  
						 			 XHY.subjectBind.resetDataGrid();	
						 		}						    						    	
							 }
						},
						{
							 id : 'btnreMoveBind',
							 text : '解除绑定',
							 btnType : 'remove',
						     handler:function(){ //选择绑定数据,批量
						    	 XHY.subjectBind.removeSubBind();						    						    	
							 }
						}
				]
			}
		},
		init:function(){			 
			_box = new YDataGrid(_this.config); 
			_box.init();
			_this.dirtree.tree({
				url:'getDirTree.do',			    
				checkbox:false,
				onLoadSuccess:function(){
					//_this.dirtree.tree("collapseAll");//折叠
					_this.dirtree.tree("expandAll"); //展开
				}
			});
			
			$('#dirtree').click(function(){
				 //重新赋值
				var node=$('#dirtree').tree('getSelected'); //获取选中树节点		
                $("#dirId").val(node.id);
                $("#dirName").val(node.text);  
                               
			});
			
			
			$("#dataDsIds").combobox({
					url:'../subjectBind/loadDataSourceList.do',
					valueField:'id',
					textField:'dsName',
					multiple:false,
					formatter:function(row){
					  var s = "<option class='selectId' style='vertical-align: middle'  id='selectId_"+row.id+"' value='"+row.id+"'>"+row.dsName+"</option>";
					  return s;  
					}					 
				});
				 
			}
		};
	 
	return _this;
}();

$(function(){
	XHY.subjectBind.init();	 
 
	 
});		