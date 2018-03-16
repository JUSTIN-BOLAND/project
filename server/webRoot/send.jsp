<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page language="java" import="java.util.*,com.lb.server.util.JDBC.JDBC,java.sql.ResultSet" %>

 <% 
					     String optType = request.getParameter("optType");
					     if(optType==null || optType.trim().length()==0) optType="0";
					     String taskType = request.getParameter("taskType");
					     String clientId = request.getParameter("clientId");
					     String machineId = request.getParameter("machineId");
					     if(clientId==null) clientId="00 00 00 00";
					     if(machineId==null) machineId="00 01";
					     String fee = request.getParameter("fee");
					     String mobile = request.getParameter("mobile");
					     if(mobile==null || mobile.trim().length()==0) mobile="0";
					     JDBC jdbc = new JDBC(JDBC.DB);
					   
					     String sql= null;
					     System.out.println("[jsp]: optType="+optType+",taskType="+taskType+",clientId="+clientId+",machineId="+machineId+",fee="+fee);
					     try
					     {
					     	jdbc.connect();
					    
					     	 if("1".equals(optType)){
							     
							     String money="",timeLen="";
							     if("1".equals(taskType)){
							    	 if("0".equals(fee)){
							    		 money = "8";
							    		 timeLen = "10";
							    	 }
							    	 else if("1".equals(fee)){
							    		 money = "15";
							    		 timeLen = "20";
							    	 }
							    	 else if("2".equals(fee)){
							    		 money = "20";
							    		 timeLen = "30";
							    	 }
							     }
							     sql = "INSERT INTO t_task (client_id, machine_id, task_type, money,time_len,STATUS,flag,insert_date) "+
							    	   "VALUES('"+clientId+"','"+machineId+"',"+taskType+",'"+money+"','"+timeLen+"',null,0,sysdate())";
							     System.out.println("[jsp]: insert="+sql);
							     jdbc.update(sql);
							     if("1".equals(mobile))response.sendRedirect("mobile.jsp?flag=1");
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
	
		<title>指令发送</title>
		
		<script type="text/javascript">
		    function f_send(){
		    	document.getElementById("optType").value="1";
		    	document.getElementById("terminalForm").submit();
		    }
		    function f_select(){
		    	var selObj = document.getElementById("taskType");
		    	//var v_idx = selObj.selectedIndex;
		    	var v_selValue = selObj.value;
		    	if(v_selValue=="0"){
		    		document.getElementById("tc").style.display="none";
		    	
		    	}
		    	else{
		    		document.getElementById("tc").style.display="";
		    		
		    	}
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
						<td>操作类型:</td>
						<td><select id="taskType" name="taskType" onchange="f_select();">
						        <option value="0">查询</option>
						        <option value="1" selected>消费</option>
	 					     </select>
	 					     <script type="text/javascript"> f_select();</script>
	 					 </td>
 					</tr>     
 					<tr>     
						<td>客户编号:</td>
						<td><input id="clientId" type="text" name="clientId" value="00 00 00 00"  class="easyui-validatebox"></td>
					</tr>
					<tr>
						<td>机器编号:</td>
						<td><input id="machineId" type="text" name="machineId" value="00 01"  class="easyui-validatebox"></td>
					</tr>
					<tr id="tc">
						<td >套餐:</td>
						<td ><select id="fee" name="fee">
						        <option value="0" selected>港式:舒筋松骨</option>
						        <option value="1" >泰式:强身减压</option>
						         <option value="2" >日式:养身理疗</option>
	 					     </select></td>
 					<!-- <td><button type="button" onclick="f_send();">发送</button></dd> -->
 					</tr>
				
					<tr>
						<td colspan="2">
						  <a id="btn1" href="javascript:void(0);" class="easyui-linkbutton l-btn" onclick="f_send();" group=""><span class="l-btn-left"><span class="l-btn-text">保  存</span></span></a>
						  <a href="demo.jsp" class="easyui-linkbutton l-btn" group="" id=""><span class="l-btn-left"><span class="l-btn-text">取  消</span></span></a>
						</td>  
				    </tr>
			    </table>
				
		
		
			</form>
	</body>
</html>
