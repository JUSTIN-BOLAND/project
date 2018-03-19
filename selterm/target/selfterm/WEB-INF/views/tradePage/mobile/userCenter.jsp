<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/login.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/button.css">
<%--<div class="ban-pic">

    <div class="right-msg">
        <dl>
            <dd><span>卡号:</span>
                <p>${card.cardNo}</p>
            </dd>
            <dd>
                <span>姓名:</span>
                <p>${card.name}</p>
            </dd>
        </dl>
    </div>
    <div class="right-head-pic" style="position:relative;">
        <a><img src="<%=request.getContextPath()%>/man/images/mobile/user.ico">

        </a>

    </div>
    <span style="float: right;font-size: 16px;margin-left: 17px;margin-right: 15px;">用户中心 </span>
</div>--%>
    <div class="mui-card">
        <form class="form-4" id="formadd" style="width:400px;" action="<%=request.getContextPath()%>/trade/pay.html">
          <%--  <input type="hidden" id="deviceId" vlaue="${deviceId}">--%>
            <table class="userCenterTable">
                <tr><td>卡号</td><td>${sessionScope.card.cardNo}</td>
                     <td rowspan="6" align="right" valign="top">
                       <%--  <span class="userCenterIcon" style="font-size:12px;display:block;width:50px;height:60px;">用户中心 </span>--%>

                         <div style="display:block;position:relative;width:50px; height:60px;">
                             <img src="<%=request.getContextPath()%>/man/images/mobile/user.ico" width="50" height="50">
                             <span style="color:black;display:block;font-size:12px;width:48px; height:12px; line-height:12px; position:absolute; top:80%; left:50%; margin-left:-24px;">用户中心</span>


                         </div>
                           <br>
                           <input type="button" name="close" id="close" style="margin-bottom: 20px;" value="退出">  <br>
                           <input type="button" name="modifyPwd"  id="modifyPwd" style="margin-bottom: 20px;" value="修改密码">  <br>
                           <input type="button" name="reportLossCard"  id="reportLossCard" style="margin-bottom: 20px;" value="卡挂失">  <br>
                           <input type="button" name="modify" id="modify"  style="margin-bottom: 20px;" value="修改资料">
                     </td></tr>
                <tr><td>状态</td>
                    <td>
                        <c:if test="${sessionScope.card.status == '0'}">
                            启用
                        </c:if>
                        <c:if test="${sessionScope.card.status == '1'}" >
                            <font color="red">
                                禁用
                            </font>
                        </c:if>
                    </td></tr>
                <tr><td>账户余额</td><td>${sessionScope.card.balance}</td></tr>
                <tr><td>姓名</td><td>${sessionScope.card.name}</td></tr>
                <tr><td>手机号</td><td>${sessionScope.card.mobile}</td></tr>
                <tr><td>身份证</td><td>${sessionScope.card.idCardNo}</td></tr>
            </table>
            <p style="margin-top:30px;">
                <input type="button" name="onlinePayBtn" id="onlinePayBtn" value="在线支付">
            </p>
        </form>
    </div>
<script type="text/javascript">

    //表单验证
    $(function() {

        //$('.mui-title').html("在线充值");
        $("title").html("在线充值");
        $("#onlinePayBtn").unbind('click', function () {
        });
        $("#onlinePayBtn").bind('click', function () {
            window.location.href="<%=request.getContextPath()%>/trade/toRecharge.html?deviceId=${sessionScope.device.id}";
        });

        $("#close").unbind('click', function () {
        });
        $("#close").bind('click', function () {

            layer.confirm('是否退出？', {
                        btn: ['退出','取消'] //按钮
                    },
                    function(){
                        window.location.href="<%=request.getContextPath()%>/trade/logOut.html?deviceId=${sessionScope.device.id}";
                    }
            );
        });

        $("#modifyPwd").unbind('click', function () {
        });
        $("#modifyPwd").bind('click', function () {
            window.location.href="<%=request.getContextPath()%>/trade/toModifyPwd.html?deviceId=${sessionScope.device.id}";
        });

        $("#reportLossCard").unbind('click', function () {
        });
        $("#reportLossCard").bind('click', function () {
            window.location.href="<%=request.getContextPath()%>/trade/toReportLossCard.html?deviceId=${sessionScope.device.id}";
        });

        $("#modify").unbind('click', function () {
        });
        $("#modify").bind('click', function () {
            window.location.href="<%=request.getContextPath()%>/trade/toModify.html?deviceId=${sessionScope.device.id}";
        });
    });



    function f_isExistCard(){
        var v_result = false;
        var v_cardNo = $("#cardNo").val();

        $.ajax({
            url: '<%=request.getContextPath()%>/trade/isExistCard.html?cardNo='+v_cardNo,
            context: document.body,
            async: false,
            timeout:5000,
            success: function(date){
                v_result = date;
            }
        });
        return v_result;
    }

    function f_validPasswd(){
        var v_result = false;
        var v_cardNo = $("#cardNo").val();
        var v_passwd = $("#passwd").val();
        $.ajax({
            url: '<%=request.getContextPath()%>/trade/validPwd.html?cardNo='+v_cardNo+"&passwd="+v_passwd,
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