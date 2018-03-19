<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style>

		.arcle-grap input[type=text] {
			max-width: 260px;
			width: 20%;
			min-width: 230px !important;
			height: 40px;
			margin-left: 6px !important;

			margin-right: 8px;
			border: 1px solid #999;
		}
		.arcle-grap span {
			display: inline-block;
			margin-bottom: 20px;
			width:50px;
			font-size:20px;font-weight:bold;
		}

		.articles {

			margin-left: 30%;

			text-align:center;
			width:450px;
			border: 1px solid #dfdfdf;
			padding: 30px;
		}
		.form-group span{
			float:left;
		}
	</style>
<script type="text/javascript">
	layer.config({
		path: '<%=request.getContextPath()%>/man/js/layer/',
		extend:'extend/layer.ext.js'
	});
			function f_delete(id){
         		layer.confirm('是否删除？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/card/delete.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/card/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('删除失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}



			function f_disable(id){
         		layer.confirm('是否停用？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/card/disable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/card/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}

			function f_enable(id){
         		layer.confirm('是否启用？', {
  	    		  btn: ['确定','取消'] //按钮
  	    		},
  	    		function(){
  	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/card/enable.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				query('<%=request.getContextPath()%>/card/page.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('启用失败！',{icon:5});
  	            			}
  	            		}
  	            	});
  	    		}
  	    	);
			}
			//编辑商户
			function f_toEdit(id){
		    	  changeRightMenu('../card/toEdit.html?id='+id);
			  }



			//商户详情
			function f_details(id){
		    	  changeRightMenu('../card/deale.html?id='+id);
			  }

			function f_checkAppIdExist(mechantId){
				v_result = true;
				$.ajax({
					url: '<%=request.getContextPath()%>/dealer/checkAppIdExist.html?mechantId='+mechantId,
					context: document.body,
					async: false,
					dataType:'json',
					success: function(data){
						if(data.success){
							//layer.msg('验证成功！',{icon:1});
							//layer.closeAll();

						}else{
							layer.msg('请在设置中配置 该商户的支付宝AppId！',{icon:5});
							v_result= false;
						}
					}
				});
				return v_result;
			}
	      $().ready(function(){
			  $("#exportBtn").click(function(){
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/card/export.html";
				  document.getElementById("query_from").submit();
				  document.getElementById("query_from").action="<%=request.getContextPath()%>/card/page.html";
			  });
		  });



		function passwordReset(id){
			layer.confirm('确定重置密码？', {
	    		  btn: ['确定','取消'] //按钮
	    		},
	    		function(){
	    			$.ajax({
  	            		url: '<%=request.getContextPath()%>/card/passwordReset.html?id='+id,
  	            		context: document.body,
  	            		async: false,
  	            		success: function(data){
  	            			if(data.success){
  	            				layer.msg('重置成功',{icon:1});
  	            				changeRightMenu('<%=request.getContextPath()%>/card/list.html');
  	            				layer.closeAll();
  	            			}else{
  	            				layer.msg('',{icon:5});
  	            			}
  	            		}
  	            	});
	    		});
		}




		</script>
</head>
<body>

	<section class="navigation">
	<span>您当前所在的位置：</span><a href="#" title="">一卡通管理</a>/<i>充值</i>


	</section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
			<div class="arcle-grap" style="display:none;">
				<div>

				<span style="">卡号</span><input class="grap-txt" type="text" name="cardNo" placeholder="卡号" />
					<t:buttonOut url="/card/toAdd.html">
						<input class="grap-btn" type="button" style="line-height: 0px;"
							   onclick="changeRightMenu('../card/readCard.html');"
							   value="读卡" />
					</t:buttonOut>

				<input class="grap-btn" type="button" style=""
										   onclick="query('<%=request.getContextPath()%>/card/page.html');"
										   value="查询" />


				</div>
		</div>

		</form>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
				<tr>
					<th>批号</th>
					<th>卡号</th>
					<th>姓名</th>
					<th>手机号</th>
					<th>账户余额</th>
					<th>充值账户余额</th>
					<th>业务状态</th>

				</tr>
				</thead>

				<tbody>


					<tr>
						<td height="22" align="center" valign="middle">
								${card.batchNo}
						</td>
						<td height="22" align="center" valign="middle">
								${card.cardNo}
						</td>
						<td height="22" align="center" valign="middle">
								${card.name}
						</td>
						<td height="22" align="center" valign="middle">
								${card.mobile}
						</td>
						<td height="22" align="center" valign="middle">
								${card.balance}
						</td>
					<%--	<td height="22" align="center" valign="middle">
								${card.deposit}
						</td>--%>
						<td height="22" align="center" valign="middle">
							<c:if test="${card.status == '0'}">
								启用
							</c:if>
							<c:if test="${card.status == '1'}" >
								<font color="red">
									禁用
								</font>
							</c:if>
						</td>


					</tr>

				</tbody>
			</table>

		</div>

		<div class="articles">
			<form class="form-add" id="formadd" action="" method="post">
				<input class="form-txt" type="hidden" name="id" value="${card.id}">
				<div class="form-group">
					<label class="form-lal"><i class="text-danger"></i>业务类型：</label>
					<span>
						<input type="radio" name="serviceType" value="1">&nbsp;预存
						<input type="radio" name="serviceType" checked value="2">&nbsp;充值
						</span>
				</div>
				<div class="form-group">
					<label class="form-lal"><i class="text-danger">*</i>充值金额：</label>
					<input class="form-txt form-adv-txt" type="text" name="recharge" value="" style="width:18%;" datatype="money" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的充值金额格式不对">
					<span class="form-yz Validform_checktip"></span>
				</div>
				<div class="form-group">

					<input class="form-btn" type="submit" name="" value="确定"/>
					<input class="form-btn" type="button" name="" value="取消"  onclick="myClose()"/>
				</div>
			</form>
		</div>


	</div>
	</section>
	<script type="text/javascript">

		//表单验证
		$(function(){

			$(".form-add").Validform({tiptype:3,
				datatype:{
					"uniqueDealerName":function(gets,obj,curform,regxp){
						/*参数gets是获取到的表单元素值，
						 obj为当前表单元素，
						 curform为当前验证的表单，
						 regxp为内置的一些正则表达式的引用。*/
						var falg = false;
						$.ajax({
							url: '<%=request.getContextPath()%>/card/uniqueCardName.html?cardName='+gets,
							context: document.body,
							async: false,
							timeout:5000,
							success: function(date){
								falg = date;
							}
						});
						return falg;
					},
					"userName":/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,6}$/,
					"account":/^[^\u4E00-\u9FA5\uf900-\ufa2d]{2,10}$/,
					"money"  : /^\d+(\.\d{1,2})?$/,
					"serial"  : /^\d{8}$/,
					"n2"  : /^\d{2}$/,
					"n5"  : /^\d{5}$/,
					"busLicenceNo":/^.{15,18}$/,
					idCard:function(gets,obj,curform,regxp){
						var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
						var iSum=0 ;
						var info="" ;
						var sId= gets;
						if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";
						sId=sId.replace(/x$/i,"a");
						if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";
						sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
						var d=new Date(sBirthday.replace(/-/g,"/")) ;
						if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";
						for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
						if(iSum%11!=1) return "你输入的身份证号非法";
						return true;                  }
				},
				ajaxPost:true,
				beforeSubmit:function(curform){
					//alert("1");
					f_add();
					//alert("2");
					return false;
				},
				callback:function(data){
					alert("1: "+data.toString());
					return false;

				}

			});
		});


		function myClose(){
			changeRightMenu('<%=request.getContextPath()%>/card/list.html');
		}
		function f_add(){
			$.ajax({
				url: "<%=request.getContextPath()%>/card/recharge.html",
				type: 'POST',
				cache: false,
				data: new FormData($('#formadd')[0]),
				processData: false,
				contentType: false,
				dataType:"json",
				/* beforeSend: function(){
				 uploading = true;
				 },*/
				success : function(data) {
					//alert("f_addDealer: "+data.success);
					if (data.success) {
						//$("#bodyRight").empty();
						//$("#bodyRight").load("<%=request.getContextPath()%>/card/list.html");
						layer.msg('添加成功！', {
							icon : 1
						});
					} else {
						layer.msg('添加失败！', {
							icon : 5
						});
					}
					//uploading = false;
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					// 状态码
					console.log(XMLHttpRequest.status);
					// 状态
					console.log(XMLHttpRequest.readyState);
					// 错误信息
					console.log(textStatus);
				}
			});
		}
	</script>
</body>
</html>