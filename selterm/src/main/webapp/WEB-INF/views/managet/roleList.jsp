<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	    function deleteRole(id){
	    	 layer.confirm('是否删除？', {
	    		  btn: ['确定','取消'] //按钮
	    		}, 
	    		function(){
	    			$.ajax({ 
	            		url: '<%=request.getContextPath()%>/role/delectRole.html?id='+id, 
	            		context: document.body, 
	            		async: false,
	            		success: function(data){
	            			if(data.success){
	            				query('<%=request.getContextPath()%>/role/page.html');
	            				layer.closeAll();
	            				
	            			}else{
	            				layer.msg(data.message,{icon:5});
	            			}
	            		}
	            	});
	    		}
	    	);
		}
	      
	      //添加角色列表
      function toAddRole(){
    	  var url= '<%=request.getContextPath()%>/role/toRoleAdd.html';
    	  $("#myModalLabel").html("新增角色");
			//加载模态框数据
		  $("#modal_show").load(url);
	  }
      
      function toEditRole(id){
    	  var url= '<%=request.getContextPath()%>/role/toEditRole.html?id='+id;
    	  $("#myModalLabel").html("编辑角色");
			//加载模态框数据
		  $("#modal_show").load(url);
	  }
      
      function toDisableRole(id){
	    	 layer.confirm('是否停用？', {
	    		  btn: ['确定','取消'] //按钮
	    		}, 
	    		function(){
	    			$.ajax({ 
	            		url: '<%=request.getContextPath()%>/role/delectRole.html?id='+id, 
	            		context: document.body, 
	            		async: false,
	            		success: function(data){
	            			if(data.success){
	            				query('<%=request.getContextPath()%>/role/page.html');
	            				layer.closeAll();
	            				
	            			}else{
	            				layer.msg(data.message,{icon:5});
	            			}
	            		}
	            	});
	    		}
	    	);
		}
      
      function toAuthorizedRole(id){
    	  var url= '<%=request.getContextPath()%>/role/toAuthorizedRole.html?id='+id;
			//加载模态框数据
		  $("#modal_tjtree").load(url);
      }
      
      
      //授权
       function authorizedRoles(id){
     	    var flag=true;
   	    	var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true),
            v="";
   	    	var  menuId=[];
            for(var i=0;i<nodes.length;i++){
            v+=nodes[i].name + ",";
            menuId.push(nodes[i].id);
            }
	        console.log(menuId);	 
        	$.ajax({  
             type: "POST",  
             traditional: true,
             url: "<%=request.getContextPath()%>/role/authorizedRole.html", 
             data: {"id":id,"menuId":menuId},  
             dataType:"json",  
             async:true,  
             cache:false,  
             success: function(msg){  
            		
            		console.log(msg);
            		query('<%=request.getContextPath()%>/role/list.html');
            		console.log(msg.success);
     				$("#tjtree").modal('hide');
     				if(msg.success == true){
     					layer.msg(msg.message,{icon:1});
     				}else{
     					
     					layer.msg(msg.message,{icon:5});
     				}
             },
         });  
        	
        }
      
  
	 </script>


</head>
<body>

	<section class="navigation"> <span>您当前所在的位置：</span> <a
		href="#" title="">系统管理</a>/<i>角色管理</i> </section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
			<div class="arcle-grap">

				<input class="grap-txt" type="text" name="orgName"	placeholder="机构名称"/> 
				<input class="grap-txt" type="text" name="rolename"	placeholder="角色名称"/> 
				<input class="grap-btn" type="button"
					onclick="query('<%=request.getContextPath()%>/role/page.html');"
					value="查询"/>
				<input class="grap-btn grap-btn-fr"
						type="button" data-toggle="modal" data-target="#tjdy" value="添加角色"
						onclick="toAddRole()"/>
			</div>
			<%@ include file="/WEB-INF/views/pageSize.jsp"%>
		</form>
		<div id="page_data"></div>

		<!-- 加载初始数据 -->
		<script type="text/javascript">
 					 var url = '<%=request.getContextPath()%>/role/page.html';
			$("#page_data").load(url);
		</script>

		<!-- 模态框（Modal） start -->
		<div class="modal fade" id="tjdy" tabindex="-1" role="dialog"
			aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">添加角色</h4>
					</div>
					<div class="model-box" id="modal_show"></div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="tjtree" tabindex="-1" role="dialog"
			aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog" style="width:500px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">角色授权</h4>
					</div>
					<div class="model-box" id="modal_tjtree"></div>
				</div>
			</div>
		</div>

	</div>
	</section>


</body>
</html>