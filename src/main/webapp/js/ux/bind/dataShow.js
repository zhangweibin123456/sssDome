$package('XHY.dataShow');
XHY.dataShow = function(){
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
	 	//绑定数据				
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
			},			
  			dataGrid:{
  				title:'数据显示列表',
	   			url:'dataList.do',	   		 
	   			idField:'id',
	   			columns:[
	   			          
	   			        ],
				toolbar:[
				           null
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
            	var data ={'dirId':node.id};
            	 
     
            	$('#box').tabs({
            		width : 300,
            		height : 100,
            		plain : true,
            		fit : true,
            		border : false,
            		tabWidth : 300,
            		tabHeight : 50,
            		scrollIncrement : 50,
            		scrollDuration : 1000,
            		tools : [{
            			iconCls : 'icon-add',
            			handler : function () {
            				alert('add');
            			},
            		},{}],
            	
            	});

                /*XHY.ajaxJson('getdataToBind.do',data,function(result){  
                	  //给dataGrid赋列值  后台动态拼接    
                     var column = eval('(' + result.msg+ ')');　                                        
                    //查询值
                     $("#data-list").datagrid({ //重新加载datagrid
                     	title:'数据绑定列表',
         	   			url:'dataList.do',
         	   			queryParams:{dirId:$("#dirId").val()},
         	   			columns:[
                                       column
         	   			         ],
         				toolbar:[
         				         {
         					         id : 'btngxBind',
         							 text : '数据绑定',
         							 btnType : 'add',       						      
         						}]                		 
                  	});                    
				});   */            
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
	XHY.dataShow.init();	  
});		