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




			//商户详情
			function f_detail(id){
		    	  changeRightMenu('../recharge/detail.html?id='+id);
			  }


	      $().ready(function(){
			  $("#exportBtn").click(function(){
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/recharge/export.html";
				  document.getElementById("query_from").submit();
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/recharge/page.html";
			  });
		  });



		function passwordReset(id){
			layer.confirm('确定重置密码？', {
	    		  btn: ['确定','取消'] //按钮
	    		},
	    		function(){
	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/recharge/passwordReset.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				layer.msg('重置成功',{icon:1});
  	            				changeRightMenu('<%=request.getContextPath()%>/recharge/list.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('',{icon:5});
  	            			}
  	            		}
  	            	});
	    		});
		}




		</script>
</head>
<body>

	<section class="navigation">
	<span>您当前所在的位置：</span><a href="#" title="">充值明细管理</a>/<i>充值明细列表</i>

	</section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
			<div class="arcle-grap">
<div>

<span style="width:70px;">商户订单号</span><input class="grap-txt" type="text" name="orderNo" placeholder="商户订单号" />
<span style="margin-left:15px;">经销商名称</span>
	<input class="grap-txt" type="text" name="authCode" id="authCode" style="margin-left: 0px;min-width:180px  !important;width:190px;" placeholder="经销商名称" />
<span style="margin-left:15px;">机器名称</span><input class="grap-txt" type="text" name="subMerchantId" id="subMerchantId" style="min-width:180px  !important;width:180px;" placeholder="机器名称" />
					<input class="grap-btn" type="button"  style="float:right"
						id="exportBtn"
						value="导出" />

</div>
<div>

<span style="width:70px;">机器编号</span><input class="grap-txt" type="text" name="memo" placeholder="机器编号" />
<span style="width:80px;margin-left:15px;">支付方式</span>
<select class="grap-sel" name="payType" style="margin-left: 0px;max-width:260px;width:190px;margin-right:0px;">

	<option value="">全部</option>
	<c:forEach items="${payTypes }" var="item" varStatus="idx">

		<option value="${item.code }">${item.name }</option>

	</c:forEach>
</select>
<span style="width:69px;margin-left:15px;">支付状态</span>
<select class="grap-sel" name="payStatus" style="margin-left: 0px;max-width:260px;width:180px;">
						<option value="">全部</option>
	<c:forEach items="${payStatuses }" var="item" varStatus="idx">

		<option value="${item.code }">${item.name }</option>

	</c:forEach>
					</select>
<input class="grap-btn" type="button" style="float:right"
						   onclick="query('<%=request.getContextPath()%>/recharge/page.html');"
						   value="查询" />		</div>
			<%@ include file="/WEB-INF/views/pageSize.jsp"%>
		</form>
		<div id="page_data"></div>
		<!-- 加载初始数据 -->
		<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/recharge/page.html';
			$("#page_data").load(url);
		</script>



	</div>
	</section>

</body>
</html>