<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>扫描识别系统</title>
    <%@include file="/view/resource.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript">
    	var uname = '${user.nickName}';
    </script>
    <script type="text/javascript" src="js/ux/main/main.js"></script>
  </head>
  
  <body id="wrap" class="easyui-layout">
 	<div id="head" data-options="region:'north',split:false,border:false" style="height:115px;overflow: hidden;border:none;">
	 	<div id ="logo" class="logo" data-options="closed:true">
	 		<div id="banner" class="banner"></div>
	 		<div id = "rightLogo" style="float:right;padding-right:5px;width:120px;border:none;"></div>
		</div>
		
	 	<div class="ui-header" style="height:32px;border:none">
		 	
		 	<div class="ui-login">
		 		<div class="ui-login-info">
<!-- 			 		<ul> -->
			 		当前用户： <span id='cuser' class="orange">${user.nickName}</span>
			 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 		当前时间： <span id="todayTime"></span>
			 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="help"><a href="${msUrl}/view/help/help.chm">帮助中心</a></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="support"><a href="javascript:void(0);">技术咨询</a></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="modify-pwd-btn"><a href="javascript:void(0);">修改密码</a></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="logout"><a href="${msUrl}/logout.shtml">退出登录</a></span>
<!-- 					</ul> -->
			 	</div>
		 	</div>
<!-- 		 	<h1 id="ccc"></h1> -->
	 	</div>
 	</div>
 	
	<!-- 树形菜单 -->
	<div data-options="region:'west'" style="width:200px;">
		<div id="tree-box" class="easyui-accordion" data-options="fit:true,border:false" style="background-color: #309de9; border-right: 3px solid #013e7f;">
			<c:forEach var="item" items="${menuList}">
			<div  title="${item.text}" style="overflow:hidden;">
			<div class="easyui-panel" data-options='fit:true,border:false'> 
				<c:forEach var="node" items="${item.children}">
					<c:choose>
						<c:when test="${empty node.children}">
							<a class="menu-item" href="${msUrl}${node.url}" title="${node.text}">${node.text}</a>
						</c:when>
						<c:otherwise><a class="menu-item1" href="#" title="${node.text}">${node.text}</a></c:otherwise>
					</c:choose>
					<c:if test="${!empty node.children}">
					<ul class="sslist">
					<c:forEach var="node2" items="${node.children}">
					<li class=l3><a class="threeMenu" href="${msUrl}${node2.url}" title="${node2.text}">${node2.text}</a>
					</c:forEach>
					</ul>
					</c:if>
				</a>
				</c:forEach>
			</div>
			</div>
			</c:forEach>
		</div>
	</div>
	
	<div data-options="region:'south',split:false,border:false" >
		<div class="bottom" style="border: none;text-align: center;color:#fff">CopyRight &copy; 2015 &nbsp; 吴忠仪表责任有限公司</div>
	</div>
	
	<!-- 中间内容页面 -->
	<div data-options="region:'center'" style="border-left: 0px;">
		<div class="easyui-tabs" id="tab-box" data-options="fit:true,border:false" >
			<div title="主页" style="padding:20px;overflow:hidden;width:100%;height:100%" id="todo"> 
			
				<!--<div style="width:49%;height:400px;float:left;">
					<div class="todo-head" id="todo-head" ></div> 
					<div class="easyui-todo" id="easyui-todo" > 
					
						<div id="todo1" class="menu-item-db" style="width:580px;border-bottom: 1px dotted blue;">
			               <div class="todo-title" style="float:left; width:200px;padding-left: 25px;background: #ff0000""></div>
			               <div class="todo-logo"  style="float:right;width:80px;background:url(images/logo/20.jpg)"></div>
			               <div class="todo-date"  style="float:right;width: 100px;padding-right: 25px;background: #eee"></div>
			            </div>
						
					</div>
				</div>-->
				<!--<div style="width:49%;height:400px;float:right;">
					<div class="todo-head1" id="todo-head1" ></div> 
					<div class="easyui-todo" id="note-todo">
					
						<div id="todo1" class="menu-item-db" style="width:580px;border-bottom: 1px dotted blue;">
			               <div class="todo-title" style="float:left; width:200px;padding-left: 25px;background: #ff0000""></div>
			               <div class="todo-logo"  style="float:right;width:80px;background:url(images/logo/20.jpg)"></div>
			               <div class="todo-date"  style="float:right;width: 100px;padding-right: 25px;background: #eee"></div>
			            </div>
						
					</div>
				</div>-->
			</div>	
		</div>
	
		<!--  右键菜单 -->
		<div id="tab_rightmenu" style="width:40px;background-color:white;">
			<div name="tab_menu-tabclose">关闭标签页</div>
			<div name="tab_menu-tabcloseall">关闭全部标签页</div>
			<div name="tab_menu-tabcloseother">关闭其他标签页</div>
			<!-- <div class="menu-sep"></div> -->
			<!-- <div name="tab_menu-tabcloseright">关闭右侧标签页</div> -->
			<!-- <div name="tab_menu-tabcloseleft">关闭左侧标签页</div> -->
		</div>	
	
		<!--  技术咨询 -->
		<div id="support-win" class="easyui-dialog" buttons="#editPwdbtn"
			data-options="closed:true,modal:true"
			style="width:350px;height:230px;">
			<div style="padding:10px;">
				吴忠仪表：&nbsp;&nbsp;&nbsp;&nbsp;0952-2218431<br>
				技术部：&nbsp;&nbsp;&nbsp;&nbsp;0952-2218749<br>
				计算机中心：&nbsp;&nbsp;&nbsp;&nbsp;0952-2218092<br>
			</div>
			<div id="editPwdbtn" class="dialog-button">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					id="btn-support-close">关闭</a>
			</div>
		</div>
		
		<!--  修改密码 -->
		<div id="modify-pwd-win"  class="easyui-dialog" title="修改密码" buttons="#editPwdbtn" data-options="closed:true,modal:true" style="width:350px;height:200px;">
			<form id="pwdForm" action="modifyPwd.do" class="ui-form" method="post">
	     		 <input class="hidden" name="id">
	     		 <input class="hidden" name="email">
	     		 <div class="ui-edit">
		           <div class="fitem">  
		              <label>输入旧密码:</label>  
		              <input id="oldPwd" name="oldPwd" type="password" class="easyui-validatebox"  data-options="required:true"/>
		           </div>
		            <div class="fitem">  
		               <label>输入新密码:</label>  
		               <input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" />
		           </div> 
		           <div class="fitem">  
		               <label>再次输入新密码:</label>  
		              <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"   required="required" validType="equals['#newPwd']" />
		           </div> 
		         </div>
	     	 </form>
	     	 <div id="editPwdbtn" class="dialog-button" >  
	            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-submit">提交</a>  
	            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-close">关闭</a>  
	         </div>
		</div>
	
		<!-- 编辑 Form -->
	    <div id="edit-win" class="easyui-dialog" title="系统消息"  data-options="closed:true,modal:true" style="width:400px;height:320px;">  
	     	<form id="editForm" class="ui-form" method="post">  
	     		 <input class="hidden" type="text" name="id">
	     		 <input class="hidden" name="deleted">
	     		 <div class="ui-edit">
		     	   <div class="ftitle">消息通知</div>    
		          
		           <div class="fitem">  
		               <label>创建人员:</label>  
		               <input class="easyui-validatebox" type="text" id="createBy" data-options="required:true">
		           </div>  
		           <div class="fitem">  
		               <label>创建时间:</label>
		               <input class="easyui-validatebox" type="text" id="createTime" data-options="required:true">
		           </div> 
		           <div class="fitem">  
		               <label>通知单位:</label>
		               <input class="easyui-validatebox" type="text" id="infoTowards" data-options="required:true"  style="width:200px;">
		           </div>
		           <div class="fitem">  
		               <label>通知标题:</label>
		               <input class="easyui-validatebox" type="text" id="infoTitle" data-options="required:true" style="width:200px;">
		           </div> 
		           <div class="fitem">  
		               <label>通知内容:</label>  
		               <textarea class="easyui-validatebox" type="text" rows="6" id="infoContent" data-options="required:true" style="width:200px;height:100px;"></textarea>  
		               
		           </div>
		         </div>
	     	</form>
	  	</div> 

  </body>
  
  <script type="text/javascript">
                                $(document).ready(function(){
                                		var tabsId = 'tab-box';//tabs页签id
                                		var tab_rightmenuId = 'tab_rightmenu';//tabs右键菜单id
										var oTimer = null; 
                                        setInterval(showTime, 1000);   
                                        oTimer = setInterval(autoScroll,2000);    
                                                                 
                                        function timer(obj,txt){
                                           obj.text(txt);
                                        };    
                                        function autoScroll(obj){
										    $('#scrollDiv').find("ul:first").animate({ 
												marginTop:"-25px" 
												},500,function(){ 
												$(this).css({marginTop:"0px"}).find("li:first").appendTo(this); 
											}); 
										};  
                                        function showTime(){                                
                                                var today = new Date();
                                                var weekday=new Array(7)
                                                weekday[0]="星期一"
                                                weekday[1]="星期二"
                                                weekday[2]="星期三"
                                                weekday[3]="星期四"
                                                weekday[4]="星期五"
                                                weekday[5]="星期六"
                                                weekday[6]="星期日"                                        
                                                var y=today.getFullYear()+"年";
                                                var month=today.getMonth()+1;
                                                month = (month<10?"0"+month:month)+"月";
                                                var td=today.getDate();
                                                td = (td<10?"0"+td:td) +"日";
                                                var d=weekday[today.getDay()];
                                                var h=today.getHours();
                                                var m=today.getMinutes();
                                                var s=today.getSeconds();  
                                                var date = y+month+td+" "+(h<10?"0"+h:h)+":"+(m<10?"0"+m:m)+":"+(s<10?"0"+s:s);      
                                                timer($("#todayTime"),date);
                                                timer($("#todayT"),date);
                                        } ;
                                        // 鼠标滑过的时候暂停滚动
                                        $('#scrollDiv').mouseover(function(){
											 window.clearInterval(oTimer);
											 //alert($(this).find("ul li:eq(0)").html());
											 //$(this).find("ul li:eq(0)").remove();
										});
										// 鼠标离开的时候继续滚动
										$('#scrollDiv').mouseout(function(){
											 oTimer = setInterval(autoScroll,2000);
										});
										// 鼠标点击的时候，弹出消息框，并删除已读取记录
										$('#scrollDiv').click(function(){
											 $(this).find("ul li:eq(0)").remove();
											 oTimer = setInterval(autoScroll,2000);
										});										
                                    	//绑定tabs的右键菜单
                                    	$("#"+tabsId).tabs({
                                    		onContextMenu:function(e,title){//这时去掉 tabsId所在的div的这个属性：class="easyui-tabs"，否则会加载2次 
                                    			e.preventDefault();
                                    			$('#'+tab_rightmenuId).menu('show',{
                                    				left: e.pageX,
                                    				top: e.pageY
                                    		    }).data("tabTitle",title);
                                    		}
                                    	});                                    	
                                    	//实例化menu的onClick事件 
                                    	$("#"+tab_rightmenuId).menu({ 
                                    		onClick:function(item){
                                    			CloseTab(tabsId,tab_rightmenuId,item.name);
                                    		}
                                    	});
                                
		                                /**  tab关闭事件  
			                                @param tabId  		tab组件Id   
			                                @param tabMenuId 	tab组件右键菜单Id   
			                                @param type   		tab组件右键菜单div中的name属性值  
		                                */  
		                                function CloseTab(tabId,tabMenuId,type){
			                                //tab组件对象  
			                                var tabs = $('#' + tabId);
			                                //tab组件右键菜单对象  
			                                var tab_menu = $('#' + tabMenuId);
			                                 
			                                //获取当前tab的标题   
			                                var curTabTitle = tab_menu.data('tabTitle');     
			                                //关闭当前tab   
			                                if(type === 'tab_menu-tabclose'){   
				                                //通过标题关闭tab    
				                                tabs.tabs("close",curTabTitle);  
			                                }     
			                                //关闭全部tab   
			                                else if(type === 'tab_menu-tabcloseall'){   
				                                //获取所有关闭的tab对象    
				                                var closeTabsTitle = getAllTabObj(tabs);   
				                                //循环删除要关闭的tab     
				                                $.each(closeTabsTitle,function(){
					                                var title = this;    
					                                tabs.tabs('close',title);    
				                                });   
				                            }     
				                            //关闭其他tab   
				                            else if(type === 'tab_menu-tabcloseother'){   
					                            //获取所有关闭的tab对象    
					                            var closeTabsTitle = getAllTabObj(tabs);   
					                            //循环删除要关闭的tab   
					                            $.each(closeTabsTitle,function(){    
						                            var title = this;    
						                            if(title != curTabTitle){     
						                            	tabs.tabs('close',title);     
						                            }    
					                            });   
				                            }     
				                            //关闭当前左侧tab   
				                            else if(type === 'tab_menu-tabcloseleft'){   
					                            //获取所有关闭的tab对象    
					                            var closeTabsTitle = getLeftToCurrTabObj(tabs,curTabTitle);   
					                            //循环删除要关闭的tab   
					                            $.each(closeTabsTitle,function(){    
						                            var title = this;    
						                            tabs.tabs('close',title);   
					                            });   
				                            }     
				                            //关闭当前右侧tab   
				                            else if(type === 'tab_menu-tabcloseright'){   
					                            //获取所有关闭的tab对象    
					                            var closeTabsTitle = getRightToCurrTabObj(tabs,curTabTitle);   
					                            //循环删除要关闭的tab   
					                            $.each(closeTabsTitle,function(){
						                            var title = this;    
						                            tabs.tabs('close',title);   
					                            });   
				                            }  
			                            }
			                            
			                            /**  
			                            	获取所有关闭的tab对象   
			                            	@param  tabs tab组件
			                            */
			                            function getAllTabObj(tabs){  
				                            //存放所有tab标题  
				                            var closeTabsTitle = [];  
				                            //所有所有tab对象  
				                            var allTabs = tabs.tabs('tabs');  
				                            $.each(allTabs,function(){   
					                            var tab = this;    
					                            var opt = tab.panel('options');   
					                            //获取标题   
					                            var title = opt.title;    
					                            //是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡   
					                            var closable = opt.closable;   
					                            if(closable){    
						                            closeTabsTitle.push(title);    
						                            }   
				                            });   
				                            return closeTabsTitle;  
			                            }  
			                            
			                            /**  
			                            	获取左侧第一个到当前的tab  
			                            	@param tabs   tab组件  
			                             	@param  curTabTitle 到当前的tab  
			                            */  
			                            function getLeftToCurrTabObj(tabs,curTabTitle){  
				                            //存放所有tab标题  
				                            var closeTabsTitle = [];  
				                            //所有所有tab对象 
				                            var allTabs = tabs.tabs('tabs');  
				                            for(var i=0;i<allTabs.length;i++){   
					                            var tab = allTabs[i];   
					                            var opt = tab.panel('options');   
					                            //获取标题   
					                            var title = opt.title;    
					                            //是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡   
					                            var closable = opt.closable;   
					                            if(closable){    
						                            //alert('title' + title + '  curTabTitle:' + curTabTitle);    
						                            if(title == curTabTitle){     
						                            	return closeTabsTitle;     
						                            }     
						                            closeTabsTitle.push(title);      
					                            }
				                            }   
				                             return closeTabsTitle;  
			                             }
			                             
			                             /**  
			                             	获取当前到右侧最后一个的tab  
			                             	@param tabs   tab组件   
			                             	@param  curTabTitle 到当前的tab  
			                             */  
			                             function getRightToCurrTabObj(tabs,curTabTitle){  
				                             //存放所有tab标题 
				                              var closeTabsTitle = [];  
				                             //所有所有tab对象  
				                             var allTabs = tabs.tabs('tabs');   
				                             for(var i=(allTabs.length - 1);i >= 0;i--){   
					                             var tab = allTabs[i];   
					                             var opt = tab.panel('options');   
					                             //获取标题   
					                             var title = opt.title;    
					                             //是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡   
					                             var closable = opt.closable;   
					                             if(closable){    
						                             //alert('title' + title + '  curTabTitle:' + curTabTitle);    
						                             if(title == curTabTitle){     
						                             	return closeTabsTitle;     
						                             }     
						                             closeTabsTitle.push(title);    
					                             }   
				                             }   
				                             return closeTabsTitle;  
			                             }
                                });
	</script>
</html>