$package('XHY.dataBind');
XHY.dataBind = function(){
	var _box = null;
	// Grid 工具类
	var Utils = {
		getCheckedRows : function(){
			return $("#data-list").datagrid('getChecked');			
		},
		checkSelect : function(rows){// 检查grid是否有勾选的行, 有返回 true,没有返回true
			var records =  rows;
			if(records && records.length > 0){
				return true;
			}
			XHY.alert('警告','未选中记录.','warning');  
			return false;
			
		},
		checkSelectOne : function(rows){// 检查grid是否只勾选了一行,是返回 true,否返回true
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
	 	// 绑定数据

		
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
				// 刷新Grid 数据
				refresh: function(){
					var param = $("#searchForm").serializeObject();
					$('#data-list').datagrid('reload',param);
					var newValue=$('#dataTypeId').combobox('getValue');
					$("#selDirTypeId").combobox('setValue',newValue);
				},
				// 删除记录
				remove: function(){
					var records = Utils.getCheckedRows();
					if (Utils.checkSelect(records)){
						$.messager.confirm('确认','你确定要删除记录?',function(r){  
						    if (r){
						    	XHY.progress();
						    	var arr = [],idKey = 'dataId'; // 主键名称
						    	$.each(records,function(i,record){
						    		arr.push('id='+record[idKey]);
						    	});
						    	var data = arr.join("&");
						   		XHY.deleteForm('delete.do',data,function(result){
									XHY.closeProgress();
									_this.config.event.refresh();
									// 回调函数
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
  				title:'数据获取结果列表',
	   			url:'dataList.do',	   		 
	   			idField:'id',
	   			columns:[
	   			         ],
				toolbar:[
			         {
					         
							 text : ''

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
					// _this.dirtree.tree("collapseAll");//折叠
					_this.dirtree.tree("expandAll"); // 展开
				}
			});
			
			$('#dirtree').click(function(){
				 // 重新赋值
				var node=$('#dirtree').tree('getSelected'); // 获取选中树节点
                $("#dirId").val(node.id);
                $("#dirName").val(node.text); 
                
            	var data ={'id':node.id};	
            	
            	// 获取项目id
                XHY.getById('../fileDirectory/getById.do',data,function(result){
                	
                	var projectId=result.data.projectId;
                	

        			$("#templateName").combobox({
        				url:'../dataBind/getTableColumnList.do?projectId='+projectId,
        				valueField:'templateId',
        				textField:'templateName',
        				
        				multiple:false,
        				formatter:function(row){
        				  var s = "<option class='selectId' style='vertical-align: middle'  id='selectId_"+row.templateId+"' value='"+row.templateId+"'>"+row.templateName+"</option>";
        				  return s;  
        				}
        			});
        			
        		});	
                });
            	
            	
            	
		  	
    		$("#btn-search").click(function(){
    			
    			var dirName=$("#dirName").val();
    			 if(dirName==null||dirName==''){
    			 XHY.alert('警告','项目名称不能为空，请从树节点上选取项目！','warning');
    			 return;
    			 }
        
    			 var tableName =  $('#templateName').combobox('getText');
    			
    			 if(tableName==null||tableName==''){
    				 
    				 XHY.alert('警告',"模板名称不能为空,请选择！",'warning');
    				 return;
    			 }
    			 
    			 
    			 
    			 
    			 var data={'tableName':tableName};
    			 XHY.ajaxJson('getTableColumn.do',data,function(result){  
               	  // 给dataGrid赋列值 后台动态拼接
                    var column = eval('(' + result.msg+ ')');                
                      
// //查询值
                    $("#data-list").datagrid({ // 重新加载datagrid
                    	title:'数据获取结果列表',
        	   			url:'getTemplateList.do?templateName='+tableName,
        	   			queryParams:{dirId:$("#dirId").val()},
        	   			align:'center',
        	   			halign:'center',
        	   			columns:[
                                      column
        	   			         ]
                                      ,
        				toolbar:[
        				         {
        							 text : ' 您查询的模板是： '+tableName
        							
        							
        						}]                		 
                 	});                    
				});               
			});
            	

			
			}
		};
	 
	return _this;
}();

$(function(){

	XHY.dataBind.init();	 
	
	 
});		