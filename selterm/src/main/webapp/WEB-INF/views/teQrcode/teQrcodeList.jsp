<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
  <script type="text/javascript">
			function deleteSalesman(id){
         		layer.confirm('是否删除？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		}, 
  	    		function(){
  	    		
  	    			$.ajax({ 
  	    				url: '<%=request.getContextPath()%>/teQrcode/teQrcodeDelete.html?id='+id, 
  	            		context: document.body, 
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/teQrcode/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('删除失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}
		     
		      function toAddEditSalesman(id){
		    	  var url= '<%=request.getContextPath()%>/teQrcode/addEdidteQrcode.html?id='+id;
		    	 var title = "";
		    	  if(id==''){
		    		  title = "添加二维码";
		    	  }else{
		    		  title = "编辑二维码";
		    	  }
		    	  $("#myModalLabel").html(title);
					//加载模态框数据
				  $("#modal_show").load(url);
			  }
		      
		      
		      function deletebatch(){
	         		layer.confirm('是否删除？', {
	  	    		  btn: ['确定','取消'] //按钮
	  	    		}, 
	  	    		function(){
	  	    			var str="";
	  	              $("input[name='ids']:checkbox").each(function(index){ 
	  	                  if($(this).is(':checked')){
	  	                      str += $(this).val()+","
	  	                  }
	  	              });
	  	            $.ajax({ 
  	    				url: '<%=request.getContextPath()%>/teQrcode/batchDelete.html?ids='+str, 
  	            		context: document.body, 
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/teQrcode/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg(data.message,{icon:5});
  	            			}
  	            		}
  	            	});
	  	    		}
	  	    	);
				}

			function f_Close(){
				$("#bindSaler").modal('hide');
			}
			function f_showBindSaler(v_qrCodeId,v_storeId){

				var html = "<option value='0'>请选择</option>";
				$.ajax({
					url: '<%=request.getContextPath()%>/teQrcode/getBindClerks.html?storeId='+v_storeId,
					context: document.body,
					async: false,
					dataType:'json',
					success: function(date){
						$("#saler").empty();
						var data = eval(date);
						for(var i = 0;i<data.length;i++){
							html += '<option value="'+data[i].id+'">' +data[i].name+ '</option>';
						}
						$("#saler").append(html);
					}
				});

				$("#qrCodeId").val(v_qrCodeId)
				$("#bindSaler").modal('show');
				//alert($("qrCodeId").val()+" -> "+v_qrCodeId);
			}
		    function f_bindSaler(){
				var v_qrCodeId = $("#qrCodeId").val();
				var v_salerId = $("#saler").val();
				//alert(v_qrCodeId+" : "+v_salerId);
				if(v_qrCodeId.length == 0 ){
					layer.msg("二维码编号不能为空",{icon:1});
					return false;
				}
				if(v_salerId.length == 0 || v_salerId=="0"){
					layer.msg("请选择收银员",{icon:1});
					return false;
				}
				//alert(v_qrCodeId+" : "+v_salerId);
				$.ajax({
					url: '<%=request.getContextPath()%>/teQrcode/bindSaler.html?qrCodeId='+v_qrCodeId+"&salerId="+v_salerId,
					context: document.body,
					async: false,
					success: function(data){
						if(data.success){
							layer.msg("绑定成功",{icon:1});
							query('<%=request.getContextPath()%>/teQrcode/page.html');
							layer.closeAll();
						}else{
							layer.msg(data.message,{icon:5});
						}
					}
				});
			}
		</script>
</head>
<body>
  <section class="navigation">
		        <span>您当前所在的位置：</span><a  href="#" title="">二维码管理</a>/<i>二维码列表</i>
		    </section>
	        <section class="main-cont">
	 			<div class="article">
		 			<form id="query_from">
			 			 <div class="arcle-grap">

			 			 	<input class="grap-txt" type="text" name="name" placeholder="商户名称"/>
			 			 	<input class="grap-txt" type="text" name="storename" placeholder="门店名称"/>
							 <input class="grap-txt" type="text" name="saler" placeholder="收银员名称"/>
			 			 	<select class="grap-sel" name="mertype">
							<option value="">商户类型</option>
							<option value="1">官方</option>
							<option value="3">银行</option>
						</select>
					        <input class="grap-btn" type="button" onclick="query('<%=request.getContextPath()%>/teQrcode/page.html');"  value="查询"/>
					        <t:buttonOut url="/teQrcode/addTeQrcode.html">
						        <input class="grap-btn grap-btn-fr" type="button" data-toggle="modal" data-target="#tjdy" value="添加二维码" onclick="toAddEditSalesman('')"/>
					        </t:buttonOut>
			       		</div>
						<%@ include file="/WEB-INF/views/pageSize.jsp"%>				 	
					</form>
					<div id="page_data">
			       
					</div>	 
					<!-- 加载初始数据 -->
					<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/teQrcode/page.html';
		    			 $("#page_data").load(url);
					</script>
					<!-- 模态框（Modal） start -->
					 <div  class="modal fade" id="tjdy" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
					 	<div class="modal-dialog modal-lg">
					        <div class="modal-content">
					          <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					            <h4 class="modal-title" id="myModalLabel">添加二维码</h4>
					          </div>
					          <div  class="model-box" id="modal_show">
					          
					          </div>
					         
					        </div>
					      </div>
					 </div> 
					 <!-- 模态框（Modal） end -->
					<!-- 添加门店 -->
					<div class="modal fade" id="bindSaler" tabindex="-1" role="dialog"
						 aria-labelledby="myLargeModalLabel" >
						<div class="modal-dialog modal-lg" style="width:380px;">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">绑定收银员</h4>
								</div>
								<div class="model-box">
									<form class="form-add"
										  action="<%=request.getContextPath()%>/teQrcode/bindSaler.html"
										  method="post" accept-charset="utf-8">
										<input type="hidden" id="qrCodeId" name="qrCodeId">
										<div class="form-group">
											<label class="form-lal" style="width:60px;""><i class="text-danger">*</i>收银员:</label>
											<select id="saler" name="saler"	class="form-sel" style="width:190px;" datatype="*" sucmsg="&nbsp;" nullmsg="不能为空">
												<option value="0">选择收银员</option>
												<%--<c:forEach items="${salers }" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>--%>
											</select>
											<span class="form-yz Validform_checktip"></span>
										</div>


										<div class="form-group">
											<label class="form-lal"  style="width:0px;">&nbsp;</label>
											<input class="form-btn"   type="button" name="" onclick="return f_bindSaler();" value="绑定" />
											<input class="form-btn"   type="button" name="" value="关闭" onclick="f_Close()"/>
										</div>

									</form>
								</div>
							</div>
						</div>
					</div>

				</div>
			 </section>
 		
</body>
</html>