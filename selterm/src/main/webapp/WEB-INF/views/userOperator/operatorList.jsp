<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	  <script type="text/javascript">
	    function deleteOperator(id){
	    	 layer.confirm('是否删除？', {
	    		  btn: ['确定','取消'] //按钮
	    		}, 
	    		function(){
	    			$.ajax({ 
	            		url: '<%=request.getContextPath()%>/userOperator/deleteUserOperator.html?id='+id, 
	            		context: document.body, 
	            		async: false,
	            		success: function(data){
	            			if(data.success){
	            				query('<%=request.getContextPath()%>/userOperator/page.html');
	            				layer.closeAll();
	            				
	            			}else{
	            				layer.msg('删除失败！',{icon:5});
	            			}
	            		}
	            	});
	    		}
	    	);
		}
	      
	      //添加角色列表
      function toAddUserOperator(){
    	  var url= '<%=request.getContextPath()%>/userOperator/toAddUserOperator.html';
			//加载模态框数据
		  $("#modal_show").load(url);
	  }
      
      function toEditUserOperator(id){
    	  
    	  var url= '<%=request.getContextPath()%>/userOperator/toEditUserOperator.html?id='+ id;
			//加载模态框数据
		  $("#modal_show1").load(url);
	  }
      
      //继承
      function accountInherit(id){
    	  var url= '<%=request.getContextPath()%>/userOperator/accountInherit.html?id='+ id;
			//加载模态框数据
		  $("#modal_zhjc").load(url);
	  }
      
      //重置密码
      function resetpass(id){
    	  layer.confirm('是否重置密码？', {
    		  btn: ['确定','取消'] //按钮
    		}, 
    		function(){
    			$.ajax({ 
            		url: '<%=request.getContextPath()%>/userOperator/resetpass.html?id='+id, 
            		context: document.body, 
            		async: false,
            		success: function(data){
            			if(data.success){
            				layer.msg(data.message,{icon:1});
            			}else{
            				layer.msg(data.message,{icon:5});
            			}
            		}
            	});
    		}
    	);
      }
      
      //启用用户
      function enableuser(id){
    	  layer.confirm('是否启用用户？', {
    		  btn: ['确定','取消'] //按钮
    		}, 
    		function(){
    			$.ajax({ 
            		url: '<%=request.getContextPath()%>/userOperator/enableuser.html?id='+id, 
            		context: document.body, 
            		async: false,
            		success: function(data){
            			if(data.success){
            				query('<%=request.getContextPath()%>/userOperator/page.html');
            				layer.closeAll();
            				//layer.msg(data.message,{icon:1});
            			}else{
            				layer.msg(data.message,{icon:5});
            			}
            		}
            	});
    		}
    	);
      }
      
    //禁用用户
      function disableuser(id){
    	  layer.confirm('是否禁用用户？', {
    		  btn: ['确定','取消'] //按钮
    		}, 
    		function(){
    			$.ajax({ 
            		url: '<%=request.getContextPath()%>/userOperator/disableuser.html?id='+id, 
            		context: document.body, 
            		async: false,
            		success: function(data){
            			if(data.success){
            				query('<%=request.getContextPath()%>/userOperator/page.html');
            				layer.closeAll();
//             				layer.msg(data.message,{icon:1});
            			}else{
            				layer.msg(data.message,{icon:5});
            			}
            		}
            	});
    		}
    	);
      }
	 </script>
	 
	
</head>
<body>
	
	       	<section class="navigation">
	          <span>您当前所在的位置：</span><a  href="#" title="">系统管理</a>/<i>用户管理</i>
	        </section>
	        <section class="main-cont">
            <div class="article">
		       	<form id="query_from">
			       <div class="arcle-grap">
				         
        				<input class="grap-txt" type="text" name="orgname" placeholder="机构名称"/>
        				<input class="grap-txt" type="text" name="name" placeholder="用户名"/>
        				<input class="grap-txt" type="text" name="rolename" placeholder="角色"/>
				        <input class="grap-btn" type="button" onclick="query('<%=request.getContextPath()%>/userOperator/page.html');" value="查询"/>
				        <t:buttonOut url="/userOperator/toAddUserOperator.html">
				        	<input class="grap-btn grap-btn-fr" type="button" data-toggle="modal" data-target="#tjdy"  value="添加用户" onclick="toAddUserOperator()"/>
				        </t:buttonOut>
			       </div>
			       <%@ include file="/WEB-INF/views/pageSize.jsp"%>		
				</form>
				<div id="page_data">
			       
				</div>	 
				
				<!-- 加载初始数据 -->
				<script type="text/javascript">
 					 var url = '<%=request.getContextPath()%>/userOperator/page.html';
	    			 $("#page_data").load(url);
				</script>
				
				<!-- 模态框（Modal） start -->
			 <div  class="modal fade" id="tjdy" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			 	<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			          <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			            <h4 class="modal-title" id="myModalLabel">添加用户</h4>
			          </div>
			          <div  class="model-box" id="modal_show">
			          
			          </div>
			        </div>
			      </div>
			 </div>
			 
			 <div  class="modal fade" id="tjdp" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			 	<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			          <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			            <h4 class="modal-title" id="myModalLabel">编辑用户</h4>
			          </div>
			          <div  class="model-box" id="modal_show1">
			          
			          </div>
			        </div>
			      </div>
			 </div>
			 
			 <div  class="modal fade" id="zhjc" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			 	<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			          <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			            <h4 class="modal-title" id="myModalLabel">账号继承</h4>
			          </div>
			          <div  class="model-box" id="modal_zhjc">
			          
			          </div>
			        </div>
			      </div>
			 </div>
			 
			 

<!--     <div class="modal fade" id="tjjc" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"> -->
<!--       <div class="modal-dialog modal-wp"> -->
<!--         <div class="modal-content"> -->
<!--           <div class="modal-header"> -->
<!--             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--             <h4 class="modal-title" id="myModalLabel">添加帐号继承</h4> -->
<!--           </div> -->
<!--           <div  class="model-box"> -->
          
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
			 
			 <!-- 模态框（Modal） end -->
      	</div>
        </section>
</body>
</html>