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
  	            		url: '<%=request.getContextPath()%>/device/delete.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/device/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('删除失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}



			function f_disable(id){
         		layer.confirm('是否停用？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/device/disable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/device/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}

			function f_enable(id){
         		layer.confirm('是否启用？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/device/enable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/device/page.html');
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
			function f_toEdit(id){
		    	  changeRightMenu('../device/toEdit.html?id='+id);
			  }



			//商户详情
			function f_details(id){
		    	  changeRightMenu('../device/deale.html?id='+id);
			  }

			function f_checkAppIdExist(mechantId){
				v_result = true;
				$.ajax({
					url: '<%=request.getContextPath()%>/dealer/checkAppIdExist.html?mechantId='+mechantId,
					context: document.body,
					async: false,
					dataType:'json',
					success: function(data){
						if(data.success){
							//layer.msg('验证成功！',{icon:1});
							//layer.closeAll();

						}else{
							layer.msg('请在设置中配置 该商户的支付宝AppId！',{icon:5});
							v_result= false;
						}
					}
				});
				return v_result;
			}
	      $().ready(function(){
			  $("#exportBtn").click(function(){
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/device/export.html";
				  document.getElementById("query_from").submit();
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/device/page.html";
			  });
		  });



		function passwordReset(id){
			layer.confirm('确定重置密码？', {
	    		  btn: ['确定','取消'] //按钮
	    		},
	    		function(){
	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/device/passwordReset.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				layer.msg('重置成功',{icon:1});
  	            				changeRightMenu('<%=request.getContextPath()%>/device/list.html');
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
	<span>您当前所在的位置：</span><a href="#" title="">设备管理</a>/<i>设备列表</i>
		<t:buttonOut url="/device/toAdd.html">
			<input class="grap-btn" type="button" style="line-height: 0px;float:right;"
				   onclick="changeRightMenu('../device/toAdd.html');"
				   value="添加设备" />
		</t:buttonOut>
	</section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
			<div class="arcle-grap">
				<div>

				<span style="width:100px;">设备名称</span><input class="grap-txt" type="text" name="deviceName" placeholder="设备名称" />
				<span style="width:100px;">经销商名称</span><input class="grap-txt" type="text" name="memo" placeholder="经销商名称" />
				<span style="width:69px;">设备分类</span>
				<select class="grap-sel" name="deviceType" style="margin-left: 0px;">
						<option value="">全部</option>
						<c:forEach items="${deviceTypes }" var="item" varStatus="idx">


									<option value="${item.code }">${item.name }</option>

						</c:forEach>
					</select>
					<input class="grap-btn" type="button"  style="float:right"
						id="exportBtn"
						value="导出" />

		</div>
		<div>

			<span style="width:100px;">设备序列号</span><input class="grap-txt" type="text" name="flag" placeholder="设备序列号" />
			<span style="width:100px;">机器编号</span><input class="grap-txt" type="text" name="deviceCode" placeholder="机器编号" />
			<span style="width:69px;">机器状态</span>
			<select class="grap-sel" name="status" style="margin-left: 0px;">
				<option value="">全部</option>
				<option value="0">在线空闲</option>
				<option value="1">异常离线</option>
				<option value="2">正在使用</option>
			</select>
			<input class="grap-btn" type="button" style="float:right"
									   onclick="query('<%=request.getContextPath()%>/device/page.html');"
									   value="查询" />
		</div>
			<%@ include file="/WEB-INF/views/pageSize.jsp"%>
		</form>
		<div id="page_data"></div>
		<!-- 加载初始数据 -->
		<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/device/page.html';
			$("#page_data").load(url);
		</script>



	</div>
	</section>

</body>
</html>