$package('XHY.sysMenu');

XHY.sysMenu = function(){
	var _box = null;
	// 记录进入几级菜单管理
	var flag = 0;
	// 回退flag
	var backflag = false;
	// 2级菜单id
	var idSec = 0;
	var _this = {
		toList:function(parentId){
				_box.form.search.resetForm();
				if(parentId){
					$('#search_parentId').val(parentId);
					$('#btnback').linkbutton('enable');
					if(flag==0) { // 0永远是前进
						idSec = parentId;
						_box.grid.datagrid('showColumn','childMenus');
						backFlag = false;
					};
					if(flag==1) {
						if(backFlag){ // 如果返回
							_box.grid.datagrid('showColumn','childMenus');
						} else {     // 如果前进
							_box.grid.datagrid('hideColumn','childMenus');
							backFlag = false;
						}
//						_box.grid.datagrid('hideColumn','childMenus');
						
					}
					if(!backFlag) //如果不回退，就+1
					flag ++;
				}else{
					$('#btnback').linkbutton('disable');
					$('#btnback').linkbutton('disable');
					_box.grid.datagrid('showColumn','childMenus');
				}
				_box.handler.refresh();
		},
		//设置默认按钮数据
		addDefBtns:function(){
			var defaultBtns= [
				{"btnName":"添加","menuid":2,"actionUrls":"save.do","btnType":"add"},
				{"btnName":"修改","menuid":2,"actionUrls":"getId.do|save.do","btnType":"edit"},
				{"btnName":"删除","menuid":2,"actionUrls":"delete.do","btnType":"remove"}
			];
			var tbline = $(".tb-line:visible");
			var btnType = $("input[name='btnType']",tbline);
			$.each(defaultBtns,function(i,btn){
				var isExists = false;
				//判断按钮类型是否存在
				if(btnType.length > 0){
					for(var i =0; i < btnType.length; i++){
						if(btnType.eq(i).val() == btn.btnType){
							isExists = true;
							break;
						}
					}
				}
				if(!isExists){
					_this.addLine(btn);
				}
			});
		},
		addLine: function(data){
			var table = $("#btn-tb");
			var	html = "<tr class='tb-line'>";
			html+=	   "	<td align='center'><span  class='newFlag red'>*</span></td>";
			html+=	   "	<td><input name=\"btnName\" class=\"easyui-validatebox text-name\" style=\"width:100%\" data-options=\"required:true\"></td>";
			html+=	   "	<td><input name=\"btnType\" class=\"easyui-validatebox text-name\" style=\"width:100%\" data-options=\"required:true\"></td>";
			html+=	   "	<td><input name=\"actionUrls\" class=\"easyui-validatebox text-desc\" style=\"width:100%\"  ></td>";
			html+=	   "	<td align='center'><a class=\"easyui-linkbutton remove-btn\"  iconCls=\"icon-remove\" plain=\"true\"></a>";
			html+=	   "	<input class=\"hidden\" name=\"btnId\">";
			html+=	   "	<input class=\"hidden\" name=\"deleteFlag\">";
			html+=	   "	</td>";
			html+=	   "</tr>";
			var line = $(html);
			//版定删除按钮事件
			$(".remove-btn",line).click(function(){
				XHY.confirm('确认','你确定你要删除记录?',function(r){
					if(r){
						_this.delLine(line);
					}
				});
			});
			if(data){
				if(data.id){
					$(".newFlag",line).html(''); //清空新增标记
				}
				$("input[name='btnId']",line).val(data.id);
				$("input[name='btnName']",line).val(data.btnName);
				$("input[name='btnType']",line).val(data.btnType);
				$("input[name='actionUrls']",line).val(data.actionUrls);
			}
			$.parser.parse(line);//解析esayui标签
			table.append(line);
			
		},
		//删除全部
		delAllLine:function(b){
			if(b){
				$(".tb-line").remove();
			}else{
				$(".tb-line").each(function(i,line){
					_this.delLine($(line));
				});
			}
		},
		//删除单行
		delLine:function(line){
			if(line){
				var btnId = $("input[name='btnId']",line).val();
				if(btnId){
					$("input[name='deleteFlag']",line).val(1); //设置删除状态
					line.fadeOut();
				}else{
					line.fadeOut("fast",function(){
						 $(this).remove();
					});
				}
			}
		},
		config:{
  			action:{
  				save:'', //新增&修改 保存Action  
  				getId:'',//编辑获取的Action
  				delele:''//删除数据的Action
  			},
  			event:{
  				add : function(){
  					_this.delAllLine(true);//清空已有的数据
  					_box.handler.add();//调用add方法
					var parentId =$('#search_parentId').val();
					if(parentId){
						$("#edit_parentId").val(parentId);
					}
				},
				edit:function(){
					_this.delAllLine(true);
					_box.handler.edit(function(result){
						$.each(result.data.btns,function(i,btn){
							_this.addLine(btn);
						});
					});
				}
  			},
  			dataGrid:{
  				title:'菜单列表',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'菜单名称',width:150,sortable: false},
					{field:'rank',title:'排序',align:'right',width:80,sortable: false},
					{field:'url',title:'系统url',width:220,sortable: false},
					{field:'createTime',title:'创建时间',width:130,sortable: false},
					{field:'updateTime',title:'更新时间',width:130,sortable: false},
					{field:'childMenus',title:'子菜单',width:120,formatter:function(value,row,index){
						var html ="<a href='#' style='text-decoration:underline;color:#0000CD' onclick='XHY.sysMenu.toList("+row.id+")'>子菜单管理("+row.subCount+")</a>";
						return html;
					}}
					
				]],
				toolbar:[
					{id:'btnadd',text:'新增',btnType:'add'},
					{id:'btnedit',text:'编辑',btnType:'edit'},
					{id:'btndelete',text:'删除',btnType:'remove'},
					{
						id:'btnback',
						text:'back',
						disabled: true,
						iconCls:'icon-back',
						handler:function(){
							backFlag = true;
							flag--;
							if(flag==1){
								_this.toList(idSec);
							}else{
								_this.toList();
							};
							
						}
					}
				]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
			$('#addLine_btn').click(_this.addLine);
			$('#addDefLine_btn').click(_this.addDefBtns);
			$('#delAllLine_btn').click(function(){
				XHY.confirm("确认","你确定要删除记录?",function(r){
					if(r){
						_this.delAllLine(false);
					}					
				});
			});
			
		}
	};
	return _this;
}();

$(function(){
	XHY.sysMenu.init();
});		