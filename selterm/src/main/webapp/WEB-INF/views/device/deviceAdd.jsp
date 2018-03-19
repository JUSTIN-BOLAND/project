<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
    .selectbox {
        width: 500px;
        height: 220px;
        margin: 0px auto;
    }
    .selectbox div {
        float: left;
    }
    .selectbox .select-bar select {
        width: 160px;
        height: 200px;
        border: 1px #A0A0A4 solid;
        padding: 4px;
        font-size: 14px;
        font-family: "microsoft yahei";
    }
    .btn-bar p .btn {
        width: 50px;
        height: 30px;
        cursor: pointer;
        font-family: simsun;
        font-size: 14px;
    }
    .selectbox .select-bar {
        padding: 0 20px;
    }
    .btn-bar p {
        margin-top: 16px;
    }
</style>
      
        <section class="navigation">
          <span>您当前所在的位置：</span><a  href="#" title="">设备管理</a>/<i>添加设备</i>
        </section>
        <section class="main-cont">
        <div class="article">
              <form class="form-add" id="formadd" action=""   method="post" >
                  <input class="form-txt" type="hidden" name="id" value="${device.id}">

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>设备分类：</label>
                  <select   id="deviceType"  name="deviceType" class="form-sel">

                      <c:forEach items="${deviceTypes }" var="item" varStatus="idx">
                          <c:choose>
                            <c:when test="${item.code  == device.deviceType }">
                                <option value="${item.code }" selected>${item.name }</option>
                            </c:when>
                             <c:otherwise>
                                 <option value="${item.code }">${item.name }</option>
                             </c:otherwise>

                          </c:choose>

                      </c:forEach>
                  </select>
                  <span class="form-yz Validform_checktip"></span>
              </div>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>设备名称：</label>
                  <input class="form-txt form-adv-txt" type="text" name="deviceName" value="${device.deviceName}"
                         style="width: 18%;" datatype="*,uniqueDeviceName" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的设备名称不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>经销商名称：</label>
                  <select   id="dealerId"  name="dealerId" class="form-sel">

                      <c:forEach items="${dealers }" var="item" varStatus="idx">
                          <c:choose>
                            <c:when test="${item.id  == device.dealerId }">
                                <option value="${item.id }" selected>${item.dealerName }</option>
                            </c:when>
                             <c:otherwise>
                                 <option value="${item.id }">${item.dealerName }</option>
                             </c:otherwise>

                          </c:choose>

                      </c:forEach>
                  </select>
                  <span class="form-yz Validform_checktip"></span>
              </div>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>机器编号：</label>
                  <input class="form-txt form-adv-txt" type="text" name="deviceCode" value="${device.deviceCode}"
                         style="width: 18%;" datatype="serial,uniqueDeviceCode" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入4位数字的设备序列号">
                  <span class="form-yz Validform_checktip"></span>
              </div>

 <div class="form-group">
                  <label class="form-lal"><i class="text-danger">*</i>装机地址：</label>
                  <input class="form-txt form-adv-txt" type="text" name="installAddress" value="${device.installAddress}"
                         style="width: 18%;" datatype="*" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的装机地址不对">
                  <span class="form-yz Validform_checktip"></span>
              </div>

            <div class="form-group">
                  <label class="form-lal" ><i class="text-danger">*</i>绑定充值套餐：</label>
                  <select   id="flag"  name="flag" multiple="multiple" class="form-sel" style="width:206.5px;height:150px;">
                  </select>
                 <input class="form-btn"   type="button" name="" value="选择" onclick="f_show()"/>
                  <span class="form-yz Validform_checktip"></span>
              </div>
              <div class="form-group">
                      <label class="form-lal"><i class="text-danger">*</i>提示：</label>

                      <textarea class="form-cont memowidth" name="hints" value="" rows="4"
                                style="width: 18%;" datatype="*" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入提示"></textarea>
                      <span class="form-yz Validform_checktip"></span>
                  </div>
            <div class="form-group">
                      <label class="form-lal">&nbsp;</label>
                      <input class="form-btn" type="submit" name="" value="确定"/>
                      <input class="form-btn" type="button" name="" value="取消"  onclick="myClose()"/>
              </div>
              
          </form>
            <!-- 选择套餐 -->
            <div class="modal fade" id="selectPlan" tabindex="-1" role="dialog"
                 aria-labelledby="myLargeModalLabel" >
                <div class="modal-dialog modal-lg" style="width:580px;">
                    <div class="modal-content">
                        <div class="modal-header" style="background:darkblue;">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel" style="color:white;">选择套餐</h4>
                        </div>
                        <div class="model-box">
                            <form class="form-add"
                                  action="<%=request.getContextPath()%>/teQrcode/bindSaler.html"
                                  method="post" accept-charset="utf-8">
                                <input type="hidden" id="qrCodeId" name="qrCodeId">
                                <div class="selectbox">
                                    <div class="select-bar">
                                        <select multiple="multiple" id="select1">
                                            <c:forEach items="${plans }" var="item" varStatus="idx">

                                                        <option value="${item.id }">${item.planName }</option>

                                            </c:forEach>

                                        </select>
                                    </div>
                                    <div class="btn-bar">
                                        <p><span id="add"><input type="button" class="btn" value=">" title="移动选择项到右侧"></span></p>
                                        <p><span id="add_all"><input type="button" class="btn" value=">>" title="全部移到右侧"></span></p>
                                        <p><span id="remove"><input type="button" class="btn" value="<" title="移动选择项到左侧"></span></p>
                                        <p><span id="remove_all"><input type="button" class="btn" value="<<" title="全部移到左侧"></span></p>
                                    </div>
                                    <div class="select-bar">
                                        <select multiple="multiple" id="select2"><option value="信息发布员">信息发布员</option></select>
                                    </div>
                                    <span class="form-yz Validform_checktip"></span>
                                </div>


                                <div class="form-group" style="text-align:center;">
                                    <label class="form-lal"  style="width:0px;">&nbsp;</label>
                                    <input class="form-btn"   type="button" name="" onclick="return f_selectPlan();" value="确定" />
                                    <input class="form-btn"   type="button" name="" value="取消" onclick="f_close()"/>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
          </div>
            <script type="text/javascript">
                $(function(){
                    //移到右边
                    $('#add').click(function(){
                        //先判断是否有选中
                        if(!$("#select1 option").is(":selected")){
                            //alert("请选择需要移动的选项")
                            layer.msg('请选择需要移动的选项！', {
                                icon : 1
                            });

                        }
                        //获取选中的选项，删除并追加给对方
                        else{
                            $('#select1 option:selected').appendTo('#select2');
                        }
                    });

                    //移到左边
                    $('#remove').click(function(){
                        //先判断是否有选中
                        if(!$("#select2 option").is(":selected")){
                            //alert("请选择需要移动的选项")
                            layer.msg('请选择需要移动的选项！', {
                                icon : 1
                            });
                        }
                        else{
                            $('#select2 option:selected').appendTo('#select1');
                        }
                    });

                    //全部移到右边
                    $('#add_all').click(function(){
                        //获取全部的选项,删除并追加给对方
                        $('#select1 option').appendTo('#select2');
                    });

                    //全部移到左边
                    $('#remove_all').click(function(){
                        $('#select2 option').appendTo('#select1');
                    });

                    //双击选项
                    $('#select1').dblclick(function(){ //绑定双击事件
                        //获取全部的选项,删除并追加给对方
                        $("option:selected",this).appendTo('#select2'); //追加给对方
                    });

                    //双击选项
                    $('#select2').dblclick(function(){
                        $("option:selected",this).appendTo('#select1');
                    });

                });
            </script>
        </section>


   
   <script type="text/javascript">
       function f_selectPlan() {
           $("#flag").empty();
           $('#select2 option').appendTo('#flag');
           f_close();
       }
       function f_close(){
           $("#selectPlan").modal('hide');
       }
       function f_show(){
           $("#select2").empty();
           $("select[name=flag] option").each(function(){

               $("#select1 option[value='"+$(this).val()+"']").remove();
               $("#select2").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
           });



           $("#selectPlan").modal('show');
       }
       //表单验证
       $(function(){

           $('#flag').click(function () {
               f_show();
           });

		    $(".form-add").Validform({tiptype:3,
                datatype:{
                    "uniqueDeviceName":function(gets,obj,curform,regxp){
                        /*参数gets是获取到的表单元素值，
                         obj为当前表单元素，
                         curform为当前验证的表单，
                         regxp为内置的一些正则表达式的引用。*/
                        var falg = false;
                        $.ajax({
                            url: '<%=request.getContextPath()%>/device/uniqueDeviceName.html?deviceName='+gets,
                            context: document.body,
                            async: false,
                            timeout:5000,
                            success: function(date){
                                falg = date;
                            }
                        });
                        return falg;
                    },
                    "uniqueDeviceCode":function(gets,obj,curform,regxp){
                        /*参数gets是获取到的表单元素值，
                         obj为当前表单元素，
                         curform为当前验证的表单，
                         regxp为内置的一些正则表达式的引用。*/
                        var falg = false;
                        $.ajax({
                            url: '<%=request.getContextPath()%>/device/uniqueDeviceCode.html?deviceCode='+gets+'&dealerId='+$("#dealerId").val(),
                            context: document.body,
                            async: false,
                            timeout:5000,
                            success: function(date){
                                falg = date;
                                if(!falg){
                                    $(obj).attr("errormsg","输入的设备序列号已存在");
                                }
                            }
                        });
                        return falg;
                    },
                 "userName":/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,6}$/,
                 "account":/^[^\u4E00-\u9FA5\uf900-\ufa2d]{2,10}$/,
                 "money"  : /^\d+(\.\d{2})?$/,
                 "serial"  : /^\d{4}$/,
                 "busLicenceNo":/^.{15,18}$/
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
		 		changeRightMenu('<%=request.getContextPath()%>/device/list.html');
		 	}
          function f_add(){
              $("select[name=flag] option").each(function(){
                  $(this).prop("selected", 'selected');
              });
              //$("#flag").removeAttr("disabled");
                $.ajax({
                    url: "<%=request.getContextPath()%>/device/add.html",
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
                            $("#bodyRight").load("<%=request.getContextPath()%>/device/list.html");
                            layer.msg('添加成功！', {
                                icon : 1
                            });
                        } else {
                            layer.msg('添加失败！', {
                                icon : 5
                            });
                        }
                       // $("#flag").attr("disabled","disabled");
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