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
	function f_initFileInput() {


		$('input[type=file]').each(function(){
			//alert($(this).attr("id")+" : "+$(this).maxFileCount);
			var maxFileCount = 1 ;
			var showPreview=true;
			var showCaption=true;
			var dropZoneEnabled=false;
			var showUpload = false;
			if($(this).attr("maxFileCount")){
				if($(this).attr("maxFileCount")=="true") maxFileCount = true;
				else maxFileCount =false;
			}
			if($(this).attr("showPreview")){
				if( $(this).attr("showPreview") == "true") showPreview = true;
				else  showPreview = false;
			}
			if($(this).attr("showCaption")){
				if($(this).attr("showCaption")=="true") showCaption = true;
				else  showCaption = false;
			}
			if($(this).attr("dropZoneEnabled")){
				if($(this).attr("dropZoneEnabled") =="true") dropZoneEnabled = true;
				else dropZoneEnabled = false;
			}
			if($(this).attr("showUpload")){
				if($(this).attr("showUpload") =="true") showUpload = true;
				else showUpload = false;
			}
			//alert("1 : "+$(this).attr("showPreview")+" : "+$(this).attr("showCaption")+" : "+$(this).attr("dropZoneEnabled"));
			//alert("2 : "+showPreview+" : "+showCaption+" : "+dropZoneEnabled);
			$(this).fileinput({
				language: 'zh', //设置语言
				uploadUrl:  $(this).attr("uploadUrl"), //上传的地址
				allowedFileExtensions: ['p12'],//接收的文件后缀
				//allowedFileExtensions:  ['jpg', 'gif', 'png', 'jpeg', 'bmp'],//接收的文件后缀
				uploadAsync: false, //默认异步上传
				showUpload: showUpload, //是否显示上传按钮
				showRemove : true, //显示移除按钮
				showPreview : showPreview, //是否显示预览
				showCaption:  showCaption,//是否显示标题
				browseClass: "btn btn-primary", //按钮样式
				dropZoneEnabled: dropZoneEnabled,//是否显示拖拽区域
				previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
				maxFileCount: maxFileCount, //表示允许同时上传的最大文件个数
				enctype: 'multipart/form-data',
				validateInitialCount:true,
				previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
				msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
			}).on("fileuploaded", function (e, data) {
				var res = data.response;
				if (res.status > 0) {
					alert('上传成功');
					alert(res.path);
				}
				else {
					alert('上传失败')
				}
			});
		});
	}

	//表单验证
	$(function() {

		f_initFileInput();
		var v_certPath = "${wxFacilor.certpath}";
		//alert(v_certPath);
		$(".file-caption-name").html(v_certPath);
	});

	function f_submit(){
		var v_check = true;
		$("form input").each(function(){
			var v_value = $(this).val();
			//alert($(this).attr("name") +" :[ "+ v_value+" : "+v_value.length +"] -> "+ $(this).prev().html());
			if( v_value.length == 0 ){
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
		var v_aliSecret = $("textarea[name='aliSecret']").val();
		var v_aliKey = $("textarea[name='aliKey']").val();
		if( v_aliSecret.length == 0 ){
			layer.msg('支付宝私钥不能为空！', {
				icon : 5
			});
			$("textarea[name='aliSecret']").focus();
			return false;
		}

		if( v_aliKey.length == 0 ){
			layer.msg('支付宝公钥不能为空！', {
				icon : 5
			});
			$("textarea[name='aliKey']").focus();
			return false;
		}
		//$(".form-add").submit();
		f_upload();
		$("#submitBtn").attr("disabled","true");
	}
	function f_upload(){
		$.ajax({
			url: "<%=request.getContextPath()%>/payConfig/upload.html",
			type: 'POST',
			cache: false,
			data: new FormData($('#formadd')[0]),
			processData: false,
			contentType: false,
			dataType:"json",
			beforeSend: function(){
				uploading = true;
			},
			success : function(data) {
				if (data.success) {
					$("#bodyRight").empty();
					$("#bodyRight").load("<%=request.getContextPath()%>/payConfig/showPayConfig.html");
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
</script>

</head>
<body>

	<section class="navigation"> <span>您当前所在的位置：</span>
	<a href="#" title="">系统管理</a>/<i>支付设置</i> </section>
	<section class="main-cont">
	<form class="form-add"  id="formadd" method="post"  action="<%=request.getContextPath()%>/payConfig/upload.html"   enctype="multipart/form-data">


		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>微信appid：</label>
			<input class="form-txt " type="text" name=wxAppId value="${wxFacilor.appid}" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空"/>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>微信商户号：</label>
			<input class="form-txt" type="text" name="wxMchId" value="${wxFacilor.mchid}" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空" />
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>微信appsec：</label>
			<input class="form-txt " type="text" name="wxSecret" value="${wxFacilor.secret}" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空"/>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>微信API密钥：</label>
			<input class="form-txt" type="text" name="wxKey" value="${wxFacilor.key}" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空" />
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal" id="cfTitle"><i class="text-danger">*</i>微信密钥文件：</label>
            <div style="width:510px;height:30px;float:left;">

			     <input id="certfile" name="certfile"  type="file"  uploadUrl="<%=request.getContextPath()%>/payConfig/upload.html" class="file" showPreview="false" showCaption="true" dropZoneEnabled="false">


			</div>
			<span class="form-yz Validform_checktip"></span>
		</div>
		<hr />
		<br />
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>支付宝appId:</label>
			<input class="form-txt" type="text" name="aliAppId" value="${aliFacilor.appid}" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空" />
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>支付宝商户号：</label>
			<input class="form-txt " type="text" name="aliMchId" value="${aliFacilor.mchid}" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空" />
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>支付宝私钥:</label>
			<textarea class="form-cont" name="aliSecret"  rows="4">${aliFacilor.secret}</textarea>
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>支付宝公钥:</label>
			<textarea class="form-cont" name="aliKey"  rows="4">${aliFacilor.key}</textarea>
			<span class="form-yz Validform_checktip"></span>
		</div>
		<hr />
		<br />
		<div class="form-group">
			<label class="form-lal">IP：</label>
			${authIp}
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal">授权域名：</label>
			${authDoamin}
			<span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal">授权目录：</label>
			${authPath}
			<span class="form-yz Validform_checktip"></span>
		</div>
     <style>
     .form-disb[type='submit']{  background:#ccc;     cursor: not-allowed;}
     </style>
		<div class="form-group">
			<label class="form-lal">&nbsp;</label>

				<input class="form-btn" id="submitBtn" type="button" onclick="f_submit();" name="" value="保存" />


		</div>
	</form>
	</section>
</body>
</html>