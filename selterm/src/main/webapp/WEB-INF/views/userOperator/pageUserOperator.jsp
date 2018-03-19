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
	 			<%-- <th>机构名称</th> --%>
				<th>用户登录账户</th>
				<th>用户名</th>
				<th>联系方式</th>
				<th>角色</th>
				<%-- <th>上级名称</th> --%>
				<th>状态</th>
				<th>操作</th>				
		 		</tr>
		 	</thead>
		 	
		 	<tbody>
		 	
				<c:forEach items="${page.results}" var="UserOperator">
		<tr>
                      <%-- <td height="22" align="center" valign="middle">
                      	${UserOperator.orgname }
                      </td> --%>
                      
                      <td height="22" align="center" valign="middle">
                      	${UserOperator.loginnmame}
                      </td>
                      
                      <td height="22" align="center" valign="middle">
                       ${UserOperator.name}
                      </td>
                      
                      <td height="22" align="center" valign="middle">
                      	${UserOperator.contact}
                      </td>
                       <td height="22" align="center" valign="middle">
                      	${UserOperator.rolename}
                      </td>
                       <%-- <td height="22" align="center" valign="middle">
                      	${UserOperator.parentname}
                      </td> --%>
                       <td height="22" align="center" valign="middle">
                       <c:if test="${UserOperator.status eq 1}">启用</c:if>
                       <c:if test="${UserOperator.status eq 2}"><font color="red">禁用</font></c:if>
                      </td>
                      
<!--                        <td height="22" align="center" valign="middle"> -->
<%--                       	${UserOperator.remark} --%>
<!--                       </td> -->
                      
                      <td height="22" align="center" valign="middle">
                      <t:buttonOut url="/userOperator/toEditUserOperator.html">
                      <input type="button" value="编辑" data-toggle="modal" data-target="#tjdp"  onclick="toEditUserOperator('${UserOperator.id}')"/> 
		              </t:buttonOut>
		              <t:buttonOut url="/userOperator/deleteUserOperator.html">
			              <input type="button" value="删除" onclick="deleteOperator('${UserOperator.id}')"/>
		              </t:buttonOut>
		              <t:buttonOut url="/userOperator/saveInherit.html">
		               <input type="button" value="继承" data-toggle="modal" data-target="#zhjc"  onclick="accountInherit('${UserOperator.id}')"/>
		               </t:buttonOut>
		               <t:buttonOut url="/userOperator/resetpass.html">
		               <input type="button" value="重置密码"  onclick="resetpass('${UserOperator.id}')"/>
		               </t:buttonOut>
		               
		               <c:if test="${UserOperator.status eq 1}"><t:buttonOut url="/userOperator/disableuser.html"> <input type="button" value="禁用"  onclick="disableuser('${UserOperator.id}')"/></t:buttonOut></c:if>
		               <c:if test="${UserOperator.status eq 2}"><t:buttonOut url="/userOperator/enableuser.html"> <input type="button" value="启用"  onclick="enableuser('${UserOperator.id}')"/></t:buttonOut></c:if>
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