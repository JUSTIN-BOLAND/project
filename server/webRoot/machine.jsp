<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page language="java" import="java.util.*,com.lb.server.util.JDBC.JDBC,java.sql.ResultSet" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>设备列表</title>
		
		<script type="text/javascript">
		   
		</script>
		<link href="resources/css/easyui.css" rel="stylesheet" type="text/css">

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
		
		<div class="datagrid-view" style="width: 1310.02px; height: 523px;"><div class="datagrid-view1" style="width: 26px;">
		<div class="datagrid-header" style="width: 26px; height: 25.0104px;">
		   <div class="datagrid-header-inner" style="display: block;">
		   <table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="height: 26px;">
		     <tbody><tr class="datagrid-header-row"><td rowspan="0"><div class="datagrid-header-rownumber"></div></td></tr></tbody>
		   </table>
		   </div>
		</div>
		<div class="datagrid-body" style="width: 26px; margin-top: 0px; height: 497.021px;">
		  <div class="datagrid-body-inner">
		   <table class="datagrid-btable" cellspacing="0" cellpadding="0" border="0">
		     <tbody>
		        <tr id="datagrid-row-r1-1-0" datagrid-row-index="0" class="datagrid-row" style="height: 37px;"> 
		           <td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber">1</div></td>
		        </tr>
		     </tbody>
		    </table>
		  </div>
	   </div>
	   <div class="datagrid-footer" style="width: 26px;">
	      <div class="datagrid-footer-inner" style="display: none;"></div>
	   </div></div>
	   <div class="datagrid-view2" style="width: 1284.02px;">
	       <div class="datagrid-header" style="width: 1284px; height: 25.0104px;">
	           <div class="datagrid-header-inner" style="display: block;">
	              <table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="height: 26px;">
	                 <tbody><tr class="datagrid-header-row">
	                  
					<td field="DEMO" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						客户编号
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td><td field="NUMS"><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						机器编号
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td><td field="AREA_NO"><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						IP
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
					<td field="CREATE_TIME" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						端口号
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
					<td field="CREATE_TIME" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						状态
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
					
					
					</tr></tbody></table></div></div>
					<div class="datagrid-body" style="width: 1284px; margin-top: 0px; height: 497.021px; overflow-x: hidden;">
					  <table class="datagrid-btable" cellspacing="0" cellpadding="0" border="0">
					     <tbody>
					     <% 
					     
					     
					     JDBC jdbc = new JDBC(JDBC.DB);
					     ResultSet rs = null;
					     String sql= null;
					     ///System.out.println("[jsp]: optType="+optType+",taskType="+taskType+",clientId="+clientId+",machineId="+machineId+",fee="+fee);
					     try
					     {
					     	jdbc.connect();
					    
					     	 
					     	 
					        sql="select client_id, machine_id, 	ip,PORT,  "+
					        	"CASE  WHEN IFNULL(STATUS,'41')='40' THEN '机器有人正在使用' "+
					        	"      WHEN IFNULL(STATUS,'41')='41' THEN '机器空闲' "+ 
					        	"      WHEN IFNULL(STATUS,'41')='33' THEN '机器被禁止使用' "+
					        	"      WHEN STATUS='00' THEN '正常启动机器' "+
					        	"      WHEN STATUS='33' THEN '机器被限制使用' "+
					        	"      WHEN STATUS='44' THEN '购买时长超出极限' " +
					        	"      WHEN STATUS='99' THEN '未使用' " +
					        	"END	 STATUS "+
					        	"from t_machine_ip";
						     	
					     	 System.out.println("[jsp]: query="+sql);
					         rs = jdbc.query(sql);
							 while(rs.next()){
						%>
						  <tr id="datagrid-row-r1-2-0" datagrid-row-index="0" class="datagrid-row" style="height: 37px;">
					       <td field="TYPE"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-TYPE"><%=rs.getString("client_id")%></div></td>
					       <td field="NUMS"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-NUMS"><%=rs.getString("machine_id")%></div></td>
					       <td field="SOURCE_CHL"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-SOURCE_CHL"><%=rs.getString("ip")%></div></td>
					       <td field="SOURCE_CHL"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-SOURCE_CHL"><%=rs.getString("port")%></div></td>
					       <td field="SOURCE_CHL"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-SOURCE_CHL"><%=rs.getString("STATUS")%></div></td>
					       </tr>
						<%		 
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
					     
					      
					         
					         
					        
					       
					    
					      </tbody>
					   </table>
					 </div>
					 <div class="datagrid-footer" style="width: 1284px;"><div class="datagrid-footer-inner" style="display: none;"></div></div></div>
			<!-- 		 <table id="badListTable" toolbar="#tb" style="display: none;">
   
     
			<thead>
			
				<tr>
					<th field="NAME" width="80" align="center" sortable="true">
						r_use_day
					</th>
					<th field="TYPE" width="60" align="center" sortable="true"> 
						d_trmnl_model
					</th>
					<th field="SOURCE_CHL" width="80" align="center" sortable="true"> 
						r_trmnl_model
					</th>
					<th field="DEMO" width="80" align="center" sortable="true">
						r_trmnl_brand
					</th>
					<th field="NUMS" width="80" align="center" sortable="true">
						item.d_use_day
					</th>
					<th field="AREA_NO" width="80" align="center" sortable="true">
						d_trmnl_brand
					</th>
					
					
				</tr>
			
		</thead>
		
  
        </table> -->
<style type="text/css">
.datagrid-cell-c1-NAME{width:72px}
.datagrid-cell-c1-TYPE{width:52px}
.datagrid-cell-c1-SOURCE_CHL{width:72px}
.datagrid-cell-c1-DEMO{width:72px}
.datagrid-cell-c1-NUMS{width:72px}
.datagrid-cell-c1-AREA_NO{width:72px}
.datagrid-cell-c1-CREATE_TIME{width:72px}
.datagrid-cell-c1-LOGON_ID{width:62px}
.datagrid-cell-c1-CZ{width:72px}
</style></div>
		<div class="datagrid-pager pagination"><table cellspacing="0" cellpadding="0" border="0"><tbody><tr><td><select class="pagination-page-list"><option>10</option><option>15</option><option>20</option><option>25</option><option>30</option><option>40</option><option>50</option></select></td><td><div class="pagination-btn-separator"></div></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-first">&nbsp;</span></span></span></a></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-prev">&nbsp;</span></span></span></a></td><td><div class="pagination-btn-separator"></div></td><td><span style="padding-left:6px;">第</span></td><td><input class="pagination-num" type="text" value="1" size="2"></td><td><span style="padding-right:6px;">共1页</span></td><td><div class="pagination-btn-separator"></div></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-next">&nbsp;</span></span></span></a></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-last">&nbsp;</span></span></span></a></td><td><div class="pagination-btn-separator"></div></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-load">&nbsp;</span></span></span></a></td></tr></tbody></table><div class="pagination-info">显示1到1,共1记录</div><div style="clear:both;"></div></div>
		
			<div id="win" style="width: 650px;height:400px; padding:2px;" closed="true" shadow="true" resizable="false" collapsible="false" minimizable="false" maximizable="false" modal="true"></div>
			</form>
	</body>
</html>
