<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>

<script type="text/javascript">



function editBulletin(id){
	  var url= '<%=request.getContextPath()%>/bulletin/toEditBulletin.html?id='+id;
		//加载模态框数据
	  $("#modal_show").load(url);
	
}

function revokeBulletin(id){
		layer.confirm('是否撤销？', {
		  btn: ['确定','取消'] //按钮
		}, 
		function(){
			$.ajax({ 
      		url: '<%=request.getContextPath()%>/bulletin/revoke.html?id='+id, 
      		context: document.body, 
      		async: false,
      		success: function(data){
      			if(data.success){
      				query('<%=request.getContextPath()%>/bulletin/page.html');
      				layer.closeAll();
      			}else{
      				layer.msg('失败！',{icon:5});
      			}
      		}
      	});
		}
	);
}


function addBulletin(){
	  var url= '<%=request.getContextPath()%>/bulletin/toAddBuller.html';
		//加载模态框数据
		$("#myModalLabel").html("添加公告");
	  $("#modal_show").load(url);
	
}
</script>


</head>
<body>
		    <section class="navigation">
		        <span>您当前所在的位置：</span><a  href="#" title="">广告管理</a>/<i>公告发布</i>
		    </section>
	        <section class="main-cont">
	 			<div class="article">
		 			<form id="query_from">
			 			 <div class="arcle-grap">
			 			 	<input class="grap-txt" type="text" name="title" placeholder="公告标题"/>
			 			 	<select class="grap-sel" name="status">
			 			 	<option value="">全部</option>
			 			 	<option value="1">已发布</option>
			 			 	<option value="2">已撤销</option>
			 			 	</select>
					        <input class="grap-btn" type="button" onclick="query('<%=request.getContextPath()%>/bulletin/page.html');"  value="查询"/>
					        
					        <t:buttonOut url="/bulletin/toAddBuller">
						        <input class="grap-btn grap-btn-fr" type="button" data-toggle="modal" data-target="#tjdy" value="添加公告" onclick="addBulletin()"/>
						    </t:buttonOut>
			       		</div>
						<%@ include file="/WEB-INF/views/pageSize.jsp"%>				 	
					</form>
					<div id="page_data">
			       
					</div>	 
					<!-- 加载初始数据 -->
					<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/bulletin/page.html';
		    			 $("#page_data").load(url);
					</script>
					
					 <!-- 模态框（Modal） start -->
		<div class="modal fade" id="tjdy" tabindex="-1" role="dialog"
			aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">编辑公告</h4>
					</div>
					<div class="model-box" id="modal_show"></div>

				</div>
			</div>
		</div>
		<!-- 模态框（Modal） end -->
					
		 		</div>
			 </section>
 		
</body>
</html>