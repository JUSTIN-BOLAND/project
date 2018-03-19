<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

      
        <section class="navigation">
          <span>您当前所在的位置：</span><a  href="#" title="">一卡通管理</a>/<i>发卡</i>
        </section>
        <section class="main-cont">
        <div class="article">
              <form class="form-add" id="formadd" action=""   method="post" >
                  <input class="form-txt" type="hidden" name="id" value="${card.id}">
                  <input class="form-txt" type="hidden" name="serviceType" value="1">
<%-- <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>批号：</label>
                  <input class="form-txt form-adv-txt" type="text" name="batchNo" value="${card.batchNo}"
                         style="width: 18%;" datatype="n2" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的批号不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>--%>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>卡号：</label>
                  <input class="form-txt form-adv-txt" type="text" name="cardNo" value="${card.cardNo}"
                         style="width: 18%;" datatype="n7,uniqueCardNo" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的卡号格式不对或者卡号已经存在">
                  <span class="form-yz Validform_checktip"></span>
              </div>
                  <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>管理密码：</label>
                      <input class="form-txt form-adv-txt iwidth" type="password" name="passwd"  plugin="passwordStrength"  value=""
                             style="width: 18%;" datatype="account" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入非汉字">
                      <span class="form-yz Validform_checktip"></span>
                  </div>

                  <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>重复密码：</label>
                      <input class="form-txt form-adv-txt iwidth" type="password" name="repasswd" recheck="passwd" value=""
                             style="width: 18%;" datatype="account" sucmsg="&nbsp;" nullmsg="请再次输入密码" errormsg="两次输入的密码不一致">
                      <span class="form-yz Validform_checktip"></span>
                  </div>
 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>姓名：</label>
                  <input class="form-txt form-adv-txt" type="text" name="name" value="${card.name}"
                         style="width: 18%;" datatype="userName,uniqueName" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入的【2-6】位汉字或者姓名已经存在">
                  <span class="form-yz Validform_checktip"></span>
              </div>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>手机号：</label>
                  <input class="form-txt form-adv-txt" type="text" name="mobile" value="${card.mobile}"
                         style="width: 18%;" datatype="m" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入的11位手机号">
                  <span class="form-yz Validform_checktip"></span>
              </div>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>账户余额：</label>
                  <input class="form-txt form-adv-txt" type="text" name="balance" value="${card.balance}"
                         style="width: 18%;" datatype="money" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的账户余额不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>

<%-- <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>充值账户余额：</label>
                  <input class="form-txt form-adv-txt" type="text" name="deposit" value="${card.deposit}"
                         style="width: 18%;" datatype="money" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的充值账户余额不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>--%>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>身份证号：</label>
                  <input class="form-txt form-adv-txt" type="text" name="idCardNo" value="${card.idCardNo}"
                         style="width: 18%;" datatype="idCard" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的身份证号不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>
<div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="确定"/>
                      <input class="form-btn" type="button" name="" value="取消"  onclick="myClose()"/>
              </div>
              
          </form>
          </div>
        </section>


   
   <script type="text/javascript">

       //表单验证
	      $(function(){

		    $(".form-add").Validform({tiptype:3,
                datatype:{
                    "uniqueCardNo":function(gets,obj,curform,regxp){
                        /*参数gets是获取到的表单元素值，
                         obj为当前表单元素，
                         curform为当前验证的表单，
                         regxp为内置的一些正则表达式的引用。*/
                        var falg = false;
                        $.ajax({
                            url: '<%=request.getContextPath()%>/card/uniqueCardNo.html?cardNo='+gets,
                            context: document.body,
                            async: false,
                            timeout:5000,
                            success: function(date){
                                falg = date;
                            }
                        });
                        return falg;
                    },
                    "uniqueName":function(gets,obj,curform,regxp){
                        /*参数gets是获取到的表单元素值，
                         obj为当前表单元素，
                         curform为当前验证的表单，
                         regxp为内置的一些正则表达式的引用。*/
                        var falg = false;
                        $.ajax({
                            url: '<%=request.getContextPath()%>/card/uniqueName.html?cardName='+gets,
                            context: document.body,
                            async: false,
                            timeout:5000,
                            success: function(date){
                                falg = date;
                            }
                        });
                        return falg;
                    },
                 "userName":/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,6}$/,
                 "account":/^[^\u4E00-\u9FA5\uf900-\ufa2d]{2,10}$/,
                 "money"  : /^\d+(\.\d{1,2})?$/,
                 "serial"  : /^\d{8}$/,
                 "n2"  : /^\d{2}$/,
                 "n7"  : /^\d{7}$/,
                 "busLicenceNo":/^.{15,18}$/,
                 idCard:function(gets,obj,curform,regxp){
                    var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
                       var iSum=0 ;  
                    var info="" ;
                     var sId= gets;
                    if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";   
                    sId=sId.replace(/x$/i,"a");   
                    if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";   
                    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));   
                    var d=new Date(sBirthday.replace(/-/g,"/")) ;  
                    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";   
                    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
                    if(iSum%11!=1) return "你输入的身份证号非法";   
                    return true;
                  }
                },
		    	ajaxPost:true,
                beforeSubmit:function(curform){
                    //alert("1");
                    f_add();
                    //alert("2");
                    return false;
                },
				callback:function(data){
                    alert("1: "+data.toString());
                    return false;

				}

		    });
		  });


	      function myClose(){
		 		changeRightMenu('<%=request.getContextPath()%>/card/list.html');
		 	}
          function f_add(){
                $.ajax({
                    url: "<%=request.getContextPath()%>/card/add.html",
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#formadd')[0]),
                    processData: false,
                    contentType: false,
                    dataType:"json",
                   /* beforeSend: function(){
                        uploading = true;
                    },*/
                    success : function(data) {
                        //alert("f_addDealer: "+data.success);
                        if (data.success) {
                            $("#bodyRight").empty();
                            $("#bodyRight").load("<%=request.getContextPath()%>/card/list.html");
                            layer.msg('修改成功！', {
                                icon : 1
                            });
                        } else {
                            layer.msg('修改失败！', {
                                icon : 5
                            });
                        }
                        //uploading = false;
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        // 状态码
                        console.log(XMLHttpRequest.status);
                        // 状态
                        console.log(XMLHttpRequest.readyState);
                        // 错误信息
                        console.log(textStatus);
                    }
                });
            }
</script>