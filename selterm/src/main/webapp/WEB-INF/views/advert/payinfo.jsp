<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>支付成功</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0" name="viewport" />
        <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <meta name="format-detection" content="telephone=no"/>
       <script type="text/javascript"  src="<%=request.getContextPath()%>/man/js/min/jquery.js"></script>
</head>
<script>
	 ;(function(win) {

     var cDoc=document; 
     var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
     var  cWid=null;
     function loadShow(){
       cWid=cDoc.documentElement.clientWidth || cDoc.body.clientWidth; //获取页面宽度
      var nHtml=cDoc.getElementsByTagName("html")[0];
      console.log(cWid)
      if(cWid<=720){
           nHtml.style.fontSize=100*(cWid/720)+"px";
         }
      }
      loadShow();
    cDoc.addEventListener("DOMContentLoaded",loadShow,false);
     win.addEventListener(resizeEvt,loadShow,false);
 })(window);

</script>
<style>
	h3,p,div{margin:0;padding: 0;}
	.header,.foot{ height:1rem;  background: #00a5a5; color: #fff; font-size:.36rem; line-height:1rem;}
	.foot{ background: #ccc;margin:10px 0;border-radius:5px;}
	.foot a{color:#fff;text-decoration: none;}
	.tc{  text-align: center; }
	.banner{width: 100%; height:3rem;}
	.banner img{ width: 100%; height: 100%; }
	.aMain{margin-top: .2rem;padding: 0 .58rem;}
	.aMain-top span{ font-size: .46rem; line-height: .4rem; color: #00a5a5; vertical-align: middle;margin-left: .2rem;}
	.icon-success{ width: .6rem; height: .2rem; background: url(success.png) no-repeat; background-size:.6rem .6rem; display: inline-block;vertical-align: middle; }
	.aMain-list{font-size: .3rem;margin-top: .3rem;}
	.aMain-list p{margin-bottom: .4rem;}
	.mClose{ width: 100%; height: .87rem;border-radius: 5px; background: #00a5a5; font-size: .35rem; color: #fff; line-height: .87rem; display: block;border:none;}

</style>
<body>
    <header class="header">
    	<h3 class="tc">支付结果</h3>
    </header>
    <section class="aMain">
    	<!-- <div class="aMain-top tc">
    		<i class="icon-success" ></i>
    	</div> -->
    	<div class="aMain-list"  style="margin-top: 1.2rem">
    		<p>商户名称：${order.merName }</p>
    		<p>订单编号：${order.orderNo }</p>
    		<p>订单金额：${order.orderMoney }元</p>
    		<p>支付时间：<fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
    		<%-- <p>订单状态：<c:if test="${order.orderStatus eq 1 }">正常</c:if><c:if test="${order.orderStatus eq 2 }">撤销</c:if><c:if test="${order.orderStatus eq 3 }">已退款</c:if></p> --%>
    		<p>当前状态：<c:if test="${order.payStatus eq 1 }">未支付</c:if><c:if test="${order.payStatus eq 2 }">支付成功</c:if><c:if test="${order.payStatus eq 3 }">支付失败</c:if></p>
    		<p>支付渠道：<c:if test="${order.payType eq 1 }">支付宝</c:if><c:if test="${order.payType eq 2 }">微信</c:if></p>
    	</div>
    </section>
    <div class="foot tc">
        <a  href="">桂林银行支付，不止支付</a>
    </div>
     <div class="banner">
    	<img src="${advert.advimage }" alt=""  onclick="clickAdv('${advert.id }','${advert.advlink }')">
     </div>
</body>

<script type="text/javascript">
function clickAdv(id,url){
	var clickurl  = '<%=request.getContextPath()%>/api/clickadv.html?id='+id;
	$.get(clickurl);
	window.location.href =url;
}
</script>
</html>