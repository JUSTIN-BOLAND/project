function loginOut(){
    window.name="2";
	$("#loginOutForm").submit();
}

function getFormInfo(formId){  
	var info = new Array();    
	getInputInfo(formId,info) + getSelectInfo(formId,info)
	var params = "";
	for (var j = 0; j < info.length; j++){  
		if(j==0){
			params = "?"+info[j];
		}else{
			params = params + "&"+info[j];
		}
	}
    return params;   
}  

function getInputInfo(formId,info){  
	var form = document.getElementById(formId,info);   
	var elements = new Array();    
	var tagElements = form.getElementsByTagName('input');    
    for (var j = 0; j < tagElements.length; j++){  
    	var tagElement = tagElements[j];
    	if(tagElement.type.toLowerCase() =='text'){
    		elements.push(tagElement);  
    	}
    	if(tagElement.type.toLowerCase() =='hidden'){
    		elements.push(tagElement);  
    	}
    }  
    for (var i = 0; i < elements.length; i++){  
         var element = elements[i]
         var name = element.name;    
         var value = encodeURIComponent(element.value);
         if("" != value){
        	 info.push(name+"="+value);  
         }
    }  
}  

function getSelectInfo(formId,info){  
	var form = document.getElementById(formId);    
	var tagElements = form.getElementsByTagName('select');    
    var params = "";
    for (var i = 0; i < tagElements.length; i++){  
         var select = tagElements[i];
         var name = select.name;    
         var value = select.options[select.selectedIndex].value;
         if("" != value){
        	 info.push(name+"="+value);  
         }
    }  
}  
		

//查询
function query(url){
	 $("#exportQuery").attr("disabled", true);
	 var params = getFormInfo('query_from');
	 console.log("params="+params);
	 url = url+params;
	 $("#page_data").empty();
	 $("#page_data").load(url);
}

//分页请求
function toPage(url,pageNo){
	 var params = getFormInfo('query_from');
	 url = url+params+"&pageNo="+pageNo;
	 $("#page_data").load(encodeURIComponent(url));
}


$(function(){
    $(".ewm-img").on(
     {"mouseenter":function(){
       var evm_offset=$(this).offset();
       $("<div class='img-da'><img src="+ $(this).attr("src")+" alt=''></div>").appendTo($("body"));
       $(".img-da").css({left:evm_offset.left+50,top:evm_offset.top-100})


    },
     "mouseleave":function(){
       $(".img-da").remove();
     }
   });
    
    

  });
