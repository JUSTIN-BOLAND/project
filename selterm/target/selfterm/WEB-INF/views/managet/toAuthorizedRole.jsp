<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>

<script type="text/javascript">
function myClose(){
	$("#tjtree").modal('hide');
}
</script>

</head>
<body>
		
        <div class="model-tjsq" style="width: 350px; height: 300px; margin: 0 auto; overflow: auto;border:1px solid #ccc;
         border-radius: 5px;">
          <ul id="treeDemo" class="ztree"></ul>
        </div>

        <div class="form-group" style="margin-top: 20px;text-align:center">
              <input class="form-btn" type="button" value="保存" onclick="authorizedRoles(${roleId })" />
              <input class="form-btn" type="button" value="关闭" onclick="myClose()" />
        </div>
		<script type="text/javascript">
    	 	var setting = {
    	          check: {
    	            enable: true,
    	            chkStyle: "checkbox",
    	            chkboxType: { "Y": "ps", "N": "ps" }
    	          },
    	          data: {
    	            simpleData: {
    	              enable: true,
    	              idKey: "id",
  	      			pIdKey: "pId",
  	      			rootPId: 0
    	            }
    	          },
    	          view: {
    	      		addDiyDom: addDiyDom
    	      	}
    	     

    	    };
    	 	function addDiyDom(treeId, treeNode) {
    	 		var aObj = $("#" + treeNode.tId + "_a");
    	 		if ($("#diyBtn_"+treeNode.id).length>0) return;
    	 		var editStr = "<input type='hidden' id='treenode' name='treenode' value='" +treeNode.id+ "'  />"
    	 		aObj.append(editStr);
    	 	};

			
    	 	var menuList = ${menuList};
    	 	console.log("menuList="+menuList);
    	 	var arrayObj = new Array();
    	 	for(var i=0;i<menuList.length;i++){
    	    	var obj=new Object(); 
    	    	obj.id = menuList[i].menuId;
    	    	obj.pId = menuList[i].parentMenuId;
    	    	obj.name = menuList[i].menuName;
    	    	if(menuList[i].checked){
    	    		obj.checked = true;
    	    	}else{
    	    		obj.checked = false;
    	    	}
    	    	obj.open = true;
    	    	arrayObj.push(obj);
    	 		
    	 	}
   	        $.fn.zTree.init($("#treeDemo"), setting, arrayObj);
        </script>
		       
	</body>
	</html>