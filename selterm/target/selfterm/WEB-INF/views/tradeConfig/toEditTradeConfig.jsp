
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript"	src="<%=request.getContextPath()%>/man/js/ajaxfileupload.js"></script>
<head>
	 <script type="text/javascript">
	  $(function(){
		   $(".form-file").on("change", function () {
			   var $obj = $(this);
			   console.info($obj)
			   var id = $(this).attr("id");
			  
		        var imgFile=$(this)[0];
		        var filextension=imgFile.value.substring(imgFile.value.lastIndexOf("."),imgFile.value.length);
		    	filextension=filextension.toLowerCase();
		          if ((filextension!='.jpg')&&(filextension!='.gif')&&(filextension!='.jpeg')&&(filextension!='.png')&&(filextension!='.bmp'))
		          {
		                alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !");
		                imgFile.focus();
		          }
		          else
		          {
		        	  $.ajaxFileUpload({  
		 	  	            url:"<%=request.getContextPath()%>/merchant/filesUpload.html",
		 	  	            secureuri:false,  
		 	  	            fileElementId:id,//file标签的id  
		 	  	            dataType: 'json',//返回数据的类型  
		 	  	            //data:{name:'logan'},//一同上传的数据  
		 	  	            success: function (data, status) {  
		 	  	            	$("#"+id).siblings('img')
		 	  	            	$("#"+id).siblings('img').attr('src',data);
		 	  	            	$("#"+id).siblings('input[type=hidden]').val(data);
		 	  	            },
		 	  	            error: function (data, status, e) {  
		 	  	            	alert("文件上传失败!提示");   
		 	  	            }  
		 	  	        });
		          }
		      });
	      });
	          
		</script>
</head>
<body>
	<section class="navigation">
		        <span>您当前所在的位置：</span><a  href="#" title="">渠道商管理</a>/<i>编辑渠道商</i>
	</section>
	<form class="form-add"  method="get" accept-charset="utf-8" action="<%=request.getContextPath()%>/org/editOrg.html">
	           
                <input class="form-txt" type="hidden" name="id" value="${editOrg.id }" />
	         
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>渠道商编码：</label>
                      <input class="form-txt" type="text" name="code" value="${editOrg.code }" disabled="disabled" />
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>渠道商名称：</label>
                      <input class="form-txt" type="text" name="name" value="${editOrg.name }" datatype="*" sucmsg="&nbsp;"  nullmsg="不能为空"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
 
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人姓名：</label>
                      <input class="form-txt" type="text" name="contactPerson" value="${editOrg.contactPerson }"  maxlength="20" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空" />
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人手机：</label>
                      <input class="form-txt" type="text" name="contactTel" value="${editOrg.contactTel }"  maxlength="11" datatype="m" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="手机格式错误"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>渠道商邮箱：</label>
                      <input class="form-txt from-address-txt" type="text" name="email" value="${editOrg.email}" datatype="*,e"  sucmsg="&nbsp;" nullmsg="不能为空"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
               <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>执照编号：</label>
                      <input class="form-txt from-address-txt" type="text" name="licenseNumber" value="${editOrg.licenseNumber}" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>被扫通知地址：</label>
                      <input class="form-txt from-address-txt" type="text" name="notifyUrl" value="${editOrg.notifyUrl}" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
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
                      <input class="form-txt from-address-txt" type="text" name="address" value="${editOrg.address }"  datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
               <%-- <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>银行卡号：</label>
                      <input class="form-txt from-address-txt" type="text" name="bankNumber" value="${editOrg.bankNumber}" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
                      <span class="form-yz Validform_checktip"></span>
              </div> --%>
              <div class="form-group">
                      <label class="form-lal">备注：</label>
                     <textarea class="form-cont" name="remark" >${editOrg.remark}</textarea>
              </div>
              <!-- <div class="form-group">
                      <label class="form-lal">开通平台帐号：</label>
                      <select class="form-sel" name="status" >
                        <option value="1">开通</option>
                        <option value="0">不开通</option>
                      </select>
              </div> -->
              <div class="form-group">
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>法人身份证正面：</label>
			</div>
			<div class="form-item form-item-wd">
			     <c:if test="${empty editOrg.legalIdentityCardPositive }">
					<img src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.legalIdentityCardPositive }">
					<img src="${editOrg.legalIdentityCardPositive }" />
				</c:if>
				<input class="form-file" type="file" name="file" value="" id="legalIdentityCardPositive" />
				<span class="form-yz Validform_checktip"></span> 
				<input type="hidden" name="legalIdentityCardPositive" value="${editOrg.legalIdentityCardOpposite }" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
			</div>
			</div>
			
			<div class="form-group">
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>法人身份证反面：</label>
			</div>
			<div class="form-item form-item-wd">
				 <c:if test="${empty editOrg.legalIdentityCardOpposite }">
					<img src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.legalIdentityCardOpposite }">
					<img src="${editOrg.legalIdentityCardOpposite }" />
				</c:if>
				 <input class="form-file" type="file" name="file" value="" id="legalIdentityCardOpposite" />
				 <span class="form-yz Validform_checktip"></span>
				 <input type="hidden" name="legalIdentityCardOpposite" value="${editOrg.legalIdentityCardOpposite }" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
			</div>
		  </div>
		  
		  <div class="form-group">
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>营业执照：</label>
			</div>
			<div class="form-item form-item-wd">
				 <c:if test="${empty editOrg.businessLicense }">
					<img src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.businessLicense }">
					<img src="${editOrg.businessLicense }" />
				</c:if>
				<input class="form-file" type="file" name="file" value="" id="businessLicense" />
				<span class="form-yz Validform_checktip"></span> 
				<input type="hidden" name="businessLicense" value="${editOrg.businessLicense }" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
			</div>
			</div>
			<div class="form-group">
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>开户许可证：</label>
			</div>
			<div class="form-item form-item-wd">
				 <c:if test="${empty editOrg.accountPermit }">
					<img src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.accountPermit }">
					<img src="${editOrg.accountPermit }" />
				</c:if>
				 <input class="form-file" type="file" name="file" value="" id="accountPermit"/>
				 <span class="form-yz Validform_checktip"></span>
				 <input type="hidden" name="accountPermit" value="${editOrg.accountPermit }"  datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空"/>
			</div>
		  </div>
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="保存"/>
                      <input class="form-btn" type="button" name="" value="关闭" onclick="myClose()"/>
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
							query('<%=request.getContextPath()%>/org/page.html');
							$("#tjdp").modal('hide');
						}else{
							 layer.msg('添加失败！',{icon:5});
						}
					}
	
			    });
			  });
	 	
	 	      function myClose(){
	 	    	 $("#tjdp").modal('hide');
	 	      }
	         
// 	 	     $(document).ready(function(){
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
// 	 		  });
			 
			 
		
		</script>
		    
 		
</body>
</html>