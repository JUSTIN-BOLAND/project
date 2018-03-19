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
			width:50px;
			font-size:20px;font-weight:bold;
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
  	            		url: '<%=request.getContextPath()%>/card/delete.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/card/page.html');
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
  	            		url: '<%=request.getContextPath()%>/card/disable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/card/page.html');
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
  	            		url: '<%=request.getContextPath()%>/card/enable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/card/page.html');
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
		    	  changeRightMenu('../card/toEdit.html?id='+id);
			  }



			//商户详情
			function f_details(id){
		    	  changeRightMenu('../card/deale.html?id='+id);
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
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/card/export.html";
				  document.getElementById("query_from").submit();
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/card/page.html";
			  });
		  });



		function passwordReset(id){
			layer.confirm('确定重置密码？', {
	    		  btn: ['确定','取消'] //按钮
	    		},
	    		function(){
	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/card/passwordReset.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				layer.msg('重置成功',{icon:1});
  	            				changeRightMenu('<%=request.getContextPath()%>/card/list.html');
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
	<span>您当前所在的位置：</span><a href="#" title="">一卡通管理</a>/<i>一卡通列表</i>
		<t:buttonOut url="/card/toAdd.html">
			<input class="grap-btn" type="button" style="line-height: 0px;margin-left:80px;"
				   onclick="changeRightMenu('../card/toAdd.html');"
				   value="发卡" />
		</t:buttonOut>

	</section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
			<div class="arcle-grap">
				<div>

				<span style="">卡号</span><input class="grap-txt" type="text" name="cardNo" placeholder="卡号" />
					<t:buttonOut url="/card/toAdd.html">
						<input class="grap-btn" type="button" style="line-height: 0px;"
							   onclick="changeRightMenu('../card/readCard.html');"
							   value="读卡" />
					</t:buttonOut>
				<span style="">姓名</span><input class="grap-txt" type="text" name="name" placeholder="姓名" />
				<span style="">状态</span>
				<select class="grap-sel" name="status" style="margin-left: 0px;">
					<option value="">全部</option>
					<option value="0">在用</option>
					<option value="1">禁用</option>
									</select>
				<input class="grap-btn" type="button" style=""
										   onclick="query('<%=request.getContextPath()%>/card/page.html');"
										   value="查询" />

									<input class="grap-btn" type="button"  style="float:right"
										id="exportBtn"
										value="导出" />

				</div>
		</div>
			<%@ include file="/WEB-INF/views/pageSize.jsp"%>
		</form>
		<div id="page_data"></div>
		<!-- 加载初始数据 -->
		<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/card/page.html';
			$("#page_data").load(url);
		</script>



	</div>
	</section>

</body>
</html>