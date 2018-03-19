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
					<th>设备名称</th>
					<th>经销商名称</th>
					<th>设备分类</th>
					<th>设备列序号</th>
					<th>机器编号</th>
					<th>机器状态</th>
					<th>业务状态</th>
					<th>二维码</th>
					<th>操作</th>
		 		</tr>
		 	</thead>

		 	<tbody>

			<c:forEach items="${page.results}" var="device">
				<tr>
					<td height="22" align="center" valign="middle">
							${device.deviceName}
					</td>
					<td height="22" align="center" valign="middle">
							${device.memo}
					</td>
					<td height="22" align="center" valign="middle">
							${device.flag}
					</td>
					<td height="22" align="center" valign="middle">
							${device.creator}
					</td>
					<td height="22" align="center" valign="middle">
							${device.deviceCode}
					</td>
					<td height="22" align="center" valign="middle">
							${device.machineStatus}
					</td>
					<td height="22" align="center" valign="middle">
							<c:if test="${device.status == '0'}">
								启用
							</c:if>
							<c:if test="${device.status == '1'}" >
							<font color="red">
									   禁用
							 </font>
							</c:if>
					</td>
					<td height="22" align="center" valign="middle">
						<img  class="ewm-img" style="padding:0;margin:0;width:30px;height:30px;"
							  id="${device.qrCode}" src="<%=request.getContextPath()%>${device.qrUrl}">
					</td>

						  <td height="22" align="center" valign="middle">
							  <c:if test="${device.status eq '0'}">
								  <t:buttonOut url="/device/disable.html">
									  <input type="button" value="停用"   onclick="f_disable('${device.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <c:if test="${device.status eq '1'}">
								  <t:buttonOut url="/device/enable.html">
									  <input type="button" value="启用"   onclick="f_enable('${device.id}')"/>
								  </t:buttonOut>
							  </c:if>
							  <t:buttonOut url="/device/toEdit.html">
								<input type="button" value="修改"   onclick="f_toEdit('${device.id}')"/>
							  </t:buttonOut>

							<t:buttonOut url="/device/delete.html">
								<input type="button" value="删除" onclick="f_delete('${device.id}')"/>
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
<script>
	$(function(){
		$(".ewm-img").on(
				{"mouseenter":function(){
					var evm_offset=$(this).offset();
					$("<div class='img-da'><img src="+ $(this).attr("src")+" alt=''><span id='qr' style='position: absolute; top: 270px; left: 30px;'>二维码编号："+$(this).attr("id")+"</span></div>").appendTo($("body"));
					$(".img-da").css({left:evm_offset.left-300,top:evm_offset.top-100});
					//$("#qr").css({left:evm_offset.left-300,top:evm_offset.top+10});


				},
					"mouseleave":function(){
						$(".img-da").remove();
					}
				});
	});
</script>
	</html>