<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
			
		</script>
</head>
<body>
	<form class="form-add" method="get" accept-charset="utf-8"
		action="<%=request.getContextPath()%>/userOperator/userOperatorAdd.html">


		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>用户登录账户：</label> <input
				class="form-txt" type="text" id="orgName" name="loginnmame" value=""
				datatype="*,uniquLoginName,s1-18" sucmsg="&nbsp;" nullmsg="不能为空"
				errormsg="数据已经拥有" /> <span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>用户名：</label> <input type="text"
				class="form-txt" name="name" />
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>联系方式：</label> <input
				class="form-txt" type="text" name="contact" maxlength="11"
				datatype="*" sucmsg="&nbsp;" nullmsg="不能为空" /> <span
				class="form-yz Validform_checktip"></span>
		</div>

		<input type="hidden" name="orgId" value="1" />
       <%-- <div class="form-group">
          <label class="form-lal"><i class="text-danger">*</i>所属机构：</label>
          <select name="orgId" id="orgId" class="form-txt" onchange="selectRole()"  sucmsg="&nbsp;" nullmsg="不能为空">
          <option value="" >请选择</option>
          <c:forEach items="${orgS}" var="org">
					<option value="${org.id}" >${org.name}</option>
				</c:forEach>
          </select>
       </div>   --%>      

		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>所属角色：</label>
			<select	name="roleid" id="roleid" class="form-txt" onchange="selectuser()">
				<option value="">请选择</option>
				<c:forEach items="${roleList}" var="role">
					<option value="${role.id}">${role.rolename}</option>
				</c:forEach>
			</select>
		</div>
		<!-- <div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>上级用户：</label>
			<select	name="parentid" id="parentid" class="form-txt">
				<option value="">请选择</option>
			</select>
		</div> -->
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>启用账号：</label>
			<select	name="status" class="form-txt">
				<option value="1">启用</option>
				<option value="2">禁用</option>
			</select>
		</div>



		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>备注：</label>
			<!--                       <input class="form-txt" type="text" name="remark"  maxlength="20" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空" /> -->
			<textarea rows="4" class="form-cont" name="remark" datatype="*"
				sucmsg="&nbsp;" nullmsg="不能为空"></textarea>
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
										$("#tjdy").modal('hide');
									} else {
										layer.msg('添加失败！', {
											icon : 5
										});
									}
								}

							});
		});

		function myClose() {
			$("#tjdy").modal('hide');
		}
		
		function selectRole(){
			$("select[name=roleid]").empty();
			var id = document.getElementById("orgId").value;
			if(id==null || id==''){
				return;
			}
			$.ajax({ 
			    type: "POST",  
        		url: '<%=request.getContextPath()%>/userOperator/selectRole.html',
        		data: {"id":id},
        		async : true,
        		dataType: "json",
        		success: function(data){
        			
					var list =  eval(data);
					var str = '<option value="">请选择</option>';
					for(var i =0;i<list.length;i++){
						str += '<option value="'+list[i].id+'">'+list[i].rolename+'</option>';
					}
					$("select[name=roleid]").append(str);

        		}
        	}) 
			
		};
		
		function selectuser(){
			$("select[name=parentid]").empty();
			var id = $("#roleid").val();
			if(id==null || id==''){
				return;
			}
			$.ajax({ 
			    type: "POST",  
        		url: '<%=request.getContextPath()%>/userOperator/selectparentiduser.html',
        		data: {"id":id},
        		async : true,
        		dataType: "json",
        		success: function(data){
        			
					var list =  eval(data);
					var str = '<option value="">请选择</option>';
					for(var i =0;i<list.length;i++){
						str += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
					}
					$("select[name=parentid]").append(str);

        		}
        	}) 
			
		}
		
	</script>


</body>
</html>