
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
	<form class="form-edit"  method="post" accept-charset="utf-8" action="<%=request.getContextPath()%>/system/editUserPad.html">
	         
             <input class="form-txt" type="hidden" name="id" value="${userInfo.id}"  />
	         
	         <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>用户名:</label>
                      <input class="form-txt" type="text" name="id" value="${userInfo.id}"  disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>原密码:</label>
                      <input class="form-txt" type="text" name="" value="" datatype="userPad"   nullmsg="不能为空"  errormsg="输入的原密码错误,请重新输入!" />
                      <span class="form-yz Validform_checktip"></span>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>新密码:</label>
                      <input class="form-txt" type="password"    name="password" nullmsg="不能为空" />
                      <span class="form-yz Validform_checktip"></span>
              </div>
               <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>再次输入密码:</label>
                      <input  class="form-txt" type="password"  datatype="*" recheck="password" errormsg="您两次输入的账号密码不一致！" />
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
		    	"userPad":function(gets,obj,curform,regxp){
	    			/*参数gets是获取到的表单元素值，
					  obj为当前表单元素，
					  curform为当前验证的表单，
					  regxp为内置的一些正则表达式的引用。*/
	    			var falg = false;
	            	$.ajax({ 
	            		url: '<%=request.getContextPath()%>/system/comparePad.html?pad='+gets, 
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
						layer.msg('修改成功,请重新登录！',{icon:1});
						setTimeout(function(){  //使用  setTimeout（）方法设定定时3000毫秒
							window.location.href=window.location.href;
						},3000);
						 $("#xgmmdy").modal('hide');
					}else{
						 layer.msg('修改失败！',{icon:5});
					}
				}

		    });
		  });
	 	
	    	
	      function myClose(){
	    	  $("#xgmmdy").modal('hide');
	      }
		</script>
		    
 		
</body>
</html>