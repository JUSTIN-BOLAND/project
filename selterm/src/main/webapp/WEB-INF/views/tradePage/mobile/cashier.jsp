<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title></title>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<!--离线应用的另一个技巧-->
<meta content="yes" name="apple-mobile-web-app-capable" />
<!--指定的iphone中safari顶端的状态条的样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!--告诉设备忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection" />
	<meta name="format-detection" content="email=no"/>
  <script type="text/javascript"  src="<%=request.getContextPath()%>/man/js/min/jquery.js"></script>
	</head>
	<body>
	<%-- <div id="log" >tradeNo = ${tradeNo}</div> --%>
	</body>
<script src="https://as.alipayobjects.com/g/component/antbridge/1.1.1/antbridge.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		function log(obj) {
			$("#log").append(obj).append(" ").append("<br />");
		}
		<%
		  String payType = (String)request.getAttribute("payType");

		  System.out.println("jsp : "+payType+" -> "+request.getAttribute("tradeNo"));
		  if("1".equals(payType)){
		%>
				$(document).ready(function(){
			// 页面载入完成后即唤起收银台
			// 此处${tradeNO}为模板语言语法，实际调用样例类似为tradePay("2016072621001004200000000752")
			tradePay("${tradeNo}");


		});

		// 由于js的载入是异步的，所以可以通过该方法，当AlipayJSBridgeReady事件发生后，再执行callback方法
		function ready(callback) {
			if (window.AlipayJSBridge) {
				callback && callback();
			} else {
				document.addEventListener('AlipayJSBridgeReady', callback, false);
			}
		}

		function tradePay(tradeNO) {
			ready(function(){
				// 通过传入交易号唤起快捷调用方式(注意tradeNO大小写严格)
				AlipayJSBridge.call("tradePay", {
					tradeNO: tradeNO
				}, function (data) {
					//log(JSON.stringify(data));
					if ("9000" == data.resultCode) {
						//log("支付成功");
					}
					else{
                       // log("支付失败:tradeNo=${tradeNo}  \n"+ JSON.stringify(data));
                    }
				});
			});
		}
    <%} else if("2".equals(payType)){
      String wxJs = (String)request.getAttribute("wxJs");
      System.out.println("jsp: "+wxJs);
      out.write(wxJs);

    }%>

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
	   //window.location.href="<%=request.getContextPath()%>/trade/error.html";
	}else{
	}
	var userAgent = navigator.userAgent;




</script>
</html>