<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page language="java" import="java.util.*,com.lb.server.util.JDBC.JDBC,java.sql.ResultSet" %>

 <% 
					     String optType = request.getParameter("optType");
					     if(optType==null || optType.trim().length()==0) optType="0";
					     String cardId = request.getParameter("cardId");
					     String reserveMoney = request.getParameter("reserveMoney");
					     String mobile = request.getParameter("mobile");
					     if(mobile==null || mobile.trim().length()==0) mobile="0";
					     JDBC jdbc = new JDBC(JDBC.DB);
					   
					     String sql= null;
					     System.out.println("[jsp]: optType="+optType+",cardId="+cardId+",reserveMoney="+reserveMoney);
					     try
					     {
					     	jdbc.connect();
					    
					     	 if("1".equals(optType)){
					     		 sql = "select count(1) from  t_card where card_id='"+cardId+"'";
							     int cnt = jdbc.queryForInt(sql);
							     if("1".equals(mobile)){
						    		 String fee = request.getParameter("fee");
						    		 if("0".equals(fee)){
						    			 reserveMoney="50";
						    		 }
						    		 else if("1".equals(fee)){
						    			 reserveMoney="100";
						    		 }
						    		 else if("2".equals(fee)){
						    			 reserveMoney="220";
						    		 }
						    	 }
							     if(cnt==0){
							    	 
								     sql = "INSERT INTO t_card (card_id, reserve_money,STATUS,insert_date) "+
								    	   "VALUES('"+cardId+"','"+reserveMoney+"',null,sysdate())";
								     System.out.println("[jsp]: insert="+sql);
								     jdbc.update(sql);
								     %>
							    	 <script>alert("保存成功");</script>
							    	 <%
							     }
							     else{
							    	 sql = "update  t_card set reserve_money='"+reserveMoney+"' where card_id='"+cardId+"'";
							    	 jdbc.update(sql);
							    	 %>
							    	 <script>alert("保存成功");</script>
							    	 <%
							     }
							     if("1".equals(mobile)) response.sendRedirect("mobileCard.jsp");
							     
						     }
					     	 else  if("3".equals(optType)){
							     
							     
							     sql = "delete from  t_card where card_id='"+cardId+"'";
							     System.out.println("[jsp]: delete="+sql);
							     jdbc.update(sql);
							     %>
						    	 <script>alert("删除卡号[<%=cardId%>]成功");</script>
						    	 <%
						    	 response.sendRedirect("card.jsp");
						     }
					     	 
					   
					     }
					     catch(Exception e)
					     {
					     	
					     	e.printStackTrace();
					     }

					     finally
					     {
					     	try
					     	{
					     		if(jdbc != null) jdbc.close();
					     	}
					     	catch(Exception e)
					     	{
					     		e.printStackTrace();
					     	}
					     }
					     
					     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
 <link href="resources/css/easyui.css" rel="stylesheet" type="text/css">
	
		<title>卡[添加]</title>
		
		<script type="text/javascript">
		    function f_add(){
		    	var v_cardId = document.getElementById("cardId").value;
		    	if(v_cardId.length ==0){
		    		alert("卡号不能为空!");
		    		return false;
		    	}
		    	var reserveMoney = document.getElementById("reserveMoney").value;
		    	if(reserveMoney.length ==0){
		    		alert("预付余额不能为空!");
		    		return false;
		    	}
		    	if(v_cardId.length !=7){
		    		alert("卡号必须是7位数字!");
		    		return false;
		    	}
		    	document.getElementById("optType").value="1";
		    	document.getElementById("terminalForm").submit();
		    	return true;
		    }
		    
		</script>
		<style type="text/css">
			dl dt {
			    float: left;
			}
			
			dl dt {
			    font-weight: normal;
			    padding: 2px 5px 0 1em;
			}
		</style>

	<style type="text/css">
		.btn-submit1 { background:#5792de; -moz-border-radius:14px; -webkit-border-radius: 14px; border-radius: 14px; color:#fff!important; padding:2px 8px; margin-right:2px; text-decoration:none!important;}
		.btn-submit1:hover { background:#4c85cd;}
		.btn-submit2 { background:#ee842f; -moz-border-radius:14px; -webkit-border-radius: 14px; border-radius: 14px; color:#fff!important; padding:2px 8px; margin-right:2px; text-decoration:none!important;}
		.btn-submit2:hover { background:#df7622;}
		.topListUser dl dd input[type="text"]{ height:22px; line-height:22px;}
	</style>
	</head>
	<body>
	<form id="terminalForm" method="post"  action=''>
	<input type="hidden" id="optType" name="optType" value="0">
		
				<table>
				      
 					<tr>     
						<td>卡号:</td>
						<td><input id="cardId" type="text" name="cardId" value=""  class="easyui-validatebox"
						 onkeyup = "if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}" 
						 onafterpaste = "if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}"
						 ></td>
					</tr>
					<tr>
						<td>预付余额:</td>
						<td><input id="reserveMoney" type="text" name="reserveMoney" value=""  class="easyui-validatebox"
												 onkeyup = "if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}" 
						 onafterpaste = "if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}"
						
						></td>
					</tr>
					
				
					<tr>
						<td colspan="2">
						  <a id="btn1" href="javascript:void(0);" class="easyui-linkbutton l-btn" onclick="f_add();" group=""><span class="l-btn-left"><span class="l-btn-text">保  存</span></span></a>
						  <a href="card.jsp" class="easyui-linkbutton l-btn" group="" id=""><span class="l-btn-left"><span class="l-btn-text">取  消</span></span></a>
						</td>  
				    </tr>
			    </table>
				
		
		
			</form>
	</body>
</html>
