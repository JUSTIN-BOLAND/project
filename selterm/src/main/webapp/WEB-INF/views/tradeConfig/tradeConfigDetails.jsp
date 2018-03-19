<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript"	src="<%=request.getContextPath()%>/man/js/ajaxfileupload.js"/>
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
	          
	  
	   var inum=1;
		$("#hideDisplay").click(function () {
			if(inum==1){
				$(this).attr("src","<%=request.getContextPath()%>/man/images/open.png"); 
				$("#meraccount").attr('type','text');
				inum++;
			}else{
 				$(this).attr("src","<%=request.getContextPath()%>/man/images/close.png");
				$("#meraccount").attr('type','password');
				inum--;
			} 
	
		});
		
		$(function(){
		    $(".ewm-img").on(
		     {"mouseenter":function(){
		       var evm_offset=$(this).offset();
		       $("<div class='img-da' style='width:400px; height:400px;'><img style='width:398px; height:398px;' src="+ $(this).attr("src")+" alt=''></div>").appendTo($("body"));
		       $(".img-da").css({left:evm_offset.left+120,top:evm_offset.top-100})


		    },
		     "mouseleave":function(){
		       $(".img-da").remove();
		     }
		   });

		  });
		
		function savepic() { 
			alert("aaa")
			if (document.all.a1 == null) { 
			objIframe = document.createElement("IFRAME"); 
			document.body.insertBefore(objIframe); 
			objIframe.outerHTML = "<iframe name=a1 style='width:400px;hieght:300px' src=" + imageName.href + "></iframe>"; 
			re = setTimeout("savepic()", 1) 
			} 
			else { 
			clearTimeout(re) 
			pic = window.open(imageName.href, "a1") 
			pic.document.execCommand("SaveAs") 
			document.all.a1.removeNode(true) 
			} 
			} 
	  
		</script>
		
	
</head>
<body>

    <section class="navigation">
		        <span>您当前所在的位置：</span><a  href="#" title="">渠道商管理</a>/<i>渠道商详情</i>
	</section>
	<section class="main-cont">
	<form class="form-add"  method="get" accept-charset="utf-8" action="">
	           
                <input class="form-txt" type="hidden" name="id" value="${editOrg.id }" disabled="disabled"/>
	         
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>渠道商编码：</label>
                      <input class="form-txt" type="text" name="code" value="${editOrg.code }" disabled="disabled"/>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>渠道商名称：</label>
                      <input class="form-txt" type="text" name="name" value="${editOrg.name }" disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
               <%-- <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>登录用户名：</label>
                      <input class="form-txt" type="password" id="orgAccount"  value="${editOrg.orgAccount}"   disabled="disabled"/>
              </div>
 			 <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>登录密码：</label>
                      <style>
                      input[type='password']{margin-right:10px;}
                      </style>
                      <input class="form-txt" type="password" id="meraccount" name="meraccount" value="${userInfo.password}"   disabled="disabled"/>
                      <img style="width:20px; height:20px;margin: 8px 0 0 10px; cursor: pointer;  " id="hideDisplay" src="<%=request.getContextPath()%>/man/images/close.png"/>
                      <input type="button" value="密码重置" onclick="passwordReset('${editOrg.id}')" style="padding: 0 10px; height: 30px; font-size: 12px; cursor: pointer;"/>
              </div> --%>
 
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人姓名：</label>
                      <input class="form-txt" type="text" name="contactPerson" value="${editOrg.contactPerson }"  disabled="disabled" />
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>联系人手机：</label>
                      <input class="form-txt" type="text" name="contactTel" value="${editOrg.contactTel }" disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>渠道商邮箱：</label>
                      <input class="form-txt from-address-txt" type="text" name="email" value="${editOrg.email}" disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
               <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>执照编号：</label>
                      <input class="form-txt from-address-txt" type="text" name="licenseNumber" value="${editOrg.licenseNumber}" disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>被扫通知地址：</label>
                      <input class="form-txt from-address-txt" type="text" name="notifyUrl" value="${editOrg.notifyUrl}" disabled="disabled" />
                      <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>选择区域：</label>
                      <select  name="province"  id="province" onchange="onSelectChange(this,'city','1');" class="form-sel" disabled="disabled">
                             
    				  </select>
                      <select  name="city" id="city"  onchange="onSelectChange(this,'areaid','2');" class="form-sel" disabled="disabled">
  							<option value="" ></option>
  					  </select>
                      <select  name="areaId"  id="areaid" onchange="onselect1()" class="form-sel" disabled="disabled">
  							<option value=""></option>
  					  </select>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>详情地址：</label>
                      <input class="form-txt from-address-txt" type="text" name="address" value="${editOrg.address }" disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div>
               <%-- <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>银行卡号：</label>
                      <input class="form-txt from-address-txt" type="text" name="bankNumber" value="${editOrg.bankNumber}" disabled="disabled"/>
                      <span class="form-yz Validform_checktip"></span>
              </div> --%>
              <div class="form-group">
                      <label class="form-lal">备注：</label>
                     <textarea class="form-cont" name="remark" disabled="disabled">${editOrg.remark}</textarea>
              </div>
              
              <div class="form-group">
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>法人身份证正面：</label>
			</div>
			<div class="form-item form-item-wd">
			     <c:if test="${empty editOrg.legalIdentityCardPositive }">
					<img class="ewm-img" src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.legalIdentityCardPositive }">
					<a href="<%=request.getContextPath()%>/system/download.html?fileName=${editOrg.legalIdentityCardPositive }" ><img class="ewm-img" src="${editOrg.legalIdentityCardPositive }" /></a>
				</c:if>
				<input   type="hidden" name="legalIdentityCardPositive" value="${editOrg.legalIdentityCardOpposite }"/>
			</div>
			 
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>法人身份证反面：</label>
			</div>
			<div class="form-item form-item-wd">
				 <c:if test="${empty editOrg.legalIdentityCardOpposite }">
					<img class="ewm-img" src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.legalIdentityCardOpposite }">
					<a href="<%=request.getContextPath()%>/system/download.html?fileName=${editOrg.legalIdentityCardOpposite }" ><img class="ewm-img" src="${editOrg.legalIdentityCardOpposite }" /></a>
				</c:if>
				 <input type="hidden" name="legalIdentityCardOpposite" value="${editOrg.legalIdentityCardOpposite }"/>
			</div>
		  </div>
		  
		  <div class="form-group">
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>营业执照：</label>
			</div>
			<div class="form-item form-item-wd">
				 <c:if test="${empty editOrg.businessLicense }">
					<img class="ewm-img" src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.businessLicense }">
					<a href="<%=request.getContextPath()%>/system/download.html?fileName=${editOrg.businessLicense }" ><img class="ewm-img" src="${editOrg.businessLicense }" /></a>
				</c:if>
				<input type="hidden" name="businessLicense" value="${editOrg.businessLicense }"/>
			</div>
			 
			<div class="form-item">
				<label class="form-lal"><i class="text-danger">*</i>开户许可证：</label>
			</div>
			<div class="form-item form-item-wd">
				 <c:if test="${empty editOrg.accountPermit }">
					<img class="ewm-img" src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty editOrg.accountPermit }">
					<a href="<%=request.getContextPath()%>/system/download.html?fileName=${editOrg.accountPermit }" ><img class="ewm-img" src="${editOrg.accountPermit }" /></a>
				</c:if>
				 <input type="hidden" name="accountPermit" value="${editOrg.accountPermit }"/>
			</div>
		  </div>
              <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="button" name="" value="返回" onclick="myClose()"/>
                      <input class="form-btn" type="button" name="" value="下载全部" onclick="location.href='<%=request.getContextPath()%>/system/downloadorg.html?id=${editOrg.id }'"/>                 
              </div>
          </form>
          </section>
      
      <script type="text/javascript">
	 	
	 	      function myClose(){
	 	    	 changeRightMenu('<%=request.getContextPath()%>/org/list.html');
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