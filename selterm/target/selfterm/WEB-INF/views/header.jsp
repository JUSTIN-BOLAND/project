<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!-- 头部 -->
<header id="header" class="header clear">
         <script type="text/javascript">
         $(function(){
         if(window.name==="2"){
        	location.href="<%=request.getContextPath()%>/system/index.html";
         };
         });
		      function toEditUser(){
		    	  var url= '<%=request.getContextPath()%>/system/toEditUserPad.html';
					//加载模态框数据
				  $("#editPad").load(url);
			  }
        </script>
	<h1 class="logo fl"><a href="index.html" title=""><img src="<%=request.getContextPath()%>/man/images/logo.jpg" alt=""></a></h1>
	 
	<div class="head-right clear fr">
		<div class="skin clear fl" style="display:none;">
		   <span class="skin-word">个性化换肤</span>
		   <i class="skin-color1"></i>
		   <i class="skin-color2"></i>
		   <i class="skin-color3"></i>
		</div>
		<div class="haed-user-info fl">
			<i  class="glyphicon glyphicon-user"></i><span class="head-user-name">您好，${userInfo.id}&nbsp;${userInfo.rolename}</span>
		</div>
		<input class="btn heade-btn" type="button" name="" value="修改密码"   data-toggle="modal" data-target="#xgmmdy"  onclick="toEditUser()"/>
		<input class="btn heade-btn" type="button" name="" value="退&nbsp;出" onclick="loginOut();">
		
		<form action="<%=request.getContextPath()%>/system/loginOut.html" method="post" id="loginOutForm">
		</form>
	</div>
</header>
<style type="text/css">
<!--
.heade-btn:focus{color: #fff}
-->
</style>

         <div  class="modal fade" id="xgmmdy" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			 	<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			          <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			            <h4 class="modal-title" id="">修改密码</h4>
			          </div>
			          <div  class="model-box" id="editPad">
			          
			          </div>
			        </div>
			      </div>
			 </div>

    
            