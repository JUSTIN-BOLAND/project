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


</script>


</head>
<body>
		    <section class="navigation">
		        <span>您当前所在的位置：</span><a  href="#" title="">系统管理</a>/<i>操作日志</i>
		    </section>
	        <section class="main-cont">
	 			<div class="article">
		 			<form id="query_from">
			 			 <div class="arcle-grap">
			 			 	<input class="grap-txt" type="text" name="userid" placeholder="操作人"/>
			 			 	<input class="grap-txt" type="text" name="content" placeholder="操作内容"/>
					        <input class="grap-btn" type="button" onclick="query('<%=request.getContextPath()%>/log/page.html');"  value="查询"/>
					        
			       		</div>
						<%@ include file="/WEB-INF/views/pageSize.jsp"%>				 	
					</form>
					<div id="page_data">
			       
					</div>	 
					<!-- 加载初始数据 -->
					<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/log/page.html';
		    			 $("#page_data").load(url);
					</script>
					
		 		</div>
			 </section>
 		
</body>
</html>