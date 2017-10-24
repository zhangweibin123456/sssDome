$package('XHY.sysOperationLog');
XHY.sysOperationLog = function(){
	var searchForm=$("#searchForm");
	var _box = null;
	var _this = {	
		deleteLogsAction:'deleteOpeLogs.do',
		deleteLogsForm:function(){
			return $("#deleteLogsForm");
		},
		deleteLogsWin:function(){
			return $("#delete-logs-win");
		},
		deleteOpeLogs:function(){
			var beginCreateTime=$("#beginCreateTime").datebox('getValue');
			var endCreateTime=$("#endCreateTime").datebox('getValue');
			if(jQuery.trim(beginCreateTime)==''|| jQuery.trim(endCreateTime)==''){
				XHY.alert("提示", "请输入删除时间", null, null);
			}else{				
				$.messager.confirm('确认','你确定要删除记录?',function(r){
					if(r){
						XHY.progress();
						var url=_this.deleteLogsAction;
						var option={};
						option['beginCreateTime']=(beginCreateTime);
						option['endCreateTime']=(endCreateTime);
						XHY.ajaxJson(url, option, function(result){
							XHY.closeProgress();
							_this.deleteLogsWin().dialog('close');
							_this.deleteLogsForm().resetForm();
							XHY.alert("提示", result.msg);
							var param = $("#searchForm").serializeObject();
							 $('#data-list').datagrid('reload',param);
						});
					}					
				});				
			}
		},
		initForm:function(){
			//删除审计日志
			_this.deleteLogsWin().find("#btn-dellogs-submit").click(function(){
				_this.deleteOpeLogs();
			});
			_this.deleteLogsWin().find("#btn-dellogs-close").click(function(){	
				_this.deleteLogsWin().dialog('close');
			});
			searchForm.find("#btn-clearsearch").click(function() {
				$('input').attr("value","");
				return false;
			});
		},
		config:{
  			dataGrid:{
  				title:'日志列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'userName',title:'用户名',width:130,sortable: false},
						{field:'IP',title:'用户IP',align:'right',width:130},
						{field:'operation',title:'操作类型',align:'center',width:130,sortable: false},
						{field:'createTime',title:'操作时间',width:150,sortable: false},
						{field:'record',title:'日志内容',width:520,sortable: false}					 						
				]],
				toolbar:[
					{id:'btndelete',text:'删除',btnType:'remove'},
					{id:'btndellogs',text:'批量删除',btnType:'remove',handler:function(data){
						_this.deleteLogsWin().window('open');
					}},
					{id:'btnexport',text:'导出',btnType:'export',iconCls:'icon-reload',handler:function(){
						var createTime=$("[name='createTime']").val();
						if(jQuery.trim(createTime)!=''){
							window.location=urls['msUrl']+"/sysOperationLog/export.do?createTime="+createTime;	
						}else{
							window.location=urls['msUrl']+"/sysOperationLog/export.do";
						}						
					}},
				]
			}
		},
		init:function(){
			_this.initForm();
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	};
	return _this;
}();

$(function(){
	XHY.sysOperationLog.init();
});