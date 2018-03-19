<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript">
	 
	</script>
</head>
<body>
  <div class="table-responsive">
	<table class="table table-hover">
        <thead>
          <tr>
            <th>广告编号</th>
            <th>广告名称</th>
            <th>添加时间</th>
            <th>创建人</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>  
        <tbody>
			<c:forEach items="${page.results}" var="advertise">
	          	<tr>
	            <td >
	            ${advertise.code }
	            </td>
	            <td >
	            ${advertise.name }
	            </td>
	            <td >
	             <fmt:formatDate value="${advertise.creattime }" pattern="yyyy-MM-dd HH:mm:ss" />
	            </td>
	             <td >
	              ${advertise.creator }
	            </td>
	            <td >
	            <c:if test="${advertise.status == '1'}">
               		已发布
               	</c:if>
               	<c:if test="${advertise.status == '2'}">
               		已撤销
               	</c:if>
	            </td>
	            <td >
<%-- 	             <fmt:formatDate value="${org.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />  --%>
	            <t:buttonOut url="/advertise/toEditAdvertise.html">
	            <input type="button" value="编辑" data-toggle="modal" data-target="#tjdy" onclick="editAdvertise('${advertise.id}')"/>
				</t:buttonOut>
				
				<t:buttonOut url="/advertise/revoke.html">
				<input type="button" value="撤销" onclick="revokeAdvertise('${advertise.id}')"/>
				</t:buttonOut>
				
					  
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