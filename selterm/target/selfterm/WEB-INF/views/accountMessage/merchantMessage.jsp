<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
		</script>
</head>
<body>

	<section class="navigation"> <span>您当前所在的位置：</span>
	<a href="#" title="">系统管理</a>/<i>账号信息</i> </section>
	<section class="main-cont">
	<div class="article">
		<form id="query_from">
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>商户编码：</label>
                      <input class="form-txt" type="text" name="code" value="${merchant.code}"  disabled="disabled"	/>
              </div>
            
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>商户名称：</label>
                      <input class="form-txt" type="text" name="name" value="${merchant.name}"  disabled="disabled"/>
              </div>
              
             <div class="form-group">
             		<label class="form-lal"><i class="text-danger">*</i>登陆用户名：</label>
             		<input class="form-txt" type="text" name="meraccount" value="${merchant.meraccount}" disabled="disabled"/>
             </div>
		
		      <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人姓名：</label>
                      <input class="form-txt" type="text" name="contactperson" value="${merchant.contactperson}"  disabled="disabled"/>
              </div>
              
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人手机：</label>
                      <input class="form-txt" type="text" name="contacttel" value="${merchant.contacttel}"  disabled="disabled"/>
              </div>
              
              <c:if test="${merchant.mertype eq 1 }">
               <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>所在区域：</label>
                      <select  name="province"  id="province" onchange="onSelectChange(this,'city','1');" class="form-sel" disabled="disabled">
                             
    				  </select>
                      <select  name="city" id="city"  onchange="onSelectChange(this,'areaid','2');" class="form-sel" disabled="disabled" >
  							<option value="" ></option>
  					  </select>
                      <select  name="areaId"  id="areaid" onchange="onselect1()" class="form-sel" disabled="disabled">
  							<option value=""></option>
  					  </select>
              </div>
              
              
              </c:if>
               <c:if test="${merchant.mertype eq 2 }">
               		<div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>所在区域：</label>
                      <select  name="province"   onchange="onSelectChange(this,'city','1');" class="form-sel" disabled="disabled">
                             <option >${city.cityName }</option>
    				  </select>
                      <select  name="city"   onchange="onSelectChange(this,'areaid','2');" class="form-sel" disabled="disabled" >
  							<option >${prov.provinceName }</option>
  					  </select>
                      
              </div>
               </c:if>
              
		    <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>详情地址：</label>
                      <input class="form-txt from-address-txt" type="text" name="address" value="${merchant.address}"  disabled="disabled"/>
              </div>
              
		      <div class="form-group">
                      <label class="form-lal">备注：</label>
                      <textarea class="form-cont" rows="5"  name="remark"   >${merchant.remark}</textarea>
              </div>
		
		
<%-- 			<%@ include file="/WEB-INF/views/pageSize.jsp"%> --%>
		</form>
<script type="text/javascript">
	 	     $().ready(function(){
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
	 					 
	 				$(document).ready(function(){
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
	 				    	}
	 				);
	 			 
	 		  });
			 
			 
		
		</script>
       
	</div>
	</section>

</body>
</html>