
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
	<form class="form-add"  method="get" accept-charset="utf-8" action="<%=request.getContextPath()%>/role/roleAdd.html">

	         <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>角色名称:</label>
                      <input class="form-txt" type="text" name="rolename"  maxlength="20" datatype="*"  sucmsg="&nbsp;"  required="required"/>
                      <span class="form-yz Validf	orm_checktip"></span>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>上级角色:</label>
<!--                       <input class="form-txt" type="text" name="rolename"  maxlength="20" datatype="*"  sucmsg="&nbsp;"  required="required"/> -->
                      <select class="form-txt" name="orgMerId">
                      	<c:forEach items="${rolelist }" var="role">
                      	<option value="${role.id }">${role.rolename }</option>
                      	</c:forEach>
                      </select>
                      <span class="form-yz Validf	orm_checktip"></span>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>描述:</label>
                      <textarea  class="form-cont" name="roledesc"  maxlength="20" datatype="*"  sucmsg="&nbsp;"   required="required"></textarea>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>备注:</label>
                      
                       <textarea  class="form-cont" name="remark"  maxlength="20" datatype="*"  sucmsg="&nbsp;"   required="required"></textarea>
                      <span class="form-yz Validform_checktip"></span>
              </div>
	
	          <div class="form-group">
	          		<input type="hidden" name="rolestatus" value="1" />
                      <!-- <label class="form-lal"><i class="text-danger">*</i>是否启用：</label>
                      <select name="rolestatus" class="form-txt">
                        <option  value="1">启用 </option>
                        <option  value="2">停用 </option>
					</select> -->
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
		    $(".form-add").Validform({tiptype:3,
		    	datatype:{
		    		
		    	},
		    	ajaxPost:true,
				callback:function(data){
					if(data.success){
						query('<%=request.getContextPath()%>/role/list.html');
						$("#tjdy").modal('hide');
					}else{
						 layer.msg('添加失败！',{icon:5});
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