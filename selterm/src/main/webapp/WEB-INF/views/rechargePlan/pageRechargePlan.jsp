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
					<th>套餐名称</th>

					<th>充值金额</th>
					<th>赠送金额</th>


					<th>到账金额</th>
					<th>售价</th>

					<th>状态</th>
					

					<th>操作</th>
		 		</tr>
		 	</thead>

		 	<tbody>

			<c:forEach items="${page.results}" var="rechargeplan">
				<tr>
					<td height="22" align="center" valign="middle">
							${rechargeplan.planName}
					</td>
					<td height="22" align="center" valign="middle">
							${rechargeplan.refillAmount}
						  </td>
						  <td height="22" align="center" valign="middle">
							${rechargeplan.giftAmount}
						  </td>
						  <td height="22" align="center" valign="middle">
							${rechargeplan.actualAmount}
						  </td>

						  <td height="22" align="center" valign="middle">
							${rechargeplan.price}
						  </td>



						  <td height="22" align="center" valign="middle">
							<c:if test="${rechargeplan.status == 0}">
								启用
							</c:if>
							<c:if test="${rechargeplan.status ==1}" >
							<font color="red">
									   禁用
							 </font>
							</c:if>
						  </td>



						  <td height="22" align="center" valign="middle">
							  <c:if test="${rechargeplan.status == 0}">
								  <t:buttonOut url="/rechargePlan/disable.html">
									  <input type="button" value="停用"   onclick="disable('${rechargeplan.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <c:if test="${rechargeplan.status == 1}">
								  <t:buttonOut url="/rechargePlan/enable.html">
									  <input type="button" value="启用"   onclick="enable('${rechargeplan.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <t:buttonOut url="/rechargePlan/toEditRechargePlan.html">
								<input type="button" value="修改"   onclick="toEdit('${rechargeplan.id}')"/>
							  </t:buttonOut>

							<t:buttonOut url="/rechargePlan/deleteRechargePlan.html">
								<input type="button" value="删除" onclick="f_delete('${rechargeplan.id}')"/>
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