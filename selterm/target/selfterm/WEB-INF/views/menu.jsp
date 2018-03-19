<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<aside class="aside">
	    	<p class="glyphicon glyphicon-align-justify nav-bar"></p>
	      	<ul class="aside-bar">
	      		<li >
	      			<a onclick="changeRightMenu('<%=request.getContextPath()%>/system/systemindex.html')"><div class="aside-bar-bg"><i class="glyphicon glyphicon-home" aria-hidden="true"></i><span>首页</span></div></a>
	      		</li>
	      		<c:forEach items="${menuList}" var="level1Menu">
	      			<c:if test="${level1Menu.menuLevel=='1' }">
						<li>
							<div class="aside-bar-bg">
								<i class="${level1Menu.menuClass}"></i><span>${level1Menu.menuName}</span>
							</div>
							
			                <dl class="aside-bar-drow hd">
								<c:forEach items="${menuList}" var="level2Menu">
									<c:if test="${level1Menu.menuId==level2Menu.parentMenuId }">
					                 	<dd>
					                 		<a onclick="changeRightMenu('<%=request.getContextPath()%>${level2Menu.menuUrl}')">
					                 			<i class="glyphicon glyphicon-triangle-right"></i>${level2Menu.menuName}
					                 		</a>
					                 	</dd>
				                 	</c:if>
								</c:forEach>
			                </dl>
						</li>
					</c:if>
	 			</c:forEach>
	         <style>
	         .aside-bar .aside-bar-drow dd{     cursor: pointer;}
	         </style>
	        
	        
	 		</ul>
	 	</aside>