<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page language="java" import="java.util.*,com.lb.server.util.JDBC.JDBC,java.sql.ResultSet" %>

<%
String flag = request.getParameter("flag");
if(flag==null || flag.trim().length()==0) flag="0";
if("1".equals(flag)){
%>
  <script>alert("消费成功");</script>
<%} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>手机端</title>
		
		<script type="text/javascript">
		   
		</script>
	


	</head>
	<body>
	<form id="terminalForm" method="post"  action=''>
	<input type="hidden" id="optType" name="optType" value="0">
		<div style="width:100%;">
             <ul style="margin:0;padding:0;">
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
               <img src="images/tc/01.png" style="display:block;width:100%;" />
             </li>
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <img src="images/tc/02.png" style="display:block;width:100%;" />
             </li>
             <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <a href="send.jsp?optType=1&taskType=1&fee=0&mobile=1"><img src="images/tc/03.png" style="display:block;width:100%;" /></a>
             </li>
             <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <a href="send.jsp?optType=1&taskType=1&fee=1&mobile=1"><img src="images/tc/04.png" style="display:block;width:100%;" /></a>
             </li>
              <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <a href="send.jsp?optType=1&taskType=1&fee=2&mobile=1"><img src="images/tc/05.png" style="display:block;width:100%;" /></a>
             </li>
             <li style="margin:0;padding:0;list-style-type:none;width:100%;">
                <img src="images/tc/06.png" style="display:block;width:100%;" />
             </li>
             
       </ul></div>
		 
	       
	                 
	</form>
	</body>
</html>
