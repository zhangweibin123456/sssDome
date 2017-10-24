$package('XHY.alreadyDo');
XHY.alreadyDo = function(){
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
		parentId: $('#parentId'),
		depth: $('#depth'),
		addDirDataAction:'add.do',
		addDirDataWin:$('#edit-win'),
		addDirDataForm:$('#editForm'),
		addDirTypeAction:'save.do',
		addDirTypeForm:function(){
			return $("#addDirTypeForm");
		},
		addDirTypeWin:function(){
			return $("#add-dirtype-win");
		},
		saveDirType:function(){
			if(_this.addDirTypeForm().form('validate')){
				XHY.progress('Please waiting','Save ing...');//缓冲条
				_this.addDirTypeForm().attr('action',_this.addDirTypeAction);
				XHY.saveForm(_this.addDirTypeForm(),function(data){
					XHY.closeProgress();//关闭缓冲条
					_this.addDirTypeWin().dialog('close');
					XHY.alert("提示", data.msg, "", "");
					$('#dirtree').tree('reload');
					var param = $("#searchForm").serializeObject();
					$('#data-list').datagrid('reload',param);
				});				
			 }
		},
		initForm:function(){
			//添加文件目录
			_this.addDirTypeWin().find("#btn-dirtype-submit").click(function(){
			   _this.saveDirType();				 				
			});
			_this.addDirTypeWin().find("#btn-pwd-close").click(function(){	
				$.messager.confirm('确认','你确定要退出吗?',function(r){  
				    if (r){  
				     	_this.addDirTypeWin().dialog('close');
				    }  
				});
			});
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
			
			},			
  			dataGrid:{
  				title:'已办事项列表',
	   			url:'dataList.do',
	   			idField:'id',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'dirName',title:'目录名称',width:150,sortable: false},
						{field:'subjectName',title:'项目名称',width:100,sortable: false},
						{field:'dsName',title:'业务数据源',width:150,sortable: false},
						{field:'status',title:'状态',width:80,sortable: false},
						{field:'opUser',title:'创建人',width:80,sortable: false},
						{field:'opTime',title:'创建时间',width:100,sortable: false},
						{field:'note',title:'备注',width:200,sortable: false}
				]],
				toolbar:[
				          null
				]
			}
		},
		init:function(){
			_this.initForm();
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
		}
	};
	return _this;
}();

$(function(){
	XHY.alreadyDo.init();	  
});		