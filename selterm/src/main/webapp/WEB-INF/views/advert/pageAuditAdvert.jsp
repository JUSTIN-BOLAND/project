<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript">
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
	</script>
</head>
<body>
  <div class="table-responsive">
	<table class="table table-hover">
        <thead>
          <tr>
            <th>广告名称</th>
            
            <th>商户名称</th>
            <th>图片</th>
            <th>链接</th>
            <th>广告位置</th>
            <th>有效期</th>
            <th>状态</th>
            <th>点击量</th>
            <th>操作</th>
          </tr>
        </thead>  
        <tbody>
			<c:forEach items="${page.results}" var="advertise">
	          	<tr>
	            <td >
	            ${advertise.advname }
	            </td>
	            <td >
	            ${advertise.mername }
	            </td>
	            <td >
	           <img src=" ${advertise.advimage }" alt=" ${advertise.advimage }" class="ewm-img" width="50px" height="50px"/>
	            </td>
	            <td >
	            ${advertise.advlink }
	            </td>
	            <td>
	            <c:if test="${advertise.advplace eq 1}">
               		支付页面
               	</c:if>
               	<c:if test="${advertise.advplace eq 2}">
               		支付完成页面
               	</c:if>
	            </td>
	            
	            <td>
	            <fmt:formatDate value="${advertise.starttime }" pattern="yyyy-MM-dd HH:mm:ss" />-
	            <fmt:formatDate value="${advertise.endtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	             
	            <td >
	            <c:if test="${advertise.pushstatus == '1'}">
               		已发布
               	</c:if>
               	<c:if test="${advertise.pushstatus == '0'}">
               		未发布
               	</c:if>
	            </td>
	            <td >
	             ${advertise.clicknum}
	            </td>
	            <td >
<%-- 	             <fmt:formatDate value="${org.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />  --%>
	           <input type="button" value="通过"  onclick="audit('${advertise.id}','1')"/>
				
				<input type="button" value="不通过" onclick="audit('${advertise.id}','2')"/>
				
					  
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