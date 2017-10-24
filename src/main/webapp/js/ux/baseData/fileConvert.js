$package('XHY.fileConvert');
 
$(function(){
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
			XHY.alert('警告','未选中记录！','warning');  
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
			XHY.alert('警告','一次只能选择一行记录,如需全部下载请点击批量打包下载！','warning');  
			return false;
		}
	};
	
	
	
	
	
	
	$("#btn-upload").click(function(){		
		var uploadWin =  $("#sysUserImportDlg");
	    uploadWin.window('open');
		uploadWin.find("#sysUserImport").uploadify({
//			 'onInit':function(){
//				 removeCompleted:true,
//			 },
		    height : '25',
		    width : '100',
		    'swf' : urls['msUrl']+'/js/uploadify/uploadify.swf',
		    'uploader' : urls['msUrl']+'/fileConvert/upload.do?var='+(new Date()).getTime(),
			'cancelImg' : urls['msUrl']+'/js/uploadify/uploadify-cancel.png',
			'debug' : false,
			'fileObjName' : 'file',
			'buttonClass' :null,
			'buttonText' : '上传文件',
			'buttonImage' :  urls['msUrl']+'/images/11.png',
			'fileTypeDesc' : 'xls|xlsx  Files',
		    'fileTypeExts' : '*.xls; *.xlsx',
		    'removeCompleted':true,
		     removeTimeout: 3,
			onUploadSuccess : function(file, data, response) {
				var data = eval('(' + data+ ')');
				var message =  data.rows;	
				var fileBean=data.fileBean;
				uploadWin.window('close');
                
                //查询值
                $("#data-list").datagrid({ //重新加载datagrid               	
                	title : '下载文件列表',   	
                	data:message,
                	striped:true,
                	rownumbers: true,
                	columns : [ [ 
                 {field:'id',checkbox:true},
            	    {
            	    	 field : 'fileName',
            	    	 title : '文件名称',
            	    	 align : 'center',
            	    	 width : 300,
            	    	 sortable : false}, 
            		{
            			field : 'filePath',
            			title : '文件路径',
            			align : 'center',
            			width : 800,
            			sortable : false
            		}
//            		{field:'id1',title: '下载文件',align: 'left',width:80,  
//                        //添加超级链 
//                        formatter:function(value,row,index){
//                        	var path =  row.filePath;
//                        var show="";
//                        if(path.substr(-4)=='.zip'){
//                        	show="下载Zip文件";
//                        	
//                        }else{
//                        	show="下载pdf文件";
//                        }
//              
//                            //return "<a href="+urls['msUrl']+"'/fileConvert/download.shtml?path="+path+"'>下载pdf文件</a>";                       
//                        	return "<a href='/scan/fileConvert/download.shtml?path="+path+"' style='text-decoration:underline;color:#0000CD'>" +
//                        			"" +show +"</a>";   
//                       }  
//                    }
            		]],
            		
            		toolbar:[
					         {
						         id : 'download',
								 text : '下载',
								 iconCls: 'easyui-linkbutton',
								 btnType : 'add',
								 handler:function(){
									
										var records = Utils.getCheckedRows();
									    if (Utils.checkSelectOne(records)){ 
								    	//console.log("records[0]="+records[0].filePath);
									    	//XHY.confirm('121', '121');
									    	
								    //var url='/scan/fileConvert/download.shtml?path='+records[0].filePath;
									    	var url='/scan/fileConvert/down.shtml?path='+records[0].filePath;
									    	
								  
							    	 window.location.href = url;
								    
	
									    	
									    }
									 
									 	 						
								   }
							   },'-',
							   {
							         id : 'downloadAll',
									 text : '批量打包下载',
									 iconCls: 'easyui-linkbutton',
									 btnType : 'add',
									 handler:function(){
										 
										//XHY.alert(fileBean.filePath, fileBean.filePath);
										 
										 var url='/scan/fileConvert/down.shtml?path='+fileBean.filePath;
								    	 window.location.href = url;
										 	 						
									   }
								   }
					         
					         
					         
					         
                               ]
                
                
            		
            		
            		
            		
            		 
             	});             
			}
		});
 
	});
	
	
	 
});	