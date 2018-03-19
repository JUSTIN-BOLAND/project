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
	border-radius:50px;
}
.div-img{
	border-radius:0;
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
	    background: #68b0f9;
	     color:#fff;
	border-radius:5px;
	margin:30px 0 0;
}
input[type=button].div4-3-1:focus {

	    background: #ccc;
	     color:#fff;

}
.pd32{
   padding:0 32px;
}
.div-4-btn{background:#68b0f9;}
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


/*弹窗*/
.wtgs{ width:100%;height:100%; background:rgba(0,0,0,.8); position:fixed;top:0;left:0; display:none;
 color:#fff; text-align:center;

}
.wtgs img{margin-top:50%;}
.wtgs p{margin-top:20px;}
.weui_mask_transparent {
    position: fixed;
    z-index: 1;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
}
.weui_toast {
    position: fixed;
    z-index: 3;
    width: 7.6em;
    min-height: 7.6em;
    top: 180px;
    left: 50%;
    margin-left: -3.8em;
    background: rgba(40, 40, 40, 0.75);
    text-align: center;
    border-radius: 5px;
    color: #FFFFFF;
}
.weui_loading {
    position: absolute;
    width: 0px;
    z-index: 2000000000;
    left: 50%;
    top: 38%;
}
.weui_loading_leaf_0 {
    -webkit-animation: opacity-60-25-0-12 1.25s linear infinite;
    animation: opacity-60-25-0-12 1.25s linear infinite;
}
.weui_loading_leaf {
    position: absolute;
    top: -1px;
    opacity: 0.25;
}
.weui_loading_leaf:before{
    content:" ";
        position: absolute;
        width: 8.14px;
        height: 3.08px;
        background: rgb(209, 209, 213);
        box-shadow: rgba(0, 0, 0, 0.0980392) 0px 0px 1px;
        border-radius: 1px;
        transform-origin: left 50% 0px;
}
.weui_loading_toast .weui_toast_content {
    margin-top: 64%;
    font-size: 14px;
}
.weui_loading_leaf_0 {
  -webkit-animation: opacity-60-25-0-12 1.25s linear infinite;
          animation: opacity-60-25-0-12 1.25s linear infinite;
}
.weui_loading_leaf_0:before {
  -webkit-transform: rotate(0deg) translate(7.92px, 0px);
          transform: rotate(0deg) translate(7.92px, 0px);
}
.weui_loading_leaf_1 {
  -webkit-animation: opacity-60-25-1-12 1.25s linear infinite;
          animation: opacity-60-25-1-12 1.25s linear infinite;
}
.weui_loading_leaf_1:before {
  -webkit-transform: rotate(30deg) translate(7.92px, 0px);
          transform: rotate(30deg) translate(7.92px, 0px);
}
.weui_loading_leaf_2 {
  -webkit-animation: opacity-60-25-2-12 1.25s linear infinite;
          animation: opacity-60-25-2-12 1.25s linear infinite;
}
.weui_loading_leaf_2:before {
  -webkit-transform: rotate(60deg) translate(7.92px, 0px);
          transform: rotate(60deg) translate(7.92px, 0px);
}
.weui_loading_leaf_3 {
  -webkit-animation: opacity-60-25-3-12 1.25s linear infinite;
          animation: opacity-60-25-3-12 1.25s linear infinite;
}
.weui_loading_leaf_3:before {
  -webkit-transform: rotate(90deg) translate(7.92px, 0px);
          transform: rotate(90deg) translate(7.92px, 0px);
}
.weui_loading_leaf_4 {
  -webkit-animation: opacity-60-25-4-12 1.25s linear infinite;
          animation: opacity-60-25-4-12 1.25s linear infinite;
}
.weui_loading_leaf_4:before {
  -webkit-transform: rotate(120deg) translate(7.92px, 0px);
          transform: rotate(120deg) translate(7.92px, 0px);
}
.weui_loading_leaf_5 {
  -webkit-animation: opacity-60-25-5-12 1.25s linear infinite;
          animation: opacity-60-25-5-12 1.25s linear infinite;
}
.weui_loading_leaf_5:before {
  -webkit-transform: rotate(150deg) translate(7.92px, 0px);
          transform: rotate(150deg) translate(7.92px, 0px);
}
.weui_loading_leaf_6 {
  -webkit-animation: opacity-60-25-6-12 1.25s linear infinite;
          animation: opacity-60-25-6-12 1.25s linear infinite;
}
.weui_loading_leaf_6:before {
  -webkit-transform: rotate(180deg) translate(7.92px, 0px);
          transform: rotate(180deg) translate(7.92px, 0px);
}
.weui_loading_leaf_7 {
  -webkit-animation: opacity-60-25-7-12 1.25s linear infinite;
          animation: opacity-60-25-7-12 1.25s linear infinite;
}
.weui_loading_leaf_7:before {
  -webkit-transform: rotate(210deg) translate(7.92px, 0px);
          transform: rotate(210deg) translate(7.92px, 0px);
}
.weui_loading_leaf_8 {
  -webkit-animation: opacity-60-25-8-12 1.25s linear infinite;
          animation: opacity-60-25-8-12 1.25s linear infinite;
}
.weui_loading_leaf_8:before {
  -webkit-transform: rotate(240deg) translate(7.92px, 0px);
          transform: rotate(240deg) translate(7.92px, 0px);
}
.weui_loading_leaf_9 {
  -webkit-animation: opacity-60-25-9-12 1.25s linear infinite;
          animation: opacity-60-25-9-12 1.25s linear infinite;
}
.weui_loading_leaf_9:before {
  -webkit-transform: rotate(270deg) translate(7.92px, 0px);
          transform: rotate(270deg) translate(7.92px, 0px);
}
.weui_loading_leaf_10 {
  -webkit-animation: opacity-60-25-10-12 1.25s linear infinite;
          animation: opacity-60-25-10-12 1.25s linear infinite;
}
.weui_loading_leaf_10:before {
  -webkit-transform: rotate(300deg) translate(7.92px, 0px);
          transform: rotate(300deg) translate(7.92px, 0px);
}
.weui_loading_leaf_11 {
  -webkit-animation: opacity-60-25-11-12 1.25s linear infinite;
          animation: opacity-60-25-11-12 1.25s linear infinite;
}
.weui_loading_leaf_11:before {
  -webkit-transform: rotate(330deg) translate(7.92px, 0px);
          transform: rotate(330deg) translate(7.92px, 0px);
}
@-webkit-keyframes opacity-60-25-0-12 {
  0% { opacity: 0.25; }
  0.01% { opacity: 0.25; }
  0.02% { opacity: 1; }
  60.01% { opacity: 0.25; }
  100% { opacity: 0.25; }
}@-webkit-keyframes opacity-60-25-1-12 {
  0% { opacity: 0.25; }
  8.34333% { opacity: 0.25; }
  8.35333% { opacity: 1; }
  68.3433% { opacity: 0.25; }
  100% { opacity: 0.25; }
}@-webkit-keyframes opacity-60-25-2-12 {
  0% { opacity: 0.25; }
  16.6767% { opacity: 0.25; }
  16.6867% { opacity: 1; }
  76.6767% { opacity: 0.25; }
  100% { opacity: 0.25; }
}@-webkit-keyframes opacity-60-25-3-12 {
  0% { opacity: 0.25; }
  25.01% { opacity: 0.25; }
  25.02% { opacity: 1; }
  85.01% { opacity: 0.25; }
  100% { opacity: 0.25; }
}@-webkit-keyframes opacity-60-25-4-12 {
  0% { opacity: 0.25; }
  33.3433% { opacity: 0.25; }
  33.3533% { opacity: 1; }
  93.3433% { opacity: 0.25; }
  100% { opacity: 0.25; }
}@-webkit-keyframes opacity-60-25-5-12 {
  0% { opacity: 0.270958333333333; }
  41.6767% { opacity: 0.25; }
  41.6867% { opacity: 1; }
  1.67667% { opacity: 0.25; }
  100% { opacity: 0.270958333333333; }
}@-webkit-keyframes opacity-60-25-6-12 {
  0% { opacity: 0.375125; }
  50.01% { opacity: 0.25; }
  50.02% { opacity: 1; }
  10.01% { opacity: 0.25; }
  100% { opacity: 0.375125; }
}@-webkit-keyframes opacity-60-25-7-12 {
  0% { opacity: 0.479291666666667; }
  58.3433% { opacity: 0.25; }
  58.3533% { opacity: 1; }
  18.3433% { opacity: 0.25; }
  100% { opacity: 0.479291666666667; }
}@-webkit-keyframes opacity-60-25-8-12 {
  0% { opacity: 0.583458333333333; }
  66.6767% { opacity: 0.25; }
  66.6867% { opacity: 1; }
  26.6767% { opacity: 0.25; }
  100% { opacity: 0.583458333333333; }
}@-webkit-keyframes opacity-60-25-9-12 {
  0% { opacity: 0.687625; }
  75.01% { opacity: 0.25; }
  75.02% { opacity: 1; }
  35.01% { opacity: 0.25; }
  100% { opacity: 0.687625; }
}@-webkit-keyframes opacity-60-25-10-12 {
  0% { opacity: 0.791791666666667; }
  83.3433% { opacity: 0.25; }
  83.3533% { opacity: 1; }
  43.3433% { opacity: 0.25; }
  100% { opacity: 0.791791666666667; }
}@-webkit-keyframes opacity-60-25-11-12 {
  0% { opacity: 0.895958333333333; }
  91.6767% { opacity: 0.25; }
  91.6867% { opacity: 1; }
  51.6767% { opacity: 0.25; }
  100% { opacity: 0.895958333333333; }
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
<div align="center" class="div2">
<div>
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
      <input type="button" class="div4-3-1" onclick="tosubmit()" value="确认付款"/>
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

<!-- loding -->
	<div id="loadingToast" class="weui_loading_toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content">loading</p>
    </div>
</div>

</body>
<%--  <form action="<%=request.getContextPath()%>/mobile/pay.html" id="calcForm" method="post"> --%>
<%--         <input type="hidden" value="${storeId}" name="sid" id="sid"/> --%>
<%--         <input type="hidden" value="${type}" name="type" id="type"/> --%>
<!--         <input type="hidden" value="" id="amount" name="amount"/> -->
<%--         <input type="hidden" value="${code}" id="code" name="code"/> --%>
<!--    </form> -->
<!-- <div id = 'qrcodediv'> -->
<!-- 	<img class="div3-img" src="" id="qrcodes" width="140" height="140">  -->
<!-- 	长按二维码可扫码 -->
<!-- </div> -->
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
// 	$("#amount").val(amount);
// 	$("#calcForm").submit();
	getQrcodeAli();
	
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
window.location.href="<%=request.getContextPath()%>/error/error.html";
}else{
}

/* AlipayJSBridge.call('openInBrowser', {
    url: 'http://m.baidu.com/' 
}); */

function getQrcodeAli(){
	
	$("#loadingToast").show();
	var storeId = '${storeId}';
	var amoun = $("#conAmount").val();
	setTimeout(function(){
		$.ajax({ 
			url: '<%=request.getContextPath()%>/mobile/qrcodeali.html?storeId='+storeId+"&amoun="+amoun, 
			context: document.body, 
			async: false,
			success: function(data){
				
				$("#loadingToast").hide();
				if(data.success){
//		 			alert(data.data.path);
					window.location.href=data.data.qrcode;
//		 			$("#qrcodes").attr("src","/"+data.data.path);
//		 			$("#qrcodediv").show();
					
				}else{
					alert("请求支付宝错误，请重新扫码.");
				}
			},
			error:function(derr){
				$("#loadingToast").hide();
			}
		});
		
	},2000)

}

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