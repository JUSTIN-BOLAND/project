<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page" %>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>

</head>
<body>
  <div class="table-responsive">
	<table class="table table-hover">
		 	<thead>
		 		<tr>
					<th>经销商名称</th>

					<th>经销商编号</th>
					<th>设备序列号</th>
					<th>联系人</th>

					<th>微信支付商户号</th>
					<th>支付宝商户号</th>

					<th>状态</th>
					<th>账户有效期</th>

					<th>操作</th>
		 		</tr>
		 	</thead>

		 	<tbody>

			<c:forEach items="${page.results}" var="dealer">
				<tr>
					<td height="22" align="center" valign="middle">
							${dealer.dealerName}
					</td>
					<td height="22" align="center" valign="middle">
							${dealer.dealerId}
						  </td>
						  <td height="22" align="center" valign="middle">
							${dealer.series}
						  </td>
						  <td height="22" align="center" valign="middle">
							${dealer.linkman}
						  </td>

						  <td height="22" align="center" valign="middle">
							${dealer.wxMchId}
						  </td>

						<td height="22" align="center" valign="middle">
								${dealer.aliMachId}
						</td>


						  <td height="22" align="center" valign="middle">
							<c:if test="${dealer.status == '0'}">
								启用
							</c:if>
							<c:if test="${dealer.status == '1'}" >
							<font color="red">
									   禁用
							 </font>
							</c:if>
						  </td>

						   <td height="22" align="center" valign="middle">
								   ${dealer.accountExpiration}
						   </td>

						  <td height="22" align="center" valign="middle">
							  <c:if test="${dealer.status eq '0'}">
								  <t:buttonOut url="/dealer/disable.html">
									  <input type="button" value="停用"   onclick="disableDealer('${dealer.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <c:if test="${dealer.status eq '1'}">
								  <t:buttonOut url="/dealer/enable.html">
									  <input type="button" value="启用"   onclick="enableDealer('${dealer.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <t:buttonOut url="/dealer/toEditDealer.html">
								<input type="button" value="修改"   onclick="toEditDealer('${dealer.id}')"/>
							  </t:buttonOut>

							<t:buttonOut url="/dealer/deleteDealer.html">
								<input type="button" value="删除" onclick="deleteDealer('${dealer.id}')"/>
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