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
            <th>渠道商编码</th>
            <th>渠道商名称</th>
            <th>联系人</th>
            <th>联系电话</th>
            <%-- <th>登录用户名</th> --%>
            <th>创建人</th>
            <th>创建时间</th>
<%--             <th>详细地址</th> --%>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>  
        <tbody>
			<c:forEach items="${page.results}" var="org">
	          	<tr>
	            <td >
	            ${org.code }
	            </td>
	            <td >
	            ${org.name }</td>
	            <td >
	            ${org.contactPerson }</td>
	            <td >
	            ${org.contactTel }</td>
	            <%-- <td >
	            ${org.orgAccount }</td> --%>
	            <td >
	            ${org.creatorName }</td>
	            <td >
	             <fmt:formatDate value="${org.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /> 
	            </td>
<%-- 	            <td > --%>
<%-- 	            ${org.address }</td> --%>
	            <td >
               	<c:if test="${org.status eq '1'}">
               		启用
               	</c:if>
               	<c:if test="${org.status eq '2'}">
               	<font color="red"> 
               		禁用
                </font>
               	</c:if>
               </td>
	            <td >
	            	
	            	
	            	<c:if test="${org.level ne '1' }">
		            	
		            	<t:buttonOut url="/org/toEditOrg.html">
		 	            	<input type="button" value="编辑" data-toggle="modal" data-target="#tjdp"  onclick="toEditOrg('${org.id}')"/> 
						</t:buttonOut>
						<t:buttonOut url="/org/orgDelete.html">
		            		<input type="button" value="删除" onclick="deleteOrg('${org.id}')"/>	
		            	</t:buttonOut>
		            	
		            	<t:buttonOut url="/org/auditOrg.html">	            			            	
							<input type="button" value="审核" onclick=""/>
						</t:buttonOut>
						
						<t:buttonOut url="/org/disableOrg.html">
							<c:if test="${org.status eq '1'}">
							     <input type="button" value="停用" onclick="disableOrg('${org.id}')"/>
							</c:if>
						</t:buttonOut>
						<t:buttonOut url="/org/enableOrg.html">
						
						<c:if test="${org.status eq '2'}">
							<input type="button" value="启用" onclick="enableOrg('${org.id}')"/>
						</c:if>
						</t:buttonOut>
						
						<t:buttonOut url="/org/orgDetails.html">	            			            	
							<input type="button" value="详情" onclick="orgDetails('${org.id}')"/>
						</t:buttonOut>
	            	</c:if>
	            	
	            	 
	            	
	            	 
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