<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">


function editAdvertise(id){
	  var url= '<%=request.getContextPath()%>/advertise/toEditAdvertise.html?id='+id;
		//加载模态框数据
	  $("#modal_show").load(url);
	
}

function audit(id,status){
		layer.confirm('是否进行该操作？', {
		  btn: ['确定','取消'] //按钮
		}, 
		function(){
			$.ajax({ 
      		url: '<%=request.getContextPath()%>/advertise/auditAdv.html', 
      		context: document.body, 
      		async: false,
      		data:{'auditstatus':status,'id':id},
      		success: function(data){
      			if(data.success){
      				query('<%=request.getContextPath()%>/advertise/pageAuditPayAdvert.html');
      				layer.closeAll();
      			}else{
      				layer.msg(data.message,{icon:5});
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
		        <span>您当前所在的位置：</span><a  href="#" title="">广告管理</a>/<i>广告审核</i>
		    </section>
	        <section class="main-cont">
	 			<div class="article">
		 			<form id="query_from">
			 			 <div class="arcle-grap">
			 			 	<input class="grap-txt" type="text" name="advname" placeholder="广告名称"/>
			 			 	<input class="grap-txt" type="text" name="mername" placeholder="商家名称"/>
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
					        <select class="grap-sel" name="advplace">
				 			 	<option value="">广告位置</option>
				 			 	<option value="1">支付页面</option>
				 			 	<option value="2">支付完成页面</option>
			 			 	</select>
					        <input class="grap-btn" type="button" onclick="query('<%=request.getContextPath()%>/advertise/pageAuditPayAdvert.html');"  value="查询"/>
			       		</div>
						<%@ include file="/WEB-INF/views/pageSize.jsp"%>				 	
					</form>
					<div id="page_data">
			       
					</div>	 
					<!-- 加载初始数据 -->
					<script type="text/javascript">
	 					 var url = '<%=request.getContextPath()%>/advertise/pageAuditPayAdvert.html';
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