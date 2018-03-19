<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<script type="text/javascript"	src="<%=request.getContextPath()%>/man/js/ajaxfileupload.js"></script>
<script type="text/javascript">


function editAdvertise(id){
	  var url= '<%=request.getContextPath()%>/advertise/toEditAdvertise.html?id='+id;
		//加载模态框数据
	  $("#modal_show").load(url);
	
}

function revokeAdvertise(id){
		layer.confirm('是否撤销？', {
		  btn: ['确定','取消'] //按钮
		}, 
		function(){
			$.ajax({ 
      		url: '<%=request.getContextPath()%>/advertise/revoke.html?id='+id, 
      		context: document.body, 
      		async: false,
      		success: function(data){
      			if(data.success){
      				query('<%=request.getContextPath()%>/advertise/pagePayAdvert.html');
      				layer.closeAll();
      			}else{
      				layer.msg('失败！',{icon:5});
      			}
      		}
      	});
		}
	);
}


function pushadv(id,push){
	layer.confirm('是否执行该操作？', {
	  btn: ['确定','取消'] //按钮
	}, 
	function(){
		$.ajax({ 
  		url: '<%=request.getContextPath()%>/advertise/auditAdv.html', 
  		context: document.body, 
  		async: false,
  		data:{"id":id,"pushstatus":push},
  		success: function(data){
  			if(data.success){
  				query('<%=request.getContextPath()%>/advertise/pageRecord.html');
  				layer.closeAll();
  			}else{
  				layer.msg(data.message,{icon:5});
  			}
  		}
  	});
	}
);
}
function deleteAdv(id){
	layer.confirm('是否删除该广告？', {
	  btn: ['确定','取消'] //按钮
	}, 
	function(){
		$.ajax({ 
  		url: '<%=request.getContextPath()%>/advertise/deleteAdv.html?id='+id, 
  		context: document.body, 
  		async: false,
  		success: function(data){
  			if(data.success){
  				query('<%=request.getContextPath()%>/advertise/pageRecord.html');
  				layer.closeAll();
  			}else{
  				layer.msg('失败！',{icon:5});
  			}
  		}
  	});
	}
);
}

function addAdv(){
	var url = "../advertise/toAddPayAdv.html";
	$("#modal_show").load(url);
	$("#tjdy").modal("show");
}

function edit(orderno){
	var url= '<%=request.getContextPath()%>/advertise/toAddPayAdv.html?id='+orderno;
	$("#modal_show").load(url);
}

</script>

</head>
<body>
		    <section class="navigation">
		        <span>您当前所在的位置：</span><a  href="#" title="">广告管理</a>/<i>支付广告</i>
		    </section>
	        <section class="main-cont">
	 			<div class="article">
		 			<form id="query_from">
			 			 <div class="arcle-grap">
			 			 	<input type="text" class="laydate-icon grap-txt" id="datestart"  name="starttime"  placeholder="开始时间"/>
					        <input type="text" class="laydate-icon grap-txt" id="dateend" name="endtime" placeholder="结束时间"/>
					        <script type="text/javascript">
					                  var start = {
					                      elem: '#datestart',
					                      format: 'YYYY-MM-DD hh:mm:ss',
					                      min: '1900-01-01 00:00:00', //设定最小日期为当前日期
// 					                      max: laydate.now(), //最大日期
					                      istime: true,
					                      istoday: false,
					                      choose: function(datas){
					                           end.min = datas; //开始日选好后，重置结束日的最小日期
					                           end.start = datas //将结束日的初始值设定为开始日
					                      }
					                  };
					                  var end = {
					                      elem: '#dateend',
					                      format: 'YYYY-MM-DD hh:mm:ss',
					                      min: '1900-01-01 00:00:00',
// 					                      max: laydate.now(),
					                      istime: true,
					                      istoday: true,
					                      choose: function(datas){
					                          start.max = datas; //结束日选好后，重置开始日的最大日期
					                      }
					                  };
					                  laydate(start);
					                  laydate(end);

					       </script>
					       <br/>
					       	<input class="grap-txt" type="text" name="createname" placeholder="发布者"/>
			 			 	<input class="grap-txt" type="text" name="mername" placeholder="商家名称"/>
					       	<input class="grap-txt" type="text" name="advname" placeholder="广告名称"/>
					       	<input class="grap-txt" type="text" name="createor" placeholder="业务员名称"/>
					        <select class="grap-sel" name="advplace">
				 			 	<option value="">广告位置</option>
				 			 	<option value="1">支付页面</option>
				 			 	<option value="2">支付完成页面</option>
			 			 	</select>
					        <input class="grap-btn" type="button" onclick="query('<%=request.getContextPath()%>/advertise/pageRecord.html');"  value="查询"/>
					        
					       <%--  <t:buttonOut url="/advertise/toAddAdv.html">
						        <input class="grap-btn grap-btn-fr" type="button"  value="添加广告" onclick="addAdv()"/>
			       		    </t:buttonOut> --%>
			       		</div>
						<%@ include file="/WEB-INF/views/pageSize.jsp"%>				 	
					</form>
					<div id="page_data">
			       
					</div>	 
					<!-- 加载初始数据 -->
					<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/advertise/pageRecord.html';
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
						<h4 class="modal-title" id="myModalLabel">编辑广告</h4>
					</div>
					<div class="model-box" id="modal_show"></div>

				</div>
			</div>
		</div>
		<!-- 模态框（Modal） end -->
					
		 		</div>
			 </section>
 		
</body>
</html>