<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/login.css">
    <div class="mui-card">
        <form class="form-4" id="formadd"  method="post" style="width: 400px;" action="<%=request.getContextPath()%>/trade/register.html">
            <input type="hidden" id="deviceId" name="deviceId" value="${deviceId}">
            <table borer="0">
            <tr>
                <td style="width:70px;">卡号</td>
                <td ><input type="text" name="cardNo"  id="cardNo"  placeholder="卡号" datatype="n{7}" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的卡号格式不对或者卡号已经存在">
                </td>
            </tr>
            <tr>
                <td >密码</td>
                <td><input type="password" name="passwd"   id="passwd"  placeholder="密码" datatype="account" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的密码格式不对">
                </td>
            </tr>
            <tr>
                <td >确认密码</td>
                <td><input type="password" name="repasswd"   id="repasswd"  placeholder="确认密码" datatype="account" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的密码格式不对">
                </td>
            </tr>
            <tr>
                <td >姓名</td>
                <td><input type="text" name="name"  id="name"  placeholder="姓名" datatype="name" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入的【2-6】位汉字">
                </td>
            </tr>
            <tr>
                <td >手机号</td>
                <td><input type="text" name="mobile"   id="mobile"  placeholder="手机号" datatype="m" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入的11位手机号">
                </td>
            </tr>
            <tr>
                <td >验证码</td>
                <td>
                    <input type="text" name="validCode"   id="validCode"  style="float:left;width:145px;margin-right:5px;"  placeholder="验证码" datatype="n{6}" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入6位的数字验证码">
                    <span class="regCheckBut" id="sendSmsCode" domainUrl="<%=request.getContextPath()%>" optType="0">获取校验码</span>
                    <span class="regCheckBut" id="SmsCodeTime" style="display:none;"><del>60</del>秒后重新获取</span>
                </td>
            </tr>
            <tr>
                <td >身份证</td>
                <td>
                    <input type="text" name="idCardNo"   id="idCardNo"  placeholder="身份证" datatype="idCard" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="请输入的18位身份证">
                </td>
            </tr>
            <tr >
                <td colspan="2"><input type="button" name="submitBtn" id="submitBtn"  value="立即注册"></td>
            </tr>
            </table>
        </form>
    </div>
<script type="text/javascript">


    //表单验证
    $(function() {

        //$('.mui-title').html("新用户注册");
        $("title").html("新用户注册");
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
            if (f_isExistCardNo("<%=request.getContextPath()%>")) {
                layer.msg('卡号已存在！', {
                    icon: 5
                });
                return false;
            }
            if (f_isExistCardName("<%=request.getContextPath()%>")) {
                layer.msg('姓名已存在！', {
                    icon: 5
                });
                return false;
            }



         $("#formadd").submit();
        });
    });



</script>
<%@ include file="footer.jsp" %>