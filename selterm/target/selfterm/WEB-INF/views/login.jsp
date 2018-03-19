<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8"></meta>
	<title>一卡通</title>
	<link href="<%=request.getContextPath()%>/man/images/bitbug_favicon1.ico" rel="shortcut icon"/>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"></meta>
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0" name="viewport"></meta>
  	<meta name="apple-mobile-web-app-status-bar-style" content="black"></meta>
  	<meta name="apple-mobile-web-app-capable" content="yes" />
  	<meta name="format-detection" content="telephone=no"/>
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/man/css/min/bootstrap.min.css" type="text/css"/>
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/man/css/min/base.min.css" type="text/css"/>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/min/style.min.css?rev=2016413" id="cssStyle"/>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/min/home.min.css"/>
  	<script type="text/javascript"  src="<%=request.getContextPath()%>/man/js/min/jquery.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/man/js/min/bootstrap.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/man/js/min/style.min.js"></script>
<style>
  body{ background:url(<%=request.getContextPath()%>/man/images/login_bg.jpg);  background-size:100% 100%; position:relative; }
  .signin{ width:450px; height: 430px; position:absolute; background:rgba(255,255,255,.6); border-radius:10px;left:50%; margin-left:-225px;top:50%; margin:-250px 0 0 -225px;}
  .sigin-tit{ text-align:center; font-size:30px; margin-top:12px; }
  .sigin-tit img{ width:110px; height:110px; }
  .sigin-tit small{ display:block; line-height:1; margin-top:13px; letter-spacing:3px; }
  .signin-form{ padding:32px 50px 0; }
  .signin-form .signin-txt,  .signin-sub{ width:100%; height:40px; border:none; border-radius:4px; font-size:16px; margin-bottom:20px; }
  .signin-form .signin-name{ background:#fff url(<%=request.getContextPath()%>/man/images/login_use.png) no-repeat 95%; }
   .signin-form .signin-pwd{ background:#fff url(<%=request.getContextPath()%>/man/images/login_pas.png) no-repeat 95%; }
   .signin-check{ width:16px; height:16px; margin-right:5px; }
   .signin-form .signin-word{ color:#999; }
    .signin-form .signin-sub{ background:#55b7fa; border:none; margin-top:15px; height:40px; font-size:18px; }
    .errMsg{color:red; text-align:center;}
</style>

<script type="text/javascript"> 
		document.onkeydown = function(event){ 
			e = event ? event :(window.event ? window.event : null); 
				if(e.keyCode==13){ 
					//执行的方法 
					if(""==$.trim($("#userId").val())){
						$("#errMsg").html("用户名不能为空。");
						$("#userId").focus();
						return false;
					}
					if(""==$.trim($("#password").val())){
						$("#errMsg").html("密码不能为空。");
						$("#password").focus();
						return false;
					}
					window.name="1";
					$("#loginForm").submit();
				} 
		};
		 history.forward();
		$(document).ready(function() {
			
		   $(window).on("unload",function(){
		   });
			setFocus();
			loginValidate();
		});
		

		function setFocus() { 
			document.getElementById('userId').focus();
		}
		function loginValidate() {
			$("#loginBtn").click(function() {
				if ("" == $.trim($("#userId").val())) {
					$("#errMsg").html("用户名不能为空。");
					$("#userId").focus();
					return false;
				}
				if ("" == $.trim($("#password").val())) {
					$("#errMsg").html("密码不能为空。");
					$("#password").focus();
					return false;
				}
				window.name="1";
				$("#loginForm").submit();
			});
		}
</script>

</head>

<body>
    <div class="signin">
    <h2 class="sigin-tit"><img src="<%=request.getContextPath()%>/man/images/login_logo.jpg" alt=""/><small>一卡通系统</small></h2>
    <form class="signin-form" id="loginForm" method="post" action="<%=request.getContextPath()%>/system/login.html" id="">
      <input class="signin-txt signin-name" maxlength="40" type="text" id="userId" name="loginName" placeholder="用户名"/>
      <input class="signin-txt signin-pwd" maxlength="40" type="password" name="passwd" id="password" placeholder="密码"/>
      <div id="errMsg" class="errMsg">
			${errMsg}
	  </div>
      
      <input class="signin-sub" type="button"  id="loginBtn" value="登录"/>
    </form>
    </div>
</body>
</html>
