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
            <th>商户名称</th>
            <th>支付页面广告数</th>
            <th>支付成功页面广告数</th>
            <th>总广告数</th>
          </tr>
        </thead>  
        <tbody>
			<c:forEach items="${page.results}" var="advertise">
	          	<tr>
	            <td >
	            ${advertise.mername }
	            </td>
	            <td >
	            ${advertise.successAdvert }
	            </td>
	            <td >
	            ${advertise.completeAdvert }
	            </td>
	             <td >
	              ${advertise.sumAdvert }
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