<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form class="form-add" method="get" accept-charset="utf-8"
		action="<%=request.getContextPath()%>/advertise/savepayadv.html">

	<input type="hidden" name="id" value="${entity.id }">
	
	<c:if test="${not empty  merchantlist}">
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>商户名称：</label>
			<select name="merid" class="form-txt">
				<c:forEach items="${merchantlist }" var="merchant">
					
					<option value="${merchant.id }" <c:if test="${entity.merid eq merchant.id }">selected=selected</c:if> >
					${merchant.name }
					</option>
				</c:forEach>
			</select>
			 <span class="form-yz Validform_checktip"></span>
		</div>
		
		</c:if>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>广告名称：</label> <input
				class="form-txt" type="text" id="orgName" name="advname" value="${entity.advname }"
				datatype="*,s1-20" sucmsg="&nbsp;" nullmsg="不能为空"
				errormsg="数据已经拥有" /> <span class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>广告图片：</label> 
			<div class="form-item form-item-wd">
				<c:if test="${empty entity.advimage }">
					<img src="<%=request.getContextPath()%>/man/images/upload.png" />
				</c:if>
				<c:if test="${not empty entity.advimage }">
					<img src="${entity.advimage }" />
				</c:if>
				
			<input class="form-file" type="file" name="file" value="" id="storesopra" />
			<input type="hidden" name="advimage" value="${entity.advimage }" datatype="*" sucmsg="&nbsp;" nullmsg="不能为空"/>
			<span class="form-yz Validform_checktip"></span>
			</div>
			
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>广告链接：</label> <input
				class="form-txt" type="text" name="advlink" value="${entity.advlink }"
				datatype="*" sucmsg="&nbsp;" nullmsg="不能为空" /> <span
				class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal"><i class="text-danger">*</i>开始时间：</label> <input
				class="form-txt laydate-icon" type="text" name="starttime" value="<fmt:formatDate value="${entity.starttime }" pattern="yyyy-MM-dd HH:mm:ss" />" id="datestart222"
				datatype="*" sucmsg="&nbsp;" nullmsg="不能为空" /> <span
				class="form-yz Validform_checktip"></span>
		</div>
		<div class="form-group">
			<label class="form-lal "><i class="text-danger">*</i>结束时间：</label> <input
				class="form-txt laydate-icon" type="text" name="endtime" value="<fmt:formatDate value="${entity.endtime }" pattern="yyyy-MM-dd HH:mm:ss" />"  id="dateend222"
				datatype="*" sucmsg="&nbsp;" nullmsg="不能为空" /> <span
				class="form-yz Validform_checktip"></span>
		</div>

       <div class="form-group">
          <label class="form-lal"><i class="text-danger">*</i>广告位置：</label>
          <select name="advplace" id="orgId" class="form-txt" datatype="*"  sucmsg="&nbsp;" nullmsg="不能为空">
          	<option value="">请选择</option>
          	<c:if test="${empty entity.id }">
	          	<option value="0">全部</option>
          	</c:if>
          	<option value="1" ${entity.advplace eq '1' ?'selected="selected"':'' } >支付页面</option>
          	<option value="2" ${entity.advplace eq '2' ?'selected="selected"':'' }>付款成功页面</option>
          </select>
          <span
				class="form-yz Validform_checktip"></span>
       </div>        

		
		<div class="form-group">
			<label class="form-lal">&nbsp;</label> <input class="form-btn"
				type="submit" name="" value="保存" /> <input class="form-btn"
				type="button" name="" value="关闭" onclick="myClose()" />
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
						query('<%=request.getContextPath()%>/advertise/paylist.html');
										$("#tjdy").modal('hide');
									} else {
										layer.msg('添加失败！', {
											icon : 5
										});
									}
								}

							});
		});

		function myClose() {
			$("#tjdy").modal('hide');
		}
		
		function selectRole(){
			$("select[name=roleid]").empty();
			var id = document.getElementById("orgId").value;
			if(id==null || id==''){
				return;
			}
			$.ajax({ 
			    type: "POST",  
        		url: '<%=request.getContextPath()%>/userOperator/selectRole.html',
        		data: {"id":id},
        		async : true,
        		dataType: "json",
        		success: function(data){
        			
					var list =  eval(data);
					var str = '<option value="">请选择</option>';
					for(var i =0;i<list.length;i++){
						str += '<option value="'+list[i].id+'">'+list[i].rolename+'</option>';
					}
					$("select[name=roleid]").append(str);

        		}
        	}) 
			
		};
		
		function selectuser(){
			$("select[name=parentid]").empty();
			var id = $("#roleid").val();
			if(id==null || id==''){
				return;
			}
			$.ajax({ 
			    type: "POST",  
        		url: '<%=request.getContextPath()%>/userOperator/selectparentiduser.html',
        		data: {"id":id},
        		async : true,
        		dataType: "json",
        		success: function(data){
        			
					var list =  eval(data);
					var str = '<option value="">请选择</option>';
					for(var i =0;i<list.length;i++){
						str += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
					}
					$("select[name=parentid]").append(str);

        		}
        	}) 
			
		}
		
		
		var start = {
                elem: '#datestart222',
                format: 'YYYY-MM-DD hh:mm:ss',
                min: '1900-01-01 00:00:00', //设定最小日期为当前日期
                //max: laydate.now(), //最大日期
                istime: true,
                istoday: false,
                choose: function(datas){
                     end.min = datas; //开始日选好后，重置结束日的最小日期
                     end.start = datas //将结束日的初始值设定为开始日
                     $("#datestart222").blur();
                }
            };
            var end = {
                elem: '#dateend222',
                format: 'YYYY-MM-DD hh:mm:ss',
                min: '1900-01-01 00:00:00',
                //max: laydate.now(),
                istime: true,
                istoday: true,
                choose: function(datas){
                    start.max = datas; //结束日选好后，重置开始日的最大日期
                    $("#dateend222").blur();
                }
            };
            laydate(start);
            laydate(end);
		
            
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


</body>
</html>