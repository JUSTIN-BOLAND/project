<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 

      
        <section class="navigation">
          <span>您当前所在的位置：</span><a  href="#" title="">广告管理</a>/<i>添加广告</i>
        </section>
        <section class="main-cont">
        <div class="article">
              <form class="form-add" action="<%=request.getContextPath()%>/advertise/addAdv.html"  accept-charset="utf-8" method="post" enctype="multipart/form-data">
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>广告名称：</label>
                      <input class="form-txt form-adv-txt" type="text" name="name" value="" datatype="*" sucmsg="&nbsp;" nullmsg="不能为空">
                       <span class="form-yz Validform_checktip"></span>
              </div>
              
	          <div class="form-group">
				  <label class="form-lal">&nbsp;</label>
				<div class="form-item form-item-wd">
					<img src="<%=request.getContextPath()%>/man/images/upload.png" /> 
					<input class="form-file" type="file" name="file" id="image1"></input>
					<span class="form-yz Validform_checktip"></span>
				    <input type="hidden" name="image1" />
				</div>
				 
				<div class="form-item form-item-wd">
					<img src="<%=request.getContextPath()%>/man/images/upload.png">
				    <input class="form-file" type="file" name="file" value="" id="image2">
					<span class="form-yz Validform_checktip"></span> 
					<input type="hidden" name="image2" />
				</div>
				
				<div class="form-item form-item-wd">
					<img src="<%=request.getContextPath()%>/man/images/upload.png">
				    <input class="form-file" type="file" name="file" value="" id="image3">
					<span class="form-yz Validform_checktip"></span> 
					<input type="hidden" name="image3" />
				</div>
			</div>
             
             
             
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存"/>
                      <input class="form-btn" type="button" name="" value="返回"  onclick="myClose()"/>
              </div>
              
          </form>
          </div>
        </section>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/js/kindeditor/themes/default/default.css">
   <script type="text/javascript" src="<%=request.getContextPath()%>/man/js/kindeditor/kindeditor-all-min.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/man/js/kindeditor/lang/zh-CN.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/man/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function(){
		   $(".form-file").on("change", function () {
			   
			   
			   var $obj = $(this);
			   console.info($obj)
			   var id = $(this).attr("id");
			  
		        var imgFile=$(this)[0];
		        var filextension=imgFile.value.substring(imgFile.value.lastIndexOf("."),imgFile.value.length);
		    	filextension=filextension.toLowerCase();
		          if ((filextension!='.jpg')&&(filextension!='.gif')&&(filextension!='.jpeg')&&(filextension!='.png')&&(filextension!='.bmp'))
		          {
		                alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !");
		                imgFile.focus();
		          }
		          else
		          {
		        	  $.ajaxFileUpload({  
		 	  	            url:"<%=request.getContextPath()%>/advertise/filesUpload.html",
		 	  	            secureuri:false,  
		 	  	            fileElementId:id,//file标签的id  
		 	  	            dataType: 'json',//返回数据的类型  
		 	  	            //data:{name:'logan'},//一同上传的数据  
		 	  	            success: function (data, status) {  
		 	  	            	$("#"+id).siblings('img')
		 	  	            	$("#"+id).siblings('img').attr('src',data);
		 	  	            	$("#"+id).siblings('input[type=hidden]').val(data);
		 	  	            },
		 	  	            error: function (data, status, e) {  
		 	  	            	alert("文件上传失败!提示");   
		 	  	            }  
		 	  	        });
		          }
		      });        
	      });
</script>
   
   <script type="text/javascript">
	 	
   
	    	//表单验证
	      $(function(){
		    $(".form-add").Validform({tiptype:3,
		    	ajaxPost:true,
				callback:function(data){
					if(data.success){
						changeRightMenu('<%=request.getContextPath()%>/advertise/list.html');
					}else{
						 layer.msg('添加失败！',{icon:5});
					}
				}

		    });
		  });
   
      
	      function myClose(){
		 		changeRightMenu('<%=request.getContextPath()%>/advertise/list.html');
		 	}
		 	
