<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
	<section class="navigation"> <span>您当前所在的位置：</span>
	<a href="#" title="">系统管理</a>/<i>账号信息</i> </section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>用户名：</label>
                      <input class="form-txt" type="text" name="name"  value="${userOperator.name}"  />
              </div>
              
             <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>所属角色：</label>
                      <select name="roleid" id="roleText" disabled="disabled" class="form-txt">
					    <c:forEach items="${roleList}" var="role">
					    <c:if test="${roleId == role.id}">
                          <option  value="${role.id}" selected="selected" disabled="disabled">${role.rolename} </option>
                        </c:if>
                        <c:if test="${roleId != role.id}">
                          <option  value="${role.id}" disabled="disabled">${role.rolename} </option>
                        </c:if>
                        </c:forEach>
					</select>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>所属机构：</label>
                      <input class="form-txt" type="text" name="contact" value="${org.name }" />
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系方式：</label>
                      <input class="form-txt" type="text" name="contact" value="${userOperator.contact }"/>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>备注：</label>
<%--                       <input class="form-txt" type="text" name="remark" value=""  /> --%>

							<textarea class="form-cont" name="remark">${userOperator.remark }</textarea>
              </div>
		
		
		</form>
       
	</div>
	</section>

</body>
</html>