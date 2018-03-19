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
					<%--<th>批号</th>--%>
					<th>卡号</th>
					<th>姓名</th>
					<th>手机号</th>
					<th>账户余额</th>
					<%--<th>充值账户余额</th>--%>
					<th>业务状态</th>
					<th>操作</th>
		 		</tr>
		 	</thead>

		 	<tbody>

			<c:forEach items="${page.results}" var="card">
				<tr>
					<%--<td height="22" align="center" valign="middle">
							${card.batchNo}
					</td>--%>
					<td height="22" align="center" valign="middle">
							${card.cardNo}
					</td>
					<td height="22" align="center" valign="middle">
							${card.name}
					</td>
					<td height="22" align="center" valign="middle">
							${card.mobile}
					</td>
					<td height="22" align="center" valign="middle">
							${card.balance}
					</td>
				<%--	<td height="22" align="center" valign="middle">
							${card.deposit}
					</td>--%>
					<td height="22" align="center" valign="middle">
							<c:if test="${card.status == '0'}">
								启用
							</c:if>
							<c:if test="${card.status == '1'}" >
							<font color="red">
								挂失
							 </font>
							</c:if>
					</td>

						  <td height="22" align="center" valign="middle">
							  <c:if test="${card.status eq '0'}">
								  <t:buttonOut url="/card/disable.html">
									  <input type="button" value="挂失"   onclick="f_disable('${card.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <c:if test="${card.status eq '1'}">
								  <t:buttonOut url="/card/enable.html">
									  <input type="button" value="启用"   onclick="f_enable('${card.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <t:buttonOut url="/card/toEdit.html">
								<input type="button" value="修改"   onclick="f_toEdit('${card.id}')"/>
							  </t:buttonOut>

							<t:buttonOut url="/card/delete.html">
								<input type="button" value="删除" onclick="f_delete('${card.id}')"/>
							</t:buttonOut>
							  <t:buttonOut url="/card/toAdd.html">
								  <input  type="button" style="line-height: 0px;margin-left:20px;"
										 onclick="changeRightMenu('../card/toRecharge.html?id=${card.id}');"
										 value="充值 " />
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