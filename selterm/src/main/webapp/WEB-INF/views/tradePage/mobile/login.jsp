<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/login.css">
    <div class="mui-card">
        <form class="form-4" id="formadd"  method="post" action="<%=request.getContextPath()%>/trade/login.html">
            <input type="hidden" id="deviceId" name="deviceId" value="${deviceId}">
            <h1></h1>
            <p>
                <label >卡号</label>
                <input type="text" name="cardNo"  id="cardNo"  placeholder="卡号" datatype="n" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的卡号格式不对">
            </p>
            <p>
                <label >密码</label>
                <input type="password" name="passwd"   id="passwd"  placeholder="密码" datatype="account" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的密码格式不对">
            </p>

            <p>
                <input type="button" name="submitBtn" id="submitBtn"  value="登陆">
            </p>
            <p>
                <a href="<%=request.getContextPath()%>/trade/toResetPwd.html?deviceId=${deviceId}">重置密码</a>
                <a href="<%=request.getContextPath()%>/trade/toRegister.html?deviceId=${deviceId}" style="float:right;">新用户注册</a>
            </p>
        </form>
    </div>
<script type="text/javascript">

    //表单验证
    $(function() {

        //$('.mui-title').html("用户登陆");
        $("title").html("用户登陆");

        $("#submitBtn").unbind('click', function () {
        });
        $("#submitBtn").bind('click', function () {
            var v_cardNo = $("#cardNo").val();
            var v_passwd = $("#passwd").val();
            if (v_cardNo.length == 0 ) {
                layer.msg('卡号不能为空！', {
                    icon: 5
                });
                return false;
            }
            if (v_passwd.length == 0 ) {
                layer.msg('密码不能为空！', {
                    icon: 5
                });
                return false;
            }
            if (!f_isExistCardNo("<%=request.getContextPath()%>")) {
                layer.msg('卡号不存在！', {
                    icon: 5
                });
                return false;
            }
            if (!f_validPasswd()) {
                layer.msg('密码不对！', {
                    icon: 5
                });
                return false;
            }

         $("#formadd").submit();
        });
    });





    function f_validPasswd(){
        var v_result = false;
        var v_cardNo = $("#cardNo").val();
        var v_passwd = $("#passwd").val();

        return f_isExistAjax("<%=request.getContextPath()%>",'/trade/validPwd.html?cardNo='+v_cardNo+"&passwd="+v_passwd);
    }

</script>
<%@ include file="footer.jsp" %>