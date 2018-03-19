<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.deyi.util.Page" language="java"%>
<%@ page import="com.deyi.entity.*" language="java"%>
<%@ page import="java.util.*" language="java"%>
<%@ page import="java.net.URLEncoder" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<style>
	.main-pading{ text-align: center;}
	.main-pading-text{vertical-align: middle;}
	.pagination{vertical-align: middle; display: inline-block}
	</style>
		 <div class="main-pading clear">
		 	<ul class="pagination">
			  <li><a href="#" onclick="toPage('${page.url}',1)" id="firstPage" title="First Page"> 首页</a></li>
			  <li><a href="#" onclick="toPage('${page.url}',${page.pageNo-1<1?1:page.pageNo-1})" id="previousPage" title="Previous Page"> 上一页</a> </li>
			  <li><a href="#">第 ${page.pageNo} 页</a></li>
			  <c:if test="${page.pageNo<page.totalPage}">
				  <li><a href="#" onclick="toPage('${page.url}',${page.pageNo+1})" id="nextPage" title="Next Page">下一页 </a></li>
			  </c:if>
			  <li><a href="#" onclick="toPage('${page.url}',${page.totalPage==0?1:page.totalPage})" id="endPage" title="Last Page">末页 </a></li>
             </ul>
             <span class="main-pading-text">共${page.totalRecord} 条记录共${page.totalPage}页</span>
		 </div>
