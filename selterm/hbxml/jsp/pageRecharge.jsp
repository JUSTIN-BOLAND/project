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
					<th>商户订单号</th>
					<th>经销商名称</th>
					<th>机器名称</th>
					<th>机器编号</th>
					<th>支付方式</th>
					<th>付款金额</th>
					<th>到账金额</th>
					<th>卡号</th>
					<th>业务类型</th>
					<th>操作前余额</th>
					<th>操作后余额</th>
					<th>时间</th>
					<th>支付状态</th>
					<th>业务结果</th>
					<th>操作</th>
		 		</tr>
		 	</thead>

		 	<tbody>

			<c:forEach items="${page.results}" var="recharge">
				<tr>
					<td height="22" align="center" valign="middle">
							${recharge.orderNo}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.authCode}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.subMerchantId}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.memo}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.payType}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.payAmount}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.actualAmount}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.sendCmd}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.serviceType}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.beforeAmount}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.afterAmount}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.payTime}
					</td>
					<td height="22" align="center" valign="middle">
							${recharge.payStatus}
					</td>
					<td height="22" align="center" valign="middle">
							<c:if test="${recharge.status == '0'}">
								启用
							</c:if>
							<c:if test="${recharge.status == '1'}" >
							<font color="red">
									   禁用
							 </font>
							</c:if>
					</td>

						  <td height="22" align="center" valign="middle">
							  <c:if test="${recharge.status eq '0'}">
								  <t:buttonOut url="/recharge/disable.html">
									  <input type="button" value="停用"   onclick="f_disable('${recharge.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <c:if test="${recharge.status eq '1'}">
								  <t:buttonOut url="/recharge/enable.html">
									  <input type="button" value="启用"   onclick="f_enable('${recharge.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <t:buttonOut url="/recharge/toEdit.html">
								<input type="button" value="修改"   onclick="f_toEdit('${recharge.id}')"/>
							  </t:buttonOut>

							<t:buttonOut url="/recharge/delete.html">
								<input type="button" value="删除" onclick="f_delete('${recharge.id}')"/>
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