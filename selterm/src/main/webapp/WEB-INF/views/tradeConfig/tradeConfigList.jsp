<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	  <script type="text/javascript">
	  
	  $().ready(function(){
		  var $excelButton = $("#excelButton");
		  
		  $excelButton.click(function(){
				document.getElementById("query_from").action="<%=request.getContextPath()%>/org/exportAll.html";
				document.getElementById("query_from").submit();	
				document.getElementById("query_from").action="<%=request.getContextPath()%>/org/page.html";	
			});
		  
	  });
	  
			function deleteOrg(id){
         		layer.confirm('是否删除？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		}, 
  	    		function(){
  	    			$.ajax({ 
  	            		url: '<%=request.getContextPath()%>/org/orgDelete.html?id='+id, 
  	            		context: document.body, 
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/org/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('删除失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}
			
			function enableOrg(id){
         		layer.confirm('是否启用？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		}, 
  	    		
  	    		function(){
  	    			$.ajax({ 
  	            		url: '<%=request.getContextPath()%>/org/enableOrg.html?id='+id, 
  	            		context: document.body, 
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/org/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('启用失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}
			
			function disableOrg(id){
				
				layer.confirm('是否停用？', {
	  	    		  btn: ['确定','取消'] //按钮
	  	    		}, 
	  	    		 
	  	    		function(){
	  	    			layer.confirm('此操作将会禁用渠道商下所有商户，请慎操作！！', {
	  	  	    		  btn: ['确定','取消'] //按钮
	  	  	    		}, 
	  	  	    		 
	  	  	    		function(){
	  	  	    			$.ajax({ 
	  	  	            		url: '<%=request.getContextPath()%>/org/disableOrg.html?id='+id, 
	  	  	            		context: document.body, 
	  	  	            		async: false,
	  	  	            		success: function(data){
	  	  	            			if(data.success){
	  	  	            				query('<%=request.getContextPath()%>/org/page.html');
	  	  	            				layer.closeAll();
	  	  	            			}else{
	  	  	            				layer.msg('失败！',{icon:5});
	  	  	            			}
	  	  	            		}
	  	  	            	});
	  	  	    		}
	  	  	    	);
	  	    		}
	  	    	);
         		
			}
  	    	 
			 
			//编辑商户
			function orgDetails(id){
		    	  changeRightMenu('../org/orgDetails.html?id='+id);
			  }
// 		      function toAddOrg(){
<%-- 		    	  var url= '<%=request.getContextPath()%>/org/toAddOrg.html'; --%>
// 					//加载模态框数据
// 				  $("#modal_show").load(url);
// 			  }
			
		      function toEditOrg(id){
		    	  var url= '<%=request.getContextPath()%>/org/toEditOrg.html?id='+id;
					//加载模态框数据
				  $("#modal_show1").load(url);
			  }
		      
		     
		      function passwordReset(id){
					layer.confirm('确定重置密码？', {
			    		  btn: ['确定','取消'] //按钮
			    		}, 
			    		function(){
			    			$.ajax({ 
		  	            		url: '<%=request.getContextPath()%>/org/passwordReset.html?id='+id, 
		  	            		context: document.body, 
		  	            		async: false,
		  	            		success: function(data){
		  	            			if(data.success){
		  	            				layer.msg('重置成功!',{icon:1});
		  	            				changeRightMenu('<%=request.getContextPath()%>/org/list.html');
		  	            				layer.closeAll();
		  	            			}else{
		  	            				layer.msg('失败！',{icon:5});
		  	            			}
		  	            		}
		  	            	});
			    		});
				}
		      
		</script>
</head>
<body>
	 
		    <section class="navigation">
		        <span>您当前所在的位置：</span><a  href="#" title="">渠道商管理</a>/<i>渠道商进件</i>
		    </section>
	        <section class="main-cont">
	 			<div class="article">
		 			<form id="query_from">
<!-- 		 			 <div style="display:none"> -->
<!-- 			[#if header?has_content] -->
<!-- 				[#list header as he] -->
<%-- 					<input type="checkbox" name="header"  checked="checked" value="${he}"/> --%>
<!-- 				[/#list] -->
<!-- 			[/#if] -->
<!-- 			[#if widths?has_content] -->
<!-- 				[#list widths as width] -->
<%-- 					<input type="checkbox" name="widths"  checked="checked" value="${width}"/> --%>
<!-- 				[/#list] -->
<!-- 			[/#if] -->
<!-- 		           </div>   -->
			 			 <div class="arcle-grap">
			 			 	<input class="grap-txt" type="text" name="code" placeholder="渠道商编码"/>
			 			 	<input class="grap-txt" type="text" name="name" placeholder="渠道商名称"/>
					        <input class="grap-btn" type="button" onclick="query('<%=request.getContextPath()%>/org/page.html');"  value="查询"/>
					        <t:buttonOut url="/org/toAddOrg.html">
						        <input class="grap-btn grap-btn-fr" type="button"  value="添加渠道商" onclick="changeRightMenu('../org/toAddOrg.html');"/>
					        </t:buttonOut>
<!-- 					        <input class="grap-btn grap-btn-fr" type="button" id="excelButton" value="导出" onclick="exceleOrg()"></input> -->
			       		</div>
						<%@ include file="/WEB-INF/views/pageSize.jsp"%>
					</form>
					<div id="page_data">
			       
					</div>	 
					<!-- 加载初始数据 -->
					<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/org/page.html';
		    			 $("#page_data").load(url);
					</script>
					<!-- 模态框（Modal） start -->
<!-- 					 <div  class="modal fade" id="tjdy" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"> -->
<!-- 					 	<div class="modal-dialog modal-lg"> -->
<!-- 					        <div class="modal-content"> -->
<!-- 					          <div class="modal-header"> -->
<!-- 					            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!-- 					            <h4 class="modal-title" id="myModalLabel">添加代理商</h4> -->
<!-- 					          </div> -->
<!-- 					          <div  class="model-box" id="modal_show"> -->
					          
<!-- 					          </div> -->
					         
<!-- 					        </div> -->
<!-- 					      </div> -->
<!-- 					 </div> -->
					 <!-- 模态框（Modal） end -->
					 <div  class="modal fade" id="tjdp" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					 	<div class="modal-dialog modal-lg">
					        <div class="modal-content">
					          <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					            <h4 class="modal-title" id="myModalLabel">编辑渠道商</h4>
					          </div>
					          <div  class="model-box" id="modal_show1">
					          
					          </div>
					         
					        </div>
					      </div>
					 </div>
					
		 		</div>
			 </section>
 		
</body>
</html>