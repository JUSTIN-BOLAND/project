<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/login.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/button.css">

    <div class="mui-card">
        <form class="form-4" id="formadd" style="width:400px;" action="<%=request.getContextPath()%>/trade/modifyPwd.html">
            <input type="hidden" id="id" name="id" value="${sessionScope.card.id}">
            <table class="userCenterTable">
                <tr>
                     <td colspan="2" align="right" valign="top">
                       <%--  <span class="userCenterIcon" style="font-size:12px;display:block;width:50px;height:60px;">用户中心 </span>--%>

                         <div style="display:block;position:relative;width:50px; height:60px;">
                             <a href="<%=request.getContextPath()%>/trade/toUserCenter.html?deviceId=${device.id}&cardId=${card.id}">
                                 <img src="<%=request.getContextPath()%>/man/images/mobile/user.ico" width="50" height="50">

                             </a>
                             <span style="color:black;display:block;font-size:12px;width:48px; height:12px; line-height:12px; position:absolute; top:80%; left:50%; margin-left:-24px;">用户中心</span>


                         </div>

                     </td></tr>

                <tr>
                    <td >原密码</td>
                    <td><input type="text" name="oldPasswd"  id="oldPasswd"   placeholder="原密码" datatype="account" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的原密码格式不对">
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
            if(!f_validOldPwd()){
                layer.msg('输入的原密码不对！', {
                    icon: 5
                });
                return false;
            }
            if($("#passwd").val() != $("#repasswd").val()){
                layer.msg('两次输入的密码不一致！', {
                    icon: 5
                });
                return false;
            }



            f_ajaxUpdate("<%=request.getContextPath()%>","modify");
        });



    });

    function f_validOldPwd(){
        var v_result = false;


        $.ajax({
            url: '<%=request.getContextPath()%>/trade/validOldPwd.html?cardId=${sessionScope.card.id}&passwd='+$("#oldPasswd").val(),
            context: document.body,
            async: false,
            timeout:5000,
            success: function(date){
                v_result = date;
            }
        });
        return v_result;
    }



</script>
<%@ include file="footer.jsp" %>