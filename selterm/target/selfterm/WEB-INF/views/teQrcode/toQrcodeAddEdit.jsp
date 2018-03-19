
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	  <script type="text/javascript">
	  	//表单验证
      $(function(){
	    $(".form-edit").Validform({tiptype:3,
	    	datatype:{
	    	},
	    	ajaxPost:true,
			callback:function(data){
				if(data.success){
					query('<%=request.getContextPath()%>/teQrcode/list.html');
					$("#tjdy").modal('hide');
				}else{
					 layer.msg(data.message,{icon:5});
				}
			}

	    });
	  });

    	function myClose(){
    		$("#tjdy").modal('hide');
    	}

//   $().ready(function(){
// 	var fixat = ${fixation};
// 	var payT = ${payType}

// 	if(fixat==2){
// 		var fmony=$(".form-money");
// 		fmony.toggle();
// 	}
// 	$("#paytype").val(payT);
// 	$("#selectsad").val(fixat);
//   })



	  </script>


</head>
<body>


	<form class="form-edit"  method="post" accept-charset="utf-8" action="<%=request.getContextPath()%>/teQrcode/addUpdate.html">
                      <input class="form-txt" type="hidden" name="id" value="${teQrcode.id}"    />
	         <c:if test="${not empty teQrcode.qrcodeadress }">
	         <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>二维码:</label>
<%--                       <input class="form-txt" type="text" name="qrcodeadress" value="${teQrcode.qrcodeadress}"     /> --%>
                      <img src="${teQrcode.qrcodeadress}" alt="" style="margin:0;padding:0"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
	         </c:if>

              <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>支付方式：</label>
                      <select name="paytype" id="paytype" class="form-txt">
                        <option  value="3">微信/支付宝合一 </option>
<!--                         <option  value="1">支付宝 </option> -->
<!--                         <option  value="2">微信</option> -->
					  </select>
                      <span class="form-yz Validform_checktip"></span>
              </div>

               <div class="form-group">
                       <label class="form-lal">是否固定：</label>
                       <select name="fixation"   class="form-txt" >
                         <option  value="2" selected>否 </option>
                         <%--<option  value="1">是 </option>--%>
 					</select>
               </div>
	        <%--  <div class="form-group form-money">
                       <label class="form-lal"><i class="text-danger">*</i>金额:</label>
                       <input class="form-txt" id="teMoney" type="text" name="mony" value="${teQrcode.mony}"  disabled />固定时金额必填

              </div>--%>
<!-- 	          <div class="form-group"> -->
<!--                       <label class="form-lal"><i class="text-danger">*</i>商户名称:</label> -->
<%--                       <input class="form-txt" type="text" name="storename" value="${teQrcode.storename}"  maxlength="20" datatype="*"  sucmsg="&nbsp;"   required="required" disabled="disabled"> --%>
<!--                       <span class="form-yz Validform_checktip"></span> -->
<!--               </div> -->
<!--               <div class="form-group"> -->
<!--                       <label class="form-lal"><i class="text-danger">*</i>门店名称:</label> -->
<%--                       <input class="form-txt" type="text" name="name" value="${teQrcode.name}"  maxlength="20" datatype="*"  sucmsg="&nbsp;"   required="required" disabled="disabled"> --%>
<!--                       <span class="form-yz Validform_checktip"></span> -->
<!--               </div> -->

              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存"/>
                      <input class="form-btn" type="button" name="" value="关闭" onclick="myClose()"/>
              </div>
          </form>


</body>
</html>