<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/login.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/button.css">

    <div class="mui-card">
        <form class="form-4" id="formadd" style="width:400px;" action="<%=request.getContextPath()%>/trade/modify.html">
          <input type="hidden" id="id" name="id" value="${sessionScope.card.id}">
            <table class="userCenterTable">
                <tr><td>卡号</td><td>${sessionScope.card.cardNo}</td>
                     <td rowspan="6" align="right" valign="top">
                       <%--  <span class="userCenterIcon" style="font-size:12px;display:block;width:50px;height:60px;">用户中心 </span>--%>

                         <div style="display:block;position:relative;width:50px; height:60px;">

                             <a href="<%=request.getContextPath()%>/trade/toUserCenter.html?deviceId=${device.id}&cardId=${card.id}">
                                 <img src="<%=request.getContextPath()%>/man/images/mobile/user.ico" width="50" height="50">

                             </a>
                             <span style="color:black;display:block;font-size:12px;width:48px; height:12px; line-height:12px; position:absolute; top:80%; left:50%; margin-left:-24px; ">用户中心</span>


                         </div>

                     </td></tr>

                <tr>
                    <td >姓名</td>
                    <td><input type="text" name="name"  id="name" value="${sessionScope.card.name}" placeholder="姓名" datatype="name" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的姓名格式不对">
                    </td>
                </tr>
                <tr>
                    <td >手机号</td>
                    <td><input type="text" name="mobile"   id="mobile"  value="${sessionScope.card.mobile}" placeholder="手机号" datatype="m" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的手机号格式不对">
                    </td>
                </tr>
                <tr>
                    <td >验证码</td>
                    <td>
                        <input type="text" name="validCode"   id="validCode"  style="float:left;width:145px;margin-right:5px;"  placeholder="验证码" datatype="n{6}" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入6位的数字验证码">
                        <span class="regCheckBut" id="sendSmsCode" domainUrl="<%=request.getContextPath()%>" optType="1">获取校验码</span>
                        <span class="regCheckBut" id="SmsCodeTime" style="display:none;"><del>60</del>秒后重新获取</span>
                    </td>
                </tr>
                <tr>
                    <td >身份证</td>
                    <td>
                        <input type="text" name="idCardNo"   id="idCardNo"  value="${sessionScope.card.idCardNo}" placeholder="身份证" datatype="idCard" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的身份证格式不对">
                    </td>
                </tr>
                <tr >
                    <td colspan="2"><input type="button" name="submitBtn" id="submitBtn"  value="确认"></td>
                </tr>
            </table>

        </form>
    </div>
<script type="text/javascript">

    //表单验证
    $(function() {

        $("title").html("修改资料");
        $("#submitBtn").unbind('click', function () {
        });
        $("#submitBtn").bind('click', function () {
            if(!f_validForm()) return false;
            if($("#passwd").val() != $("#repasswd").val()){
                layer.msg('两次输入的密码不一致！', {
                    icon: 5
                });
                return false;
            }



            f_ajaxUpdate("<%=request.getContextPath()%>","modify");
        });



    });





</script>
<%@ include file="footer.jsp" %>