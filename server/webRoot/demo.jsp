<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page language="java" import="java.util.*,com.lb.server.util.JDBC.JDBC,java.sql.ResultSet" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<script src="resources/js/jquery-1.8.0.min.js" type="text/javascript"></script>
	 <script type="text/javascript" src="resources/js/rendezvous.js" ></script>
	 <link href="resources/js/rendezvous.css" rel="stylesheet" type="text/css">
	 <link href="resources/css/easyui.css" rel="stylesheet" type="text/css">
		<title>消费流水</title>
		
		<script type="text/javascript">
		    function f_query(){
		    	document.getElementById("optType").value="1";
		    	document.getElementById("terminalForm").submit();
		    }
		    function f_addRow(rowNum){
		    	var v_tr='<tr id="datagrid-row-r1-1-0" datagrid-row-index="0" class="datagrid-row" style="height: 37px;">  '+
		                 '<td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber">'+rowNum+'</div></td> '+
			        '</tr>';
		    	 $("#tbRow tbody").append(v_tr);
		    }
		   /*  function f_select(){
		    	var selObj = document.getElementById("taskType");
		    	//var v_idx = selObj.selectedIndex;
		    	var v_selValue = selObj.value;
		    	if(v_selValue=="0"){
		    		document.getElementById("tcDt").style.display="none";
		    		document.getElementById("tcDd").style.display="none";
		    	}
		    	else{
		    		document.getElementById("tcDt").style.display="";
		    		document.getElementById("tcDd").style.display="";
		    	}
		    } */
		    //f_select();
		</script>
		<style type="text/css">
			dl  {
			    float: left;
			   font-weight: bold;
			   font-size: 12px;
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
		
	</style>
	</head>
	<body>
	<form id="terminalForm" method="post"  action=''>
	<input type="hidden" id="optType" name="optType" value="0">
		<div id="tb">
			<div class="form topListUser">
				<dl>
				<div style="float:left;">
					操作类型:<select id="taskType" name="taskType" onchange="f_select();">
					        <option value="0">查询</option>
					        <option value="1" selected>消费</option>
 					     </select>
 					 客户编号:<input id="clientId" type="text" name="clientId" value="00 00 00 00"  class="easyui-validatebox">
					机器编号:<input id="machineId" type="text" name="machineId" value="00 01"  class="easyui-validatebox">
			   </div>
			   		
			<span style="float:left">日期:</span>
					<div id="trackDate" style="float:left;margin-top: 0px;"></div>
                <script type="text/javascript">
                    $('#trackDate').RendezVous({
                        inputDateId: 'createDate',
                        formats: {
                            display: {
                                date: '%Y-%m-%d'
                            }
                        },
                        i18n: {
                            calendar: {
                                month: {
                                    previous: '上一月',
                                    next:     '下一月',
                                    up:       '选择月份'
                                },
                                year: {
                                    previous: '上一年',
                                    next:     '下一年',
                                    up:       '选择年份'
                                },
                                decade: {
                                    previous: '上十年',
                                    next:     '下十年',
                                    up:       '选择日期'
                                }
                            },
                            days: {
                                abbreviation: {
                                    monday:    '一',
                                    tuesday:   '二',
                                    wednesday: '三',
                                    thursday:  '四',
                                    friday:    '五',
                                    saturday:  '六',
                                    sunday:    '日'
                                },
                                entire: {
                                    monday:    '星期一',
                                    tuesday:   '星期二',
                                    wednesday: '星期三',
                                    thursday:  '星期四',
                                    friday:    '星期五',
                                    saturday:  '星期六',
                                    sunday:    '星期日'
                                }
                            },
                            months: {
                                abbreviation:
                                {
                                    january:   '一月',
                                    february:  '二月',
                                    march:     '三月',
                                    april:     '四月',
                                    may:       '五月',
                                    june:      '六月',
                                    july:      '七月',
                                    august:    '八月',
                                    september: '九月',
                                    october:   '十月',
                                    november:  '十一月',
                                    december:  '十二月'
                                },
                                entire: {
                                    january:   '一月',
                                    february:  '二月',
                                    march:     '三月',
                                    april:     '四月',
                                    may:       '五月',
                                    june:      '六月',
                                    july:      '七月',
                                    august:    '八月',
                                    september: '九月',
                                    october:   '十月',
                                    november:  '十一月',
                                    december:  '十二月'
                                }
                            }
                        }
                    });
                </script>
					      <!--  <select id="fee" name="fee">
					        <option value="0" selected>港式:舒筋松骨</option>
					        <option value="1" >泰式:强身减压</option>
					         <option value="2" >日式:养身理疗</option>
 					     </select> -->
 					
 					<a id="btn1" href="javascript:void(0);" class="easyui-linkbutton l-btn" onclick="f_query();" group=""><span class="l-btn-left"><span class="l-btn-text">查  询</span></span></a>
 					<a href="send.jsp" class="easyui-linkbutton l-btn" group="" id=""><span class="l-btn-left"><span class="l-btn-text">发  送</span></span></a>
 					<a href="machine.jsp" class="easyui-linkbutton l-btn" group="" id=""><span class="l-btn-left"><span class="l-btn-text">设  备</span></span></a>
 					
				</dl>
				
			</div>
		
		</div>
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
		   <table class="datagrid-btable" id="tbRow" cellspacing="0" cellpadding="0" border="0">
		     <tbody>
		        
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
	              <table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="float:left;height: 26px;">
	                 <tbody><tr class="datagrid-header-row">
	                  
					<td field="SOURCE_CHL" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span> 
						任务类型
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td><td field="DEMO" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						客户编号
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td><td field="NUMS"><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						机器编号
					
					<td field="CREATE_TIME" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						套餐
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td><td field="AREA_NO"><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						金额
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
					<td field="CREATE_TIME" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						时长
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
					<td field="CREATE_TIME" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						状态
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
		
					<td field="CREATE_TIME" class=""><div class="datagrid-cell" style="width: 138px; text-align: center;"><span>
						日期
					</span><span class="datagrid-sort-icon">&nbsp;</span></div></td>
					
					</tr></tbody></table></div></div>
					<div class="datagrid-body" style="width: 1284px; margin-top: 0px; height: 497.021px; overflow-x: hidden;">
					  <table class="datagrid-btable" cellspacing="0" cellpadding="0" border="0">
					     <tbody>
					     <% 
					     String optType = request.getParameter("optType");
					     if(optType==null || optType.trim().length()==0) optType="0";
					     String taskType = request.getParameter("taskType");
					     String clientId = request.getParameter("clientId");
					     String machineId = request.getParameter("machineId");
					     if(clientId==null) clientId="00 00 00 00";
					     if(machineId==null) machineId="00 01";
					     String fee = request.getParameter("fee");
					     String createDate = request.getParameter("createDate");
					     
					     JDBC jdbc = new JDBC(JDBC.DB);
					     ResultSet rs = null;
					     String sql= null;
					     System.out.println("[jsp]: optType="+optType+",taskType="+taskType+",clientId="+clientId+",machineId="+machineId+",createDate="+createDate);
					     try
					     {
					     	jdbc.connect();
					    
					     	/*  if("1".equals(optType)){
							     
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
						     } */
					     	 
					        sql="SELECT      a.client_id, a.machine_id,                                                                                                                     "+
						        "CASE task_type WHEN 0 THEN '查询'  WHEN 1 THEN '消费' ELSE '未知' END task_type,                                                                           "+
						        "IFNULL(money,'') money, IFNULL(time_len,'') time_len,                                                                                                      "+
						        "                                                                                                                                                           "+
						        "CASE                                                                                                                                                       "+
						        "  WHEN task_type='1'  THEN  CASE  WHEN a.STATUS='00' THEN '正常启动机器' WHEN a.STATUS='33' THEN '机器被限制使用' WHEN a.STATUS='44' THEN '购买时长超出极限' WHEN a.STATUS='99' THEN '未使用' END "+
						        "  WHEN task_type='0' THEN  CASE  WHEN a.STATUS='40' THEN '机器有人正在使用' WHEN a.STATUS='41' THEN '机器空闲' WHEN a.STATUS='33' THEN '机器被禁止使用' WHEN a.STATUS='99' THEN '未使用' END      "+
						        "END STATUS,                                                                                                                                                "+
						        "CASE                                                                                                                                                       "+
						        "   WHEN money='8' AND time_len='10' THEN '港式:舒筋松骨'                                                                                                   "+
						        "   WHEN money='15' AND time_len='20' THEN '泰式:强身减压'                                                                                                  "+
						        "   WHEN money='20' AND time_len='30' THEN '日式:养身理疗'                                                                                                  "+
						        "   ELSE '' END tc,                                                                                                                                         "+
						        "flag, DATE_FORMAT(insert_date,'%Y-%m-%d %H:%i:%s') insert_date, id ,                                                                                                                                    "+
						        "b.ip,b.port                                                                                                                                                "+
						        "FROM  t_task a,t_machine_ip b                                                                                                                              "+
						        "WHERE a.client_id = b.client_id AND a.machine_id = b.machine_id                                                                                            "+
						        " and  a.flag=1  and  a.client_id='"+clientId+"' AND a.machine_id='"+machineId+"'";
						     	 if(taskType!=null && taskType.trim().length() > 0 ) sql += " AND task_type="+taskType;
						     	 if(createDate!=null && createDate.trim().length() > 0 )  sql += " AND DATE_FORMAT(insert_date,'%Y-%m-%d')='"+createDate+"'";
					     	 System.out.println("[jsp]: query="+sql);
					         rs = jdbc.query(sql);
					         int i = 1;
							 while(rs.next()){
						%>
						  <tr id="datagrid-row-r1-2-0" datagrid-row-index="0" class="datagrid-row" style="height: 37px;">
					        <td field="TYPE"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-TYPE"><%=rs.getString("task_type")%></div></td>
					       <td field="TYPE"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-TYPE"><%=rs.getString("client_id")%></div></td>
					       <td field="NUMS"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-NUMS"><%=rs.getString("machine_id")%></div></td>
					          <td field="TYPE"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-TYPE"><%=rs.getString("tc")%></div></td>
					       <td field="SOURCE_CHL"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-SOURCE_CHL"><%=rs.getString("money")%></div></td>
					       <td field="SOURCE_CHL"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-SOURCE_CHL"><%=rs.getString("time_len")%></div></td>
					    
					         <td field="NAME"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-NAME">	<%=rs.getString("STATUS")%></div></td>
					          <td field="NAME"><div style="width: 138px;text-align:center;;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-NAME">	<%=rs.getString("insert_date")%></div></td>
					     </tr>
					     <script>f_addRow('<%=i%>');</script>
						<%		 
						    i++;
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
					     		if(rs != null) rs.close();
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
		<div class="datagrid-pager pagination">
		<table cellspacing="0" cellpadding="0" border="0">
		   <tbody><tr><td><select class="pagination-page-list"><option>10</option><option>15</option><option>20</option><option>25</option><option>30</option><option>40</option><option>50</option></select></td><td><div class="pagination-btn-separator"></div></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-first">&nbsp;</span></span></span></a></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-prev">&nbsp;</span></span></span></a></td><td><div class="pagination-btn-separator"></div></td><td><span style="padding-left:6px;">第</span></td><td><input class="pagination-num" type="text" value="1" size="2"></td><td><span style="padding-right:6px;">共1页</span></td><td><div class="pagination-btn-separator"></div></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-next">&nbsp;</span></span></span></a></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-last">&nbsp;</span></span></span></a></td><td><div class="pagination-btn-separator"></div></td><td><a href="javascript:void(0)" class="l-btn l-btn-plain" group="" id=""><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-load">&nbsp;</span></span></span></a></td></tr></tbody></table><div class="pagination-info">显示1到1,共1记录</div><div style="clear:both;"></div></div>
		
			<div id="win" style="width: 650px;height:400px; padding:2px;" closed="true" shadow="true" resizable="false" collapsible="false" minimizable="false" maximizable="false" modal="true"></div>
			</form>
	</body>
</html>
