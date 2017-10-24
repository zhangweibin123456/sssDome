$package('XHY.dictionary');
XHY.dictionary = function(){
	var _box = null;
	var _selDicType = $("#selDicTypeId");
	var _selParentId = $("#selParentId");
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
	}
	var _this = {			
		addDicDataAction:'add.do',
		addDicDataWin:$('#edit-win'),
		addDicDataForm:$('#editForm'),
		addDicTypeAction:'saveDicType.do',
		addDicTypeForm:function(){
			return $("#addDicTypeForm");
		},
		addDicTypeWin:function(){
			return $("#add-dictype-win");
		},
		saveDicType:function(){
			if(_this.addDicTypeForm().form('validate')){
				XHY.progress('Please waiting','Save ing...');//缓冲条
				_this.addDicTypeForm().attr('action',_this.addDicTypeAction);
				XHY.saveForm(_this.addDicTypeForm(),function(data){
					XHY.closeProgress();//关闭缓冲条
					_this.addDicTypeWin().dialog('close');
					XHY.alert("提示", data.msg, "", "");
				});
			 }
		},
		initForm:function(){
			//添加数据类别
			_this.addDicTypeWin().find("#btn-dictype-submit").click(function(){
				_this.saveDicType();
			});
			_this.addDicTypeWin().find("#btn-pwd-close").click(function(){	
				$.messager.confirm('确认','你确定要退出吗?',function(r){  
				    if (r){  
				     	_this.addDicTypeWin().dialog('close');
				    }  
				});
			});
		},
		config:{
			event:{
				add:function(callback){
					var node=$('#searchForm #dataTypeId').combobox('getValue');
					_this.addDicDataWin.dialog('open');
					_this.addDicDataForm.resetForm();
					if(node!=''){
						_selDicType.combobox('setValue',node);
					}
				},
				//刷新Grid 数据
				refresh: function(){
					var param = $("#searchForm").serializeObject();
					$('#data-list').datagrid('reload',param);
					var newValue=$('#dataTypeId').combobox('getValue');
					$("#selDicTypeId").combobox('setValue',newValue);
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
				}
			},			
  			dataGrid:{
  				title:'数据字典列表',
	   			url:'dataList.do',
	   			idField:'dataId',
	   			columns:[[
						{field:'dataId',checkbox:true},
						{field:'dataKey',title:'字典索引',width:100,sortable: false},
						{field:'dataValue',title:'字典数值',width:300,sortable: false}
				]],
				toolbar:[
					{id:'btnadd',text:'添加',btnType:'add'},
					{id:'btnedit',text:'编辑',btnType:'edit'},
					{id:'btndelete',text:'删除',btnType:'remove'}
					
				]
			}
		},
		init:function(){
			_this.initForm();
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	XHY.dictionary.init();
	$('#dataTypeId').combobox({
		onChange:function(newValue,oldValue){
			var param = $("#searchForm").serializeObject();
			$('#data-list').datagrid('reload',param);
			$("#selDicTypeId").combobox('setValue',newValue);
	    }
	});
	$("#btnadddictype").click(function(){
		$("#add-dictype-win").dialog('open');
		$("#addDicTypeForm").resetForm();
	});
	$("#selDicTypeId").combobox({
		onChange:function(newValue,oldValue){
			$("#selParentId").combobox({url:'dicTypeDataJSON.do?typeId='+newValue,valueField:'dataId',textField:'dataValue',editable:false});
		}
	});
});		