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

<script type="text/javascript">
$(function(){
    $(".ewm-img").on(
     {"mouseenter":function(){
       var evm_offset=$(this).offset();
       $("<div class='img-da'><img src="+ $(this).attr("src")+" alt=''></div>").appendTo($("body"));
       $(".img-da").css({left:evm_offset.left+50,top:evm_offset.top-100})


    },
     "mouseleave":function(){
       $(".img-da").remove();
     }
   });
    
    $(".tab-ck1").on("click",function(){
        if($(this).is(':checked')){
           $(".tab-ck2").prop("checked",true);
         }else{
          $(".tab-ck2").prop("checked",false);
         }
    });

  });
</script>
  <div class="table-responsive">
<table class="table table-hover">
       <thead>
         <tr>
         <th><input type="checkbox" class="tab-ck1" style="width:15px;height:15px;"/></th>
            <th>二维码编号</th>
            <th>二维码地址</th>
			<th>支付方式</th>
			<th>金额</th>
			<th>是否固定</th>
<!-- 			<th>商户id</th> -->
			<th>商户名称</th>
<!-- 			<th>门店id</th> -->
			<th>门店名称</th>
             <th>收银员</th>
			<th>创建时间</th>
			<th>操作</th>
         </tr>
       </thead>
       <tbody>
         	<c:forEach items="${page.results}" var="te">
				<tr>
					<td><input type="checkbox" value="${te.id}" class="tab-ck2" name="ids" style="width:15px;height:15px;"/></td>
				<td height="22" align="center" valign="middle" >
                       ${te.id}
                    </td>
                    <td height="22" align="center" valign="middle" style="padding:0;margin:0">
                       <img class="ewm-img" src="${te.qrcodeadress}" width="50px" style="padding:0;margin:0"/>
                    </td>
                    
                    <td height="22" align="center" valign="middle">
                     <c:if test="${te.paytype==1}">
                                                               支付宝
                     </c:if>
                     <c:if test="${te.paytype==2}">
                                                               微信
                     </c:if>
                     <c:if test="${te.paytype==3}">合一</c:if>
                    </td>
                      <td height="22" align="center" valign="middle">
                       ${te.mony}
                    </td>
                    <td height="22" align="center" valign="middle">
                     <c:if test="${te.fixation==1}">
                                                               是
                     </c:if>
                     <c:if test="${te.fixation==2}">
                                                               否
                     </c:if>
                    </td>
<!--                     <td height="22" align="center" valign="middle"> -->
<%--                        ${te.merid} --%>
<!--                     </td> -->
                      <td height="22" align="center" valign="middle">
                       ${te.name}
                    </td>
<!--                       <td height="22" align="center" valign="middle"> -->
<%--                        ${te.storeid} --%>
<!--                     </td> -->
                     <td height="22" align="center" valign="middle">
                       ${te.storename}
                    </td>
                    <td height="22" align="center" valign="middle">
                            ${te.saler}
                    </td>
                      <td height="22" align="center" valign="middle">
                     <fmt:formatDate value="${te.creattime}" pattern="yyyy-MM-dd HH:mm:ss" />
                       
                    </td>
                    
                    <td height="22" align="center" valign="middle">
<%--                        <t:buttonOut url="/teQrcode/addEdidteQrcode.html"> --%>
                    	<input type="button" data-toggle="modal" data-target="#tjdy" value="编辑" onclick="toAddEditSalesman('${te.id}')"/>
<%--                     	</t:buttonOut> --%>
                    	<t:buttonOut url="/teQrcode/teQrcodeDelete.html">
                    	<input type="button" value="删除" onclick="deleteSalesman('${te.id}')"/>
                    	</t:buttonOut>
                            <input class="grap-btn grap-btn-fr" type="button" name=""
                                   onclick="return f_showBindSaler('${te.id}','${te.storeid}');" value="绑定" />
                    </td>
				</tr>
			</c:forEach>
        
      </tbody>
</table>
<input class="grap-btn" type="button" onclick="deletebatch()" value="删除"/>
</div>
   		 <div class="main-pading clear">
   <%@ include file="/WEB-INF/views/page.jsp"%>
</div>
	       
</body>
</html>