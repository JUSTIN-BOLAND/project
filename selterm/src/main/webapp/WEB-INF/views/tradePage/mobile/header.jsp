<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <!--告诉设备忽略将页面中的数字识别为电话号码-->
    <meta content="telephone=no" name="format-detection" />
    <meta name="format-detection" content="email=no"/>
    <!--指定的iphone中safari顶端的状态条的样式-->
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />

    <title>一卡通</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/mui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/app.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/links.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/icons-extra.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/work.css">


    <script type="text/javascript"  src="<%=request.getContextPath()%>/man/js/min/jquery.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/man/js/Validform/Validform.js"></script>
    <script src="<%=request.getContextPath()%>/man/js/json-minified.js"></script>
    <script src="<%=request.getContextPath()%>/man/js/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/man/js/laydate/laydate.js"></script>
    <script src="<%=request.getContextPath()%>/man/js/mobile.js"></script>

<script>
    //f_checkOs("<%=request.getContextPath()%>/trade/error.html?message=请在微信或者支付宝登陆");
</script>
</head>
<body class="add-orgination"　id="add-orgination">

<%--<header class="mui-bar mui-bar-nav" style="display:none;">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" id="backBtn" style="font-size:18px;" onclick="f_goBack();">返回</a>
    &lt;%&ndash;<div class="mui-title"><input placeholder="请在此输入" type="text" id="search" value="${param.searchUser}"></div>&ndash;%&gt;
    <h1 class="mui-title"></h1>
    <a class="mui-action-back mui-icon" style="font-size:18px;float:right;" id="closeBtn" onclick="f_close();">关闭</a>
</header>--%>
<div class="mui-content" style="height:100%;">