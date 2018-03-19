<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="<%=request.getContextPath()%>/man/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/man/js/bootstrap/js/bootstrap.js"></script>

<link href="<%=request.getContextPath()%>/man/js/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>

<script src="<%=request.getContextPath()%>/man/js/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/man/js/fileinput/js/locales/zh.js" type="text/javascript"></script>
      
        <section class="navigation">
          <span>您当前所在的位置：</span><a  href="#" title="">充值套餐管理</a>/<i>修改充值套餐</i>
        </section>
        <section class="main-cont">
        <div class="article">
              <form class="form-add" id="formadd" action=""   method="post" enctype="multipart/form-data">
                  <input class="form-txt" type="hidden" name="id" value="${rechargePlan.id}">

              <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>套餐名称：</label>
                  <input class="form-txt form-adv-txt" type="text" name="planName" value="${rechargePlan.planName}"
                         style="width: 18%;" datatype="*,uniqueUpdateRechargePlanName" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的套餐名称已存在">
                  <span class="form-yz Validform_checktip"></span>
              </div>



              <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>充值金额：</label>
                  <input class="form-txt form-adv-txt" type="text" name="refillAmount" value="${rechargePlan.refillAmount}"
                         style="width: 18%;" datatype="money" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的充值金额格式不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>赠送金额：</label>
                  <input class="form-txt form-adv-txt" type="text" name="giftAmount" value="${rechargePlan.giftAmount}"
                         style="width: 18%;" datatype="money" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的赠送金额格式不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>

              <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>到账金额：</label>
                  <input class="form-txt form-adv-txt" type="text" name="actualAmount" value="${rechargePlan.actualAmount}"
                         style="width: 18%;" datatype="money" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的到账金额格式不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>
                  <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>售价：</label>
                      <input class="form-txt form-adv-txt" type="text" name="price" value="${rechargePlan.price}"
                             style="width: 18%;" datatype="money" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的售价格式不对">
                      <span class="form-yz Validform_checktip"></span>
                  </div>

				<div class="form-group">

                    <label class="form-lal">产品图片：</label>
                    <img id="previewImg" style="z-index:1;min-width:100px;min-height:100px;max-width:500px;max-height:400px;"
                         src="<%=request.getContextPath()%>/${rechargePlan.planImg}" >
                    <div style="display:none;">
                        <input id="planImgfile" name="planImgfile"   type="file"   class="file" showPreview="false" showCaption="true" dropZoneEnabled="false" >
                    </div>
					<span class="form-yz Validform_checktip"></span> 
					<input type="hidden" id="planImg" name="planImg"  value="${rechargePlan.planImg}"  />
				</div>
                  <div class="form-group">
                      <label class="form-lal">描述：</label>

                      <textarea class="form-cont" name="memo" value="${rechargePlan.memo}" rows="4"
                                style="width: 18%;" >${rechargePlan.memo}</textarea>
                      <span class="form-yz Validform_checktip"></span>
                  </div>

             
             
             
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存"/>
                      <input class="form-btn" type="button" name="" value="返回"  onclick="myClose()"/>
              </div>
              
          </form>
          </div>
        </section>


   
   <script type="text/javascript">
       function loadImg( file){
           //获取文件
          // var file = $(".form-add").find("input")[0].files[0];

           //创建读取文件的对象
           var reader = new FileReader();

           //创建文件读取相关的变量
           var imgFile;

           //为文件读取成功设置事件
           reader.onload=function(e) {
               //alert('文件读取完成');
               imgFile = e.target.result;
               console.log(imgFile);
               $("#previewImg").attr('src', imgFile);
           };

           //正式读取文件
           reader.readAsDataURL(file);
       }

       //表单验证
	      $(function(){
              f_initFileInput();
              $('#previewImg').click(function () {
                  $("#planImgfile").click();
              });
              $("#planImgfile").on("filebatchselected", function(event, files) {
                  $("#planImg").val( $("#planImgfile").val());
                  loadImg(files[0]);
              });

		    $(".form-add").Validform({tiptype:3,
                datatype:{
                    "uniqueUpdateRechargePlanName":function(gets,obj,curform,regxp){
                        /*参数gets是获取到的表单元素值，
                         obj为当前表单元素，
                         curform为当前验证的表单，
                         regxp为内置的一些正则表达式的引用。*/
                        var falg = false;
                        $.ajax({
                            url: '<%=request.getContextPath()%>/rechargePlan/uniqueUpdateRechargePlanName.html?rechargePlanName='+gets+'&oldRechargePlanName=${rechargePlan.planName}',
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
                 "busLicenceNo":/^.{15,18}$/
                },
		    	ajaxPost:true,
                beforeSubmit:function(curform){
                    //alert("1");
                    f_add();
                    //alert("2");
                    return false;
                },
				callback:function(data){
                    //alert("1: "+data.toString());
                    return false;

				}

		    });
		  });


	      function myClose(){
		 		changeRightMenu('<%=request.getContextPath()%>/rechargePlan/list.html');
		 	}
            function f_add(){
                $.ajax({
                    url: "<%=request.getContextPath()%>/rechargePlan/editRechargePlan.html",
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
                        //alert("f_addrechargePlan: "+data.success);
                        if (data.success) {
                            $("#bodyRight").empty();
                            $("#bodyRight").load("<%=request.getContextPath()%>/rechargePlan/toEditRechargePlan.html?id=${rechargePlan.id}");
                            layer.msg('修改成功！', {
                                icon : 1
                            });
                        } else {
                            layer.msg('修改失败！', {
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

                        allowedFileExtensions:  ['jpg', 'gif', 'png', 'jpeg', 'bmp'],//接收的文件后缀
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
                    }).on("filebatchselected", function(event, files) {
                        //alert(files);
                        //$(this).fileinput("upload");
                    });
                });
            }
</script>