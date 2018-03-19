<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/login.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/button.css">

    <div class="mui-card">
        <form class="form-4" id="formadd" style="width:400px;" action="<%=request.getContextPath()%>/trade/resetPwd.html">
          <%--  <input type="hidden" id="deviceId" vlaue="${deviceId}">--%>
            <table class="userCenterTable">
                <tr><td></td><td></td>
                     <td rowspan="6" align="right" valign="top">
                       <%--  <span class="userCenterIcon" style="font-size:12px;display:block;width:50px;height:60px;">用户中心 </span>--%>

                         <div style="display:block;position:relative;width:50px; height:60px;">
                             <a href="<%=request.getContextPath()%>/trade/toUserCenter.html">
                                <img src="<%=request.getContextPath()%>/man/images/mobile/user.ico" width="50" height="50">
                             </a>
                             <span style="color:black;display:block;font-size:12px;width:48px; height:12px; line-height:12px; position:absolute; top:80%; left:50%; margin-left:-24px;">用户中心</span>


                         </div>

                     </td></tr>
                <tr>
                    <td style="width:70px;">卡号</td>
                    <td ><input type="text" name="cardNo"  id="cardNo"  placeholder="卡号" datatype="n{5}" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的卡号格式不对">
                    </td>
                </tr>
                <tr>
                    <td >姓名</td>
                    <td><input type="text" name="name"  id="name"  placeholder="姓名" datatype="name" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的姓名格式不对">
                    </td>
                </tr>
                <tr>
                    <td >手机号</td>
                    <td><input type="text" name="mobile"   id="mobile"  placeholder="手机号" datatype="m" sucmsg="&nbsp;" nullmsg="不能为空" errormsg="输入的手机号格式不对">
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

        $("title").html("重置密码");
        $("#submitBtn").unbind('click', function () {
        });
        $("#submitBtn").bind('click', function () {
            if(!f_validForm()) return false;
            v_result = f_validReset();
            if(v_result!=0){
                if(v_result==1) v_msg="输入的卡号不存在";
                else if(v_result==2) v_msg="输入的姓名不存在";
                else if(v_result==3) v_msg="输入的手机号码不存在";
                layer.msg(v_msg, {
                    icon: 5
                });
                return false;
            }



            f_ajaxUpdate("<%=request.getContextPath()%>","resetPwd");
        });
        function f_validReset(){
            var v_result = 0;
             console.log("[f_validReset]:"+'<%=request.getContextPath()%>/trade/validReset.html?cardId='+$("#cardNo").val()+'&mobile='+$("#mobile").val()+'&name='+$("#name").val())

            $.ajax({
                url: '<%=request.getContextPath()%>/trade/validReset.html?cardNo='+$("#cardNo").val()+'&mobile='+$("#mobile").val()+'&name='+$("#name").val(),
                context: document.body,
                async: false,
                timeout:5000,
                success: function(date){
                    v_result = date;
                }
            });
            return v_result;
        }


    });





</script>
<%@ include file="footer.jsp" %>