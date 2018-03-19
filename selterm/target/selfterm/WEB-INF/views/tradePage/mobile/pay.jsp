<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!doctype html>
<html>
<head>
<title>扫码支付</title>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<!--离线应用的另一个技巧-->
<meta content="yes" name="apple-mobile-web-app-capable" />
<!--指定的iphone中safari顶端的状态条的样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!--告诉设备忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection" />
  <script type="text/javascript"  src="<%=request.getContextPath()%>/man/js/min/jquery.js"></script>
<style>
* {
	-webkit-appearance: none;
}
a:visited, a {
	text-decoration: none;
	cursor: pointer;
	color: #575757;
}
input[type="checkbox"] {
	-webkit-appearance: checkbox;
}
body, html, h1, h2, h3, h4, p, ul, li, form {
	margin: 0;
	padding: 0;
	line-height: 28px;
	list-style: none;
}
body, input, textarea, select {
	font-size: 14px;
	font-family: 微软雅黑, 雅黑;
	color: #333;
}
input[type=text], input[type=number], input[type=email], input[type=password], .pxui-text, .pxui-select {
	display: inline-block;
	vertical-align: middle;
	height: 20px;
	line-height: 20px;
	padding: 8px;
	border: 1px solid #cfcfcf;
	border-top: 2px solid #c4cde0;
	border-left-color: #c4cde0;
	border-radius: 3px;
	background: #fff;
	margin: 6px;
}
.pxui-button, input[type=button], input[type=submit], input[type=reset] {
	background: #f7f7f7;
	border: 1px solid #cfcfcf;
	padding: 9px 12px 8px 12px;
	line-height: 35px;
	display: inline-block;
	vertical-align: middle;
	margin: 6px;
	color: #40597d;
	background-image: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#F6F6F6), to(#EEEEEE));
}



body {
	background-color: #E4E4E4;
}
.div1 {
	height: 50px;
	width: 100%;
	text-align: left;
	background-color: #212737;
}
.div1-1 {
	margin-left: 2%;
	width: 10%;
	float: left;
	margin-top: 10px;
}
.div1-2 {
	font-size: 16px;
	color: #FFF;
	font-weight: bold;
	margin-top: 10px;
	width: 88%;
	float: left;
}
.div2 {
	width: 100%;
	height: 100%;
	margin-top: 30px;
}
.div3-img {
	border-radius: 50px;
}
.div4-1 {
	background-color: #FFF;
	width: 100%;
	height: 100%;
	border-bottom: #CCC 1px solid;
	border-top: #CCC 1px solid;
	margin-top: 20px;
}
.div4-2 {
	background-color: #FFF;
	width: 100%;
	height: 100%;
	border-bottom: #CCC 1px solid;
	border-top: #CCC 1px solid;4
}
.div4-3 {
	width: 100%;
	height: 100%;
/*  	background-color: #6C7B8B;  */
}
input[type=button].div4-3-1 {
	width: 100%;
	font-size: 16px;
	    background: #ccc;

	color:#333;
	border-radius:5px;
	margin:30px 0 0;
}
.pd32{
   padding:0 32px;
}
.div-tit{font-size:24px;margin-top:20px;}
/* .div4-3-1 {
	width: 70%;
	background-image: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#666), to(#999)) !important;
	//background-image: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#999), to(#CCC)) !important;
	color:#FFF !important;
	font-weight:bold;
	font-size:18px;
} */
.htop{ width:100%; height:160px; background:#ccc;}
.htop img{ width:100%; height:100%; }
input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
.banner{width: 100%; height:150px;}
.banner img{ width: 100%; height: 100%; }

</style>
</head>
<body>
<c:if test="${not empty adv }">
   <div class="banner">
    	<img src="${adv.advimage }" alt="" onclick="clickAdv('${adv.id }','${adv.advlink }')">
    </div>
 <div>
 </c:if>

 <div>
<div align="center" class="div2">
<div>
<c:if test="${empty logo }">
	<img class="div3-img" src="<%=request.getContextPath()%>/man/images/login_logo.png" width="70" height="70">
</c:if>
<c:if test="${not empty logo }">
	<img class="div3-img" src="${logo }" width="70" height="70">
</c:if>
<%-- <div><img class="div3-img" src="<%=request.getContextPath()%>/man/images/mobile/tx_bea.jpg" width="70" height="70"> --%>
  <div>
    <div><p class="div-tit">${storeName}</p>
      <div> </div>
	  <div class="pd32">
      <div align="center" class="div4-1">消费总金额
        <input style="border:0;" type="number" id="conAmount" maxlength="6" placeholder="输入消费金额">
      </div>
	  </div>
    </div>
	 <div class="pd32">
    <div align="center" class="div4-3">
      <input type="button" style="background:#348903" class="div4-3-1" onclick="tosubmit()" value="确认付款"/>
    </div>
	</div>
    <div align="center" id="errorMsg" class="div4-3">
    </div>
      <div align="center" class="div4-3" style="margin-top:30px;">
	桂林银行收单 0771-5785121
    </div>
  </div>
</div>
</div>
</div>
</body>
 <form action="<%=request.getContextPath()%>/trade/cashier.html" id="calcForm" method="post">
        <input type="hidden" value="${deviceId}" name="storeCode" id="deviceId"/>
        <input type="hidden" value="${payType}" name="payType" id="payType"/>
        <input type="hidden" value="" id="amount" name="amount"/>
        <input type="hidden" value="${code}" id="code" name="code"/>
     <input type="hidden" value="${userId}" id="userId" name="userId"/>　

   </form>
<script type="text/javascript">
var os = function() {
	var ua = navigator.userAgent,
	isQB = /(?:MQQBrowser|QQ)/.test(ua),
	isWindowsPhone = /(?:Windows Phone)/.test(ua),
	isSymbian = /(?:SymbianOS)/.test(ua) || isWindowsPhone,
	isAndroid = /(?:Android)/.test(ua),
	isFireFox = /(?:Firefox)/.test(ua),
	isChrome = /(?:Chrome|CriOS)/.test(ua),
	isIpad = /(?:iPad|PlayBook)/.test(ua),
	isTablet = /(?:iPad|PlayBook)/.test(ua)||(isFireFox && /(?:Tablet)/.test(ua)),
	isSafari = /(?:Safari)/.test(ua),
	isPhone = /(?:iPhone)/.test(ua) && !isTablet,
	isOpera= /(?:Opera Mini)/.test(ua),
	isUC = /(?:UCWEB|UCBrowser)/.test(ua),
	isWeixin= /(?:MicroMessenger)/.test(ua),
	isBaiduWallet= /(?:BaiduWallet)/.test(ua),
	isPc = !isPhone && !isAndroid && !isSymbian;
	return {
		isQB: isQB,
		isTablet: isTablet,
		isPhone: isPhone,
		isAndroid : isAndroid,
		isPc : isPc,
		isOpera : isOpera,
		isUC: isUC,
		isIpad : isIpad,
		isWeixin : isWeixin,
		isBaiduWallet : isBaiduWallet
	};
}();
var falg = false;
function tosubmit(){
	var amount = $("#conAmount").val();
	if(amount == ''){
		return false;
	}
	var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
    if(!exp.test(amount)){
		$("#errorMsg").html("<lable style=\"color:red\">输入的金额不正确</lable>");
		return false;
    }
	if(falg) return;
	falg = true;
	$("#amount").val(amount);
	$("#calcForm").submit();

}
//平台、设备和操作系统
var system ={
win : false,
mac : false,
xll : false
};
//检测平台
var p = navigator.platform;
system.win = p.indexOf("Win") == 0;
system.mac = p.indexOf("Mac") == 0;
system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
//跳转语句
if(system.win||system.mac||system.xll){//转向后台登陆页面
window.location.href="<%=request.getContextPath()%>/trade/error.html";
}else{
}

/* AlipayJSBridge.call('openInBrowser', {
    url: 'http://m.baidu.com/'
}); */
/**
 * 点击广告
 */
function clickAdv(id,url){
	var clickurl  = '<%=request.getContextPath()%>/api/clickadv.html?id='+id;
	$.get(clickurl);
	window.location.href =url;
}

</script>
</html>