<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style>

		.arcle-grap input[type=text] {
			max-width: 260px;
			width: 20%;
			min-width: 230px !important;
			height: 40px;
			margin-left: 6px !important;

			margin-right: 8px;
			border: 1px solid #999;
		}
		.arcle-grap span {
			display: inline-block;
			margin-bottom: 20px;

		}
	</style>
<script type="text/javascript">
	layer.config({
		path: '<%=request.getContextPath()%>/man/js/layer/',
		extend:'extend/layer.ext.js'
	});
			function f_delete(id){
         		layer.confirm('是否删除？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/rechargePlan/deleteRechargePlan.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/rechargePlan/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('删除失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}



			function disable(id){
         		layer.confirm('是否停用？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/rechargePlan/disable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/rechargePlan/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}

			function enable(id){
         		layer.confirm('是否启用？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/rechargePlan/enable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/rechargePlan/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('启用失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}
			//编辑商户
			function toEdit(id){
		    	  changeRightMenu('../rechargePlan/toEditRechargePlan.html?id='+id);
			  }



			//商户详情
			function details(id){
		    	  changeRightMenu('../rechargePlan/dealerDetails.html?id='+id);
			  }


	      $().ready(function(){
			  $("#exportBtn").click(function(){
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/rechargePlan/export.html";
				  document.getElementById("query_from").submit();
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/rechargePlan/page.html";
			  });
		  });








		</script>
</head>
<body>

	<section class="navigation">
	<span>您当前所在的位置：</span><a href="#" title="">充值套餐管理</a>/<i>充值套餐管理</i>
		<t:buttonOut url="/dealer/toAddDealer.html">
			<input class="grap-btn" type="button" style="line-height: 0px;float:right;"
				   onclick="changeRightMenu('../rechargePlan/toAddRechargePlan.html');"
				   value="添加充值套餐" />
		</t:buttonOut>
	</section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
			<div class="arcle-grap">



			</div>
			<%@ include file="/WEB-INF/views/pageSize.jsp"%>
		</form>
		<div id="page_data"></div>
		<!-- 加载初始数据 -->
		<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/rechargePlan/page.html';
			$("#page_data").load(url);
		</script>



	</div>
	</section>

</body>
</html>