
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	  <script type="text/javascript">
	  
		</script>
</head>
<body>
	<section class="navigation"> <span>您当前所在的位置：</span>
	<a href="#" title="">系统管理</a>/<i>账号信息</i> </section>
	<section class="main-cont">
	<form class="form-add"  method="get" accept-charset="utf-8" action="<%=request.getContextPath()%>/org/editOrg.html">
	          <div class="form-group">                                     
                      <input class="form-txt" type="hidden" name="id" value="${org.id }" />
              </div>
	         
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>经销商编码：</label>
                      <input class="form-txt" type="text" name="code" value="${org.code }"/>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>经销商名称：</label>
                      <input class="form-txt" type="text" name="name" value="${org.name }" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空" disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
 
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人姓名：</label>
                      <input class="form-txt" type="text" name="contactPerson" value="${org.contactPerson }"  maxlength="20" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空" />
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人手机：</label>
                      <input class="form-txt" type="text" name="contactTel" value="${org.contactTel }"  maxlength="20" datatype="m" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="手机格式错误"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>选择区域：</label>
                      <select  name="province"  id="province" onchange="onSelectChange(this,'city','1');" class="form-sel">
                             
    				  </select>
                      <select  name="city" id="city"  onchange="onSelectChange(this,'areaid','2');" class="form-sel" >
  							<option value="" ></option>
  					  </select>
                      <select  name="areaId"  id="areaid" onchange="onselect1()" class="form-sel" datatype="*" sucmsg="&nbsp;" nullmsg="不能为空">
  							<option value=""></option>
  					  </select>
              </div>
              
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>详情地址：</label>
                      <input class="form-txt from-address-txt" type="text" name="address" value="${org.address }"  datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal">备注：</label>
<%--                       <input class="form-cont" type="text" name="remark" value="${org.remark }"  datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空" /> --%>
                      <textarea class="form-cont" rows="5" name="remark">${org.remark }</textarea>
              </div>
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存"/>
              </div>
          </form>
          
          <script type="text/javascript">
          
	       //表单验证
		      $(function(){
			    $(".form-add").Validform({tiptype:3,
			    	datatype:{
			    		"uniquLoginName":function(gets,obj,curform,regxp){
			    			/*参数gets是获取到的表单元素值，
							  obj为当前表单元素，
							  curform为当前验证的表单，
							  regxp为内置的一些正则表达式的引用。*/
			    			var falg = false;
			            	$.ajax({ 
			            		url: '<%=request.getContextPath()%>/system/checkLoginName.html?loginName='+gets, 
			            		context: document.body, 
			            		async: false,
			            		success: function(date){
			            			console.log("检验结果："+date);
			            			falg = date;
			            		}
			            	});
			            	console.log("检验结果2：");
			            	return falg;
			    		}
			    	},
			    	ajaxPost:true,
					callback:function(data){
						if(data.success){
							layer.msg('修改成功！',{icon:1});
							query('<%=request.getContextPath()%>/system/accountMessage.html');
						}else{
							 layer.msg('添加失败！',{icon:5});
						}
					}
	
			    });
			  });
	 	
	         
	 	     $(document).ready(function(){
	 			  function onselect1(){
	 					if($("#province").val() == ''){
	 						$("#areaid").val("");
	 						layer.alert('请先选省份',{icon:2});
	 						retrun ;
	 					}else if($("#city").val() == ''){
	 						$("#areaid").val("");
	 						layer.alert('请先选城市',{icon:2});
	 						retrun ;
	 					}
	 				};
	 				function onSelectChange(obj,toSelId,num){
	 					if(num == '2'){
	 						if($("#province").val() == ''){
	 							$("#city").val("");
	 							layer.alert('请先选择省份',{icon:2});
	 							return ;
	 						}
	 					}
	 					setSelect(obj.value,toSelId);
	 				};
	 				function setSelect(fromSelVal,toSelId){
	 					document.getElementById(toSelId).innerHTML="";
	 					jQuery.ajax({
	 					  url: "<%=request.getContextPath()%>/merchant/getRogin.html",
	 					  cache: false,
	 					  data:"parentId="+fromSelVal,
	 					  success: function(data){
	 						  console.log(data);
	 					    createSelectObj(data,toSelId);
	 					  }
	 					});
	 				};
	 			
	 				function createSelectObj(data,toSelId){
	 					var arr = jsonParse(data).itemList;
	 					if(arr != null && arr.length>0){
	 						var obj = document.getElementById(toSelId);
	 						obj.innerHTML="";
	 						var nullOp = document.createElement("option");
	 						nullOp.setAttribute("value","");
	 						nullOp.appendChild(document.createTextNode("请选择"));
	 						obj.appendChild(nullOp);
	 						for(var o in arr){
	 							var op = document.createElement("option");
	 							op.setAttribute("value",arr[o].id);
	 							//op.text=arr[o].name;//这一句在ie下不起作用，用下面这一句或者innerHTML
	 							op.appendChild(document.createTextNode(arr[o].name));
	 							obj.appendChild(op);
	 						}
	 						
	 					}
	 				};
	 					 
	 				 
	 					setSelect('1','province');
	 					setSelect('${province}','city');
	 					setSelect('${city}','areaid');
	 					setTimeout(StartProvince,100);
	 				    function StartProvince(){
	 				    	$("#province").find("option[value='${province}']").attr("selected",true);
	 				        $("#city").find("option[value='${city}']").attr("selected",true);
	 				        $("#areaid").find("option[value='${areaId}']").attr("selected",true);
	 				    	}
	 					var id = '${enterpriseid}';
	 					if(id !=''){
	 						setSelect('1','province');
	 					}
	 					
	 				        $("#areaid").find("option[value='${areaid}']").attr("selected",true);
	 				    	 
	 				 
	 					 
	 				 
	 			 
	 		  });
			 
			 
		
		</script>
		    
 		
</body>
</html>