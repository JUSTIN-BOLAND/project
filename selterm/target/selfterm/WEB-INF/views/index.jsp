<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	  <link href="<%=request.getContextPath()%>/man/images/bitbug_favicon1.ico" rel="shortcut icon"/>
	<%@ include file="/WEB-INF/views/include.jsp"%>
	<script type="text/javascript">
		function changeRightMenu(url){
			$("#bodyRight").empty();
			$("#bodyRight").load(url);
		}
	</script>
</head>
<body>
<script type="text/javascript">
 		$(function(){
 			 $(".aside-bar-bg").click(function(event) {
 	            $(".aside-bar-bg").removeClass('scors');
 	            $(this).addClass('scors');
 	         $(this).parent().siblings().find('dl').slideUp();
 	            var $aside_dl=$(this).parents("li").find("dl");
 	            if($aside_dl.is(":hidden")){
 	                   $aside_dl.slideDown();
 	               }else{
 	              $aside_dl.slideUp();
 	               }
 	          });
 			 
 			 $("li>dl>dd").click(function(){
 				$("li>dl>dd>a").removeClass('scors');
 				 $(this).find('a').addClass('scors');
 			 });
 	       $(".nav-bar").click(function(event) {
 	           $(".aside").toggleClass('aside-hide');
 	       });
 	       
 		var url ="<%=request.getContextPath()%>/system/systemindex.html";
 	    $("#bodyRight").load(url);
    	   		
 			var style=document.getElementById("cssStyle");
 			var style_href=$(style).attr("href").toString();
 			var lhref=location.href.toString().split("//")[1];
 			$(".skin").on("click","i",function(){
 				var $index=$(this).index();
 				var $href=null;
 				var reg=/style[0-9]/;
 		        reg=style_href.match(reg)?/style[0-9]/:/style/;
 			    if($index===1){
 			     $href=style_href.replace(reg,"style");
 			     style.href=$href;
 			    }else{
 			      $href=style_href.replace(reg,"style"+$index);
 			      style.href=$href;
 			    }
 		        
 		        document.cookie="stylesheet="+escape($href)+";path=/";//写入Cookie
 			 });
 	    	   
 			
             if(/stylesheet=([^;]+)/.test(document.cookie)){//判断是否存在cookie
             document.getElementById("cssStyle").href=unescape(RegExp.$1);}
 		});
	    
		</script>
 <div class="wrap">
    <%@ include file="/WEB-INF/views/header.jsp"%>
 	<!--主体 -->
 	<section class="content">
 		<%@ include file="/WEB-INF/views/menu.jsp"%>
	 	<div class="main" id="bodyRight">
			   
	 		</div>
 		</section>
 		<style>
 		.home-list a{cursor:pointer;}
 		.cred{color:red;}
 		</style>
 		<%@ include file="/WEB-INF/views/footer.jsp"%>
 		
 		
 </div>
</body>
</html>