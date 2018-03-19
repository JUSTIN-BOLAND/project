<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tags/page"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="<%=request.getContextPath()%>/man/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/man/js/bootstrap/js/bootstrap.js"></script>

	<link href="<%=request.getContextPath()%>/man/js/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>

	<script src="<%=request.getContextPath()%>/man/js/fileinput/js/fileinput.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/man/js/fileinput/js/locales/zh.js" type="text/javascript"></script>
<script type="text/javascript">
	function f_save(){
		$.ajax({
			url: "<%=request.getContextPath()%>/dealer/editConfigPayParam.html",
			type: 'POST',
			cache: false,
			data: new FormData($('#formadd')[0]),
			processData: false,
			contentType: false,
			dataType:"json",
			beforeSend: function(){

			},
			success : function(data) {
				if (data.success) {
					$("#bodyRight").empty();
					$("#bodyRight").load("<%=request.getContextPath()%>/dealer/showConfigPayParam.html");
					layer.msg('设置成功！', {
						icon : 1
					});
				} else {
					layer.msg('设置失败！', {
						icon : 5
					});
				}
				//uploading = false;
			}
		});
	}
	//表单验证
	$(function() {
		var v_wxMchId="${dealerPay.wxMchId}";
		var v_userType = "${userType}";
		if(v_wxMchId.length > 0 || v_userType!="1"){
			$("#submitBtn").attr("disabled","true");
		}
	});

	function f_submit(){
		var v_userType = "${userType}";
		if(v_userType!="1") {
			layer.msg('请用经销商账号设置！', {
				icon : 1
			});
			return false;
		}
		var v_check = true;
		$("form input").each(function(){
			var v_value = $(this).val();
			//alert($(this).attr("name") +" :[ "+ v_value+" : "+v_value.length +"] -> "+ $(this).prev().html());
			if( v_value.length == 0 && $(this).attr("name") =="wxMchId"){
               var v_title = $(this).prev().text();
				if($(this).attr("name") =="certfile")  v_title = $("#cfTitle").text();
				if(v_title.length > 0) v_title = v_title.replace("*","");
				//alert("v_title="+v_title);
				layer.msg(v_title+'不能为空！', {
					icon : 5
				});
				$(this).focus();
				v_check = false;
				return false;
			}
		});
		if(!v_check) return false;
        //alert("sdf");
		f_save();
		$("#submitBtn").attr("disabled","true");
	}

</script>
	<style type="text/css">
		input.text{text-align:center;}
	</style>
</head>
<body>

	<section class="navigation"> <span>您当前所在的位置：</span>
	<a href="#" title="">系统管理</a>/<i>支付参数设置</i> </section>
	<section class="main-cont">
	<form class="form-add"  id="formadd" method="post"  action="<%=request.getContextPath()%>/dealer/editConfigPayParam.html" >

       <input type="hidden" id="dealerId" name="dealerId" value="${dealerPay.dealerId}">

		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>微信商户号：</label>
			<input class="form-txt" type="text" name="wxMchId" value="${dealerPay.wxMchId}" style="width:35%;"
				   placeholder="请认真填写，商户号只可设置一次，不可修改"

				   datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空" />
			<span class="form-yz Validform_checktip"></span>
		</div>



		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>支付宝商户号：</label>
			<input class="form-txt " type="text" name="aliMachId" value="${dealerPay.aliMachId}" readonly style="width:35%;" datatype="*" sucmsg="&nbsp;"
				   placeholder="请用收款支付宝扫下方二维码完成授权自动获取商户号"  nullmsg="不能为空" />
			<span class="form-yz Validform_checktip"></span>

		</div>

		<img src="<%=request.getContextPath()%>${qrUrl}" style="margin-left: 18%;">
		<hr />
		<br />

     <style>
     .form-disb[type='submit']{  background:#ccc;     cursor: not-allowed;}
     </style>
		<div class="form-group">


				<input class="form-btn" id="submitBtn" style="margin-left:22%;" type="button" onclick="f_submit();" name="" value="保存" />


		</div>
	</form>
	</section>
</body>
</html>