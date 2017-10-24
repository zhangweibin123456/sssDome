<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 公共资源CSS,JS  -->
<!--Css -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.3.2/themes/broadengate/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.3.2/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
<link href="${pageContext.request.contextPath}/css/uploadify.css" rel="stylesheet" type="text/css" />
 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/package.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/urls.js?v=11"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/base.js?v=11"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/print/LodopFuncs.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/jscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
   $.extend($.fn.validatebox.defaults.rules, {
     idcard : {// 验证身份证
        validator : function(value) {
// 			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
			return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);  
        },
        message : '身份证号码格式不正确'
    },
    phone : {// 验证电话号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '格式不正确,请使用下面格式:020-88888888'
    },
    mobile : {// 验证手机号码
        validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message : '手机号码格式不正确'
    },
     zip : {// 验证邮政编码
        validator : function(value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message : '邮政编码格式不正确'
    },
     qq : {// 验证QQ,从10000开始
        validator : function(value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message : 'QQ号码格式不正确'
    },
      name : {// 验证姓名，可以是中文或英文
            validator : function(value) {
                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
            },
            message : '请输入姓名'
    },
    email:{
        validator : function(value){
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    },
    message : '请输入有效的电子邮件账号(例：abc@126.com)'    
    },
     integer : {// 验证整数
        validator : function(value) {
            return /^[+]?[1-9]+\d*$/i.test(value);
        },
        message : '请输入整数'
    } ,
    numbers : {// 验证整数
        validator : function(value) {
            return /^[+]?[0-9]+\d*$/i.test(value);
        },
        message : '请输入数字'
    } ,
     postcode:{  
        validator:function(value,param){  
                return /^\d{6}$/.test(value);  
        },  
        message:'请输入正确的6位邮政编码'  
       }          
});

</script> 