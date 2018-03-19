<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
    
	 </script>


</head>
<body>

	<section class="navigation"> <span>您当前所在的位置：</span> <a
		href="#" title="">系统管理</a>/<i>账号继承</i> </section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
			<div class="arcle-grap">

				<input class="grap-txt" type="text" name="rawname"	placeholder="原账号姓名"/> 
				<input class="grap-txt" type="text" name="inheritaccount"	placeholder="继承账号"/> 
				<input class="grap-btn" type="button"
					onclick="query('<%=request.getContextPath()%>/inheritRecordSheet/page.html');"
					value="查询"/>
				 
			</div>
			<%@ include file="/WEB-INF/views/pageSize.jsp"%>
		</form>
		<div id="page_data"></div>

		<!-- 加载初始数据 -->
		<script type="text/javascript">
 					 var url = '<%=request.getContextPath()%>/inheritRecordSheet/page.html';
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
						<h4 class="modal-title" id="myModalLabel">  </h4>
					</div>
					<div class="model-box" id="modal_show"></div>
				</div>
			</div>
		</div>

	</div>
	</section>


</body>
</html>