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
          	<th>操作人账号</th>
            <th>操作对象</th>
            <th>操作类型</th>
            <th>内容</th>
            <th>时间</th>
          </tr>
        </thead>  
        <tbody>
			<c:forEach items="${page.results}" var="log">
	          	<tr>
	            <td >
	            ${log.userid }
	            </td>
	            <td >
	            ${log.type }
	            </td>
	            <td >
	              ${log.subtype }
	            </td>
	             <td >
	            	${log.content }
	            </td>
	            <td >
	            <fmt:formatDate value="${log.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />
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