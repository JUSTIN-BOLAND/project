<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	</head>
	<body>
	<label id="messa">${message}</label>
	</body>
	
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
	var userAgent = navigator.userAgent; 
	var wx_double = '${wx_double}';
	var ali_double = '${ali_double}';
	if(os.isWeixin){
		if(wx_double == '1'){
			$("#messa").val("微信通道尚未开通");
		}else{
			window.location.href='${authPathWx}';
		}
	}else{
		if(ali_double == '1'){
			$("#messa").val("支付宝通道尚未开通");
		}else{
			window.location.href='${authPathAli}';
		}
	}
	/* AlipayJSBridge.call('openInBrowser', {
	     url: 'http://m.baidu.com/' 
	}); */
	
</script>
</html>