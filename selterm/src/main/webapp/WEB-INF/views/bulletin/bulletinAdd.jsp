<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

         
        <section class="main-cont">
              <form class="form-add" action="<%=request.getContextPath()%>/bulletin/addBuller.html"  accept-charset="utf-8" method="post" enctype="multipart/form-data">
              	     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/js/kindeditor/themes/default/default.css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/man/js/kindeditor/kindeditor-all-min.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/man/js/kindeditor/lang/zh-CN.js"></script>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>公告名称：</label>
                      <input class="form-txt form-adv-txt" type="text" name="title" value="" datatype="*" sucmsg="&nbsp;" nullmsg="不能为空">
                       <span class="form-yz Validform_checktip"></span>
              </div>
              
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>公告内容：</label>
<!--                      <textarea name="content" style="width:200px;height:400px;visibility:hidden;"></textarea> -->
                       <textarea  class="form-cont" name="content"  datatype="*"  sucmsg="&nbsp;"   required="required" rows="5" ></textarea>
              </div>
<!--              <div class="form-group"> -->
<!--                 <label class="form-lal">公告内容：</label><textarea id ="content" name="content" style="width:60%;height:400px;visibility:hidden;"></textarea> -->
<!--              </div>  -->
             
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存">
                      <input class="form-btn" type="button" name="" value="关闭" onclick="myClose()">
              </div>
          </form>
        </section>
    <script>
      var editor;
      KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
          allowFileManager : true
        });
      });
    </script>
   <script type="text/javascript">
  
	      $(function(){
	 

		    $(".form-add").Validform({tiptype:3,
		    	ajaxPost:true,
				callback:function(data){
					if(data.success){
						query('<%=request.getContextPath()%>/bulletin/list.html');
						$("#tjdy").modal('hide');
					}else{
						 layer.msg('添加失败！',{icon:5});
					}
				}

		    });
		  });
	      
	      function myClose(){
	    	  $("#tjdy").modal('hide');
	      }
	</script>
	      
