
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	  <script type="text/javascript">
			
		</script>
</head>
<body>
	<form class="form-edit"  method="post" accept-charset="utf-8" action="<%=request.getContextPath()%>/role/editRole.html">
	         
             <input class="form-txt" type="hidden" name="id" value="${role.id}"  maxlength="20" datatype="*"  sucmsg="&nbsp;"  required="required"/>
	         
	         <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>角色名称:</label>
                      <input class="form-txt" type="text" name="rolename" value="${role.rolename}"  maxlength="20" datatype="*"  sucmsg="&nbsp;"  required="required"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
	         <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>上级角色:</label>
							<select class="form-txt" name="orgMerId"  datatype="*"  sucmsg="&nbsp;"  required="required">
								<c:forEach items="${rolelist }" var="role1">
									<c:if test="${role.orgMerId eq role1.id }">
										<option value="${role1.id }" selected="selected">${role1.rolename }</option>
									</c:if>
									<c:if test="${role.id ne role1.id }"><option value="${role1.id }">${role1.rolename }</option></c:if>
								</c:forEach>
							</select>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>描述:</label>
                      <textarea    class="form-cont" cols="29" name="roledesc"       datatype="*"  sucmsg="&nbsp;" style="overflow-y:auto"  required="required">${role.roledesc}</textarea>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>备注:</label>
                       <textarea    class="form-cont" cols="29" name="remark"       datatype="*"  sucmsg="&nbsp;" style="overflow-y:auto"  required="required">${role.remark}</textarea>
                      <span class="form-yz Validform_checktip"></span>
              </div>
	           
	          
	
	
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存"/>
                      <input class="form-btn" type="button" name="" value="关闭" onclick="myClose()"/>
              </div>
          </form>
          
          <script type="text/javascript">
	    	//表单验证
	      $(function(){
		    $(".form-edit").Validform({tiptype:3,
		    	datatype:{
		    		
		    	},
		    	ajaxPost:true,
				callback:function(data){
					if(data.success){
						query('<%=request.getContextPath()%>/role/list.html');
						myClose();
					}else{
						 layer.msg('修改失败！',{icon:5});
					}
				}

		    });
		  });
	 	
	    	
	      function myClose(){
	    	  $("#tjdy").modal('hide');
	      }
		</script>
		    
 		
</body>
</html>