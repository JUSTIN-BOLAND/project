<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	
</head>
<body>
 <div class="table-responsive">
	<table class="table table-hover">
		 	<thead>
		 		<tr>
		 		<th>原账号姓名</th>
	 			<th>原账号</th>
				<th>继承账号</th>
				<th>继承账号姓名</th>
				<th>继承时间</th>				
		 		</tr>
		 	</thead>
		 	<tbody>
			<c:forEach items="${page.results}" var="inherit">
				<tr>
				      <td height="22" align="center" valign="middle">
                      	${inherit.rawname}
                      </td>
                      <td height="22" align="center" valign="middle">
                      	${inherit.rawaccount}
                      </td>
                      <td height="22" align="center" valign="middle">
                       ${inherit.inheritaccount}
                      </td>
                      <td height="22" align="center" valign="middle">
                      	${inherit.inheritname}
                      </td>
                      <td height="22" align="center" valign="middle">
<%--                       ${inherit.inheritTime} --%>
                      <fmt:formatDate value="${inherit.inheritTime}" type="both"  />
                      </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="main-pading clear">
	   <%@ include file="/WEB-INF/views/page.jsp"%>
	</div>
	</body>
	</html>