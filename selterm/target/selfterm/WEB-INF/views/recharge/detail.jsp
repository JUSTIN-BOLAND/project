<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

      
        <section class="navigation">
          <span>您当前所在的位置：</span><a  href="#" title="">充值明细</a>/<i>指令信息</i>
        </section>
        <section class="main-cont">
        <%--<div class="article" style="border:0;">
              <form class="form-add" id="formadd" action=""   method="post" >
                  <input class="form-txt" type="hidden" name="id" value="${recharge.id}">

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>机器名称：</label>
                  <input class="form-txt form-adv-txt" type="text" name="deviceName" value="${recharge.subMerchantId}"
                         style="width: 18%;"  readonly>
                  <span class="form-yz Validform_checktip"></span>
              </div>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>机器编号：</label>
                  <input class="form-txt form-adv-txt" type="text" name="deviceCode" value="${recharge.memo}"
                         style="width: 18%;" readonly>
                  <span class="form-yz Validform_checktip"></span>
              </div>
                  <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>卡号：</label>
                      <input class="form-txt form-adv-txt" type="text" name="cardNo" value="${recharge.operatorId}"
                             style="width: 18%;"  readonly>
                      <span class="form-yz Validform_checktip"></span>
                  </div>--%>
 <div class="form-group">
                  <label class="form-lal" style="text-align: left;width:480px;">发送指令：${recharge.sendCmd}</label>
                 <%-- <input class="form-txt form-adv-txt" type="text" name="sendCmd" value="${recharge.sendCmd}"
                         style="width: 60%;" readonly>
                  <span class="form-yz Validform_checktip"></span>--%>
              </div>

 <div class="form-group">
                  <label class="form-lal" style="text-align: left;width:480px;">接受指令：${recharge.recieveCmd}</label>
                 <%-- <input class="form-txt form-adv-txt" type="text" name="recieveCmd" value="${recharge.recieveCmd}"
                         style="width: 60%;"  readonly>
                  <span class="form-yz Validform_checktip"></span>--%>
              </div>


<div class="form-group">
                      <label class="form-lal">&nbsp;</label>

                      <input class="form-btn" type="button" name="" value="返回"  onclick="f_goBack()"/>
              </div>
              
          </form>
          </div>
        </section>


   
   <script type="text/javascript">

       //表单验证
	      $(function(){

		    $(".form-add").Validform({tiptype:3,
                datatype:{
                    "uniqueDealerName":function(gets,obj,curform,regxp){
                        /*参数gets是获取到的表单元素值，
                         obj为当前表单元素，
                         curform为当前验证的表单，
                         regxp为内置的一些正则表达式的引用。*/
                        var falg = false;
                        $.ajax({
                            url: '<%=request.getContextPath()%>/recharge/uniqueUpdateCardName.html?rechargeName='+gets,
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
                 "n5"  : /^\d{5}$/,
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
                    return true;                  }
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


	      function f_goBack(){
		 		//history.go(-1);
              changeRightMenu('<%=request.getContextPath()%>/recharge/list.html');
		 	}

</script>