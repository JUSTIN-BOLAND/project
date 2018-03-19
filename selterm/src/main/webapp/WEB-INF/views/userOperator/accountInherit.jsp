<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <style>
    .form-jc-left{ width:250px;margin-left:20px; }
    .form-jc{margin-bottom:20px;}
    .form-jc h3{ font-size:18px;color:#333;}
    .form-jc-box{ padding-left:35px;margin-top:20px; }
    .form-ul{ width:350px; height:300px; overflow-y:auto;border:1px solid #ccc; }
    .form-ul td{padding-left:10px; padding:5px;word-break:break-all; vertical-align: center; }
    .form-ul td i{margin-right:10px;}
    .form-ul td input,.form-ul td i,.form-ul td em{vertical-align:middle;}
    .form-jc-zh{white-space:normal}
    .form-jc-xn{ line-height:1.5; margin-top:20px;}
    .form-jc-zh em,.form-jc-xn em{margin-right:10px;}
    .form-jc-box  input[type="radio"]{margin-top:6px;}
    .form-jc-box  input[type="text"]{border:none;  background:#fff;vertical-align:middle;}
    .form-jc-box td  input[type="text"]{width:100%;}
    </style>
    
<script type="text/javascript">
$(function(){
    $(".form-add").Validform({tiptype:3,
    	ajaxPost:true,
		callback:function(data){
			if(data.success){
				 query('<%=request.getContextPath()%>/userOperator/page.html');
<%--             changeRightMenu('<%=request.getContextPath()%>/inheritRecordSheet/list.html'); --%>
layer.msg('继承成功！',{icon:1});
            $("#zhjc").modal('hide');
            
			}else{
				 layer.msg('继承失败！',{icon:5});
			}
		}

    });
  });
 
 function myClose(){
	 $("#zhjc").modal('hide');
 }
 
 
</script>
    
        <section class="main-cont">
             <form class="form-add" action="<%=request.getContextPath()%>/userOperator/saveInherit.html" method="get" accept-charset="utf-8">
              <div class="form-jc clear">
                <div class="form-jc-left fl">
                <h3>当前帐号:</h3>
                <div class="form-jc-box">
                  <input type="hidden" name="rawid" value="${userOperator.id}">
                  <p class="form-jc-zh"><em>登录账号：</em>${ userOperator.loginnmame}</p>
                  <p class="form-jc-xn"><em>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</em>${userOperator.name }</p>
                  <p class="form-jc-xn"><em>角色名称：</em>${userOperator.rolename }</p>
                </div>
                </div>
                <div class="form-jc-right fl">
                <h3>继承帐号:</h3>
                <div class="form-jc-box">
                <div  class="form-ul">
                     <table class="table">
                  <thead>
                    <tr>
                      <td width='15%'>选择</td>
                      <td width='30%'>登录账号</td>
                      <td width='30%'>用户名</td>
                      <td width='25%'>角色名称</td>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${newUserOperator}" var="user">
                      <tr id="inher">
                      <td><input type="radio" name="inheritId" id="inheritId" value="${user.id }" > </td>
                      <td><i>${user.loginnmame}</i></td>
                      <td><i>${user.name }</i></td>
                      <td><em>${user.rolename }</em></td>
                      </tr>
                       </c:forEach>
                       
                  </tbody>
                </table>
                </div>
               
        
              </div>
                </div>
              </div>
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存">
                      <input class="form-btn" type="button" name="" value="关闭" onclick="myClose()">
              </div>
          </form>
        </section>
     
	      
