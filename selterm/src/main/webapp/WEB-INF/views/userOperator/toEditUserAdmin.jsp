<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>

</head>
<body>
	<form class="form-add" method="get" accept-charset="utf-8"
		action="<%=request.getContextPath()%>/userOperator/editUserOperator.html">

			<input class="form-txt" type="hidden" name="id"
				value="${userOperator.id}" />

		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>用户登录账户：</label> <input
				class="form-txt" type="text" id="orgName" name="loginnmame" value="${userOperator.loginnmame}" readonly="readonly"
				 /> <span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>用户名：</label> <input type="text"
				class="form-txt" name="name" value="${userOperator.name}" />
		</div>
				<t:buttonOut url="/userOperator/showpass.html">
		<div class="form-group">
		 <style>
                      input[type='password']{margin-right:10px;}
                      </style>
			<label class="form-lal"><i class="text-danger">*</i>密码：</label> <input type="password" 
				class="form-txt" id="password" value="${password}" disabled="disabled"/>
				<img style="width:20px; height:20px;margin: 8px 0 0 10px; cursor: pointer;  " id="hideDisplay" src="../man/images/close.png"/>
		</div>
		</t:buttonOut>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>联系方式：</label> <input
				class="form-txt" type="text" name="contact"
				value="${userOperator.contact }" maxlength="20" datatype="*"
				sucmsg="&nbsp;" nullmsg="不能为空" /> <span
				class="form-yz Validform_checktip"></span>
		</div>

		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>所属角色：</label> <select
				name="roleid" id="roleText" class="form-txt" onchange="editselectuser()">
				<c:forEach items="${roleList}" var="role">
					<c:if test="${roleId == role.id}">
						<option value="${role.id}" selected="selected">${role.rolename}
						</option>
					</c:if>
					<c:if test="${roleId != role.id}">
						<option value="${role.id}">${role.rolename}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<%-- <div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>上级用户：</label> <select
				name="parentid"  class="form-txt">
				<option value="">请选择</option>
				<c:forEach items="${listuser}" var="user">
					<c:if test="${user.id eq parentuser.id}">
						<option value="${user.id}" selected="selected">${user.name}
						</option>
					</c:if>
					<c:if test="${user.id ne parentuser.id}">
						<option value="${user.id}">${user.name}</option>
					</c:if>
				</c:forEach>
			</select>当角色为经理时，可以不选择
		</div> --%>



		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>备注：</label>
			<%--                       <input class="form-txt" type="text" name="remark" value="${userOperator.remark }" maxlength="20" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空" /> --%>
			<textarea rows="4" class="form-cont" name="remark" datatype="*"
				sucmsg="&nbsp;" nullmsg="不能为空">${userOperator.remark}</textarea>
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal">&nbsp;</label> <input class="form-btn"
				type="submit" name="" value="保存" /> <input class="form-btn"
				type="button" name="" value="关闭" onclick="myClose()" />
		</div>
	</form>



	<script type="text/javascript">
          
          
          
	    	//表单验证
	      $(function(){
		    $(".form-add").Validform({tiptype:3,
		    	datatype:{
		    		"uniquLoginName":function(gets,obj,curform,regxp){
		    			/*参数gets是获取到的表单元素值，
						  obj为当前表单元素，
						  curform为当前验证的表单，
						  regxp为内置的一些正则表达式的引用。*/
		    			var falg = false;
		            	$.ajax({ 
		            		url: '<%=request.getContextPath()%>/system/checkLoginName.html?loginName='+gets, 
		            		context: document.body, 
		            		async: false,
		            		success: function(date){
		            			console.log("检验结果："+date);
		            			falg = date;
		            		}
		            	});
		            	console.log("检验结果2：");
		            	return falg;
		    		} 
		    		
		    	},
		    	ajaxPost:true,
				callback:function(data){
					if(data.success){
						query('<%=request.getContextPath()%>/userOperator/list.html');
										$("#tjdp").modal('hide');
									} else {
										layer.msg('添加失败！', {
											icon : 5
										});
									}
								}

							});
		});

		function myClose() {
			$("#tjdp").modal('hide');
		}
		
		function editselectuser(){
			var id = $("#roleText").val();
			if(id==null || id==''){
				return;
			}
   			$("select[name=parentid]").empty();
			$.ajax({ 
			    type: "POST",  
        		url: '<%=request.getContextPath()%>/userOperator/selectparentiduser.html',
        		data: {"id":id},
        		async : true,
        		dataType: "json",
        		success: function(data){
        			console.info(data)
					var list =  eval(data);
					var str = '<option value="">请选择</option>';
					for(var i =0;i<list.length;i++){
						str += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
					}
					$("select[name=parentid]").append(str);

        		}
        	}) 
			
		}
		  var inum=1;
	 		$("#hideDisplay").click(function () {
	 			if(inum==1){
	 				$(this).attr("src","<%=request.getContextPath()%>/man/images/open.png"); 
	 				$("#password").attr('type','text');
	 				inum++;
	 			}else{
	 				$(this).attr("src","<%=request.getContextPath()%>/man/images/close.png");
	 				$("#password").attr('type','password');
	 				inum--;
	 			} 
	 	
	 		});
	</script>


</body>
</html>