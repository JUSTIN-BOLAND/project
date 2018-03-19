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
		 		<th>所属机构</th>
	 			<th>角色名称</th>
				<th>角色状态</th>
				<th>描述</th>
<!-- 				<th>备注</th> -->	
				<th>操作</th>				
		 		</tr>
		 	</thead>
		 	
		 	<tbody>
		 	
		 	
		 	
			<c:forEach items="${page.results}" var="role">
				<tr>
				      <td height="22" align="center" valign="middle">
                      	${role.orgName}
                      </td>
                      <td height="22" align="center" valign="middle">
                      	${role.rolename}
                      </td>
                      <td height="22" align="center" valign="middle">
                      
                      	<c:if test="${role.rolestatus == '1'}">
                      		启用
                      	</c:if>
                      	<c:if test="${role.rolestatus == '2'}">
                      		禁用
                      	</c:if>
                      </td>
                      
                      <td height="22" align="center" valign="middle">
                      	${role.roledesc}
                      </td>
                      
<!--                        <td height="22" align="center" valign="middle"> -->
<%--                       	${role.remark} --%>
<!--                       </td> -->
                      
                      <td height="22" align="center" valign="middle">
                        <t:buttonOut url="/role/toEditRole.html">
                        	<input type="button" value="编辑" data-toggle="modal" data-target="#tjdy"  onclick="toEditRole('${role.id}')"/> 
		            	</t:buttonOut>
		            	<t:buttonOut url="/role/delectRole.html">
		            		<input type="button" value="删除" onclick="deleteRole('${role.id}')"/>
		            	</t:buttonOut>
<%-- 		            	<t:buttonOut url="/role/toAuthorizedRole.html"> --%>
                        	<input type="button" value="授权 " data-toggle="modal" data-target="#tjtree"  onclick="toAuthorizedRole('${role.id}')"/>
<%-- 		            	</t:buttonOut> --%>
                      
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