<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page language="java" import="java.util.*,com.lb.server.util.JDBC.JDBC,java.sql.ResultSet" %>

<%
String flag = request.getParameter("flag");
if(flag==null || flag.trim().length()==0) flag="0";
if("1".equals(flag)){
%>
  <script>alert("支付成功");</script>
<%} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>手机端</title>
		<link href="resources/css/easyui.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		 function f_add(){
		    	var v_cardId = document.getElementById("cardId").value;
		    	if(v_cardId.length ==0){
		    		alert("卡号不能为空!");
		    		return false;
		    	}
		    	
		    	if(v_cardId.length !=7){
		    		alert("卡号必须是7位数字!");
		    		return false;
		    	}
		    	var v_fee= document.getElementById("fee").value;
		    	if(v_fee.length ==0){
		    		alert("请选择充值费用!");
		    		return false;
		    	}
		    	//alert("v_cardId="+v_cardId+",v_fee="+v_fee);
		    	//document.getElementById("optType").value="1";
		    	//document.getElementById("terminalForm").action="cardAdd.jsp?optType=1&taskType=1&mobile=1&cardId="+v_cardId+"&fee="+v_fee;
		    	document.getElementById("terminalForm").submit();
		    	return true;
		    }
		 function f_select(v_value){
			 document.getElementById("fee").value=v_value;
		 }
		</script>
	<style type="text/css">  
	.worksbox{width:100%;height:100%;position:relative;}  
	.worksbox a{border:1px solid #F0F0E8;background-color:#FFF;padding:6px;display:block;}  
	.worksbox a:hover{border:1px solid #000;background-color:#333;text-decoration: none;}  
	.worksbox a span{display:none; text-align:center; font-size:12px;}  
	.worksbox a:hover span{color:#FFF;display:block;background-color:#333;width:114px;position:absolute;top:94px;left:0px;line-height:20px;}  
	//.worksbox a img{width:100px;height:100px;}  
</style>  


	</head>
	<body>
	<form id="terminalForm" method="post"  action='cardAdd.jsp?optType=1&taskType=1&mobile=1'>
	<input type="hidden" id="fee" name="fee" >
		<div style="width:100%;">
             <ul style="margin:0;padding:0;">
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
               <font size="20">卡号:</font>
               <input id="cardId" type="text" name="cardId"    style="width:93%;height:60px;font-size:60px;"  
						 onkeyup = "if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}" 
						 onafterpaste = "if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}"
						 >
              </li>
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
               <img src="images/card/WechatIMG316_01.png" style="display:block;width:100%;height:300px;" />
             </li>
              
             <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <div class="worksbox" >
                   <a href="javascript:void(0);" onclick="f_select('0');"><img src="images/card/WechatIMG316_02.png" style="display:block;width:100%;height:320px;" /></a>
                   </div>
             </li>
             <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <div class="worksbox" >
                  <a href="javascript:void(0);" onclick="f_select('1');"><img src="images/card/WechatIMG316_03.png" style="display:block;width:100%;height:320px;margin-left: 80px;" /></a>
                  </div>
             </li>
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <div class="worksbox" >
                  <a href="javascript:void(0);" onclick="f_select('2');"><img src="images/card/WechatIMG316_04.png" style="display:block;width:100%;height:320px;margin-left: 110px;" /></a>
                </div>
             </li>
            
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <img src="images/card/WechatIMG316_06.png" style="display:block;width:100%;height:300px;" />
             </li>
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <a href="javascript:void(0);" onclick="return f_add();" >
                <img src="images/card/WechatIMG316_07.png" style="display:block;width:100%;height:130px;" />
                </a>
             </li>
       </ul></div>
		 
	       
	                 
	</form>
	</body>
</html>
