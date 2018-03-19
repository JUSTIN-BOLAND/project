<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="arcle-box">
 	<div class="arcle-box-pading">
 		<span>每页显示</span>
 		<select  class="arcle-box-sel" name="pageSize" id="page_select">
	 		<option <c:if test="${page.pageSize==10 }">selected="selected"</c:if> value="10">10</option>
	 		<option <c:if test="${page.pageSize==20 }">selected="selected"</c:if> value="20">20</option>
	 		<option <c:if test="${page.pageSize==50 }">selected="selected"</c:if> value="50">50</option>
	 		<option <c:if test="${page.pageSize==100 }">selected="selected"</c:if> value="100">100</option>
 		</select>
 		<span>条记录</span>
 	</div>
</div>