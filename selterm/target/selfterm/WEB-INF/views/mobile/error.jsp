<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>信息提示</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0" name="viewport">
</head>
<style type="text/css">
.b1{ width:40%;margin:80px auto 30px; text-align:center;  }
.b2{font-size:20px; text-align:center; }
.b3{ font-size:16px;  text-align:center;}
.b3 a{color: #0B72A4; text-decoration:none;}
.b3 span{  color:#ccc; }
img{ width:100%; max-width:500px; }
</style>
<body>
	<div class="b1">
		<img src="<%=request.getContextPath()%>/man/images/mobile/message.png" alt="">
	</div>
	<div class="b2">
        <p>${message}</p>
      </div>
</body>
</html>