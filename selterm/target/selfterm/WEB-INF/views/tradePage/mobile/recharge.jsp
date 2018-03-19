<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.deyi.entity.UserInfo" %>
<%@ page import="com.deyi.util.UserManage" %>
<%
    UserInfo userInfo = UserManage.getCurrUserInfo();

%>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/login.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/man/css/mobile/button.css">
<script type="text/javascript"  src="<%=request.getContextPath()%>/man/js/pay/jsPay.js"></script>
<div class="ban-pic">

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
        <a href="<%=request.getContextPath()%>/trade/toUserCenter.html?deviceId=${device.id}&cardId=${card.id}">
            <img src="<%=request.getContextPath()%>/man/images/mobile/user.ico">

        </a>

    </div>
    <span style="float: right;font-size: 16px;margin-left: 17px;margin-right: 15px;" id="userCenterSpan">用户中心 </span>
</div>
    <div class="mui-card">
        <form class="form-4" id="formadd" style="width:350px;" action="<%=request.getContextPath()%>/trade/pay.html">
            <input type="hidden" id="deviceId" value="${device.id}">
            <input type="hidden" id="selectPlan" >

            <c:forEach items="${rechargePlanList }" var="item" varStatus="idx">


                <c:choose>
                        <c:when test="${idx.index % 2  == 0 }">
                            <p style="float:left;margin-top:20px;margin-right:20px;">
                                <a href="#" class="a_3d" style="display:inline-block;text-align:center;width:150px;"
                                   id="${item.id}"  data="${item.price}" pdata="${item.actualAmount}">${item.planName}</a>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p style="margin-top:20px;margin-left:20px;">
                                <a href="#" class="a_3d"  style="display:inline-block;width:150px;text-align:center;"
                                   id="${item.id}"  data="${item.price}" pdata="${item.actualAmount}">${item.planName}</a>
                            </p>
                        </c:otherwise>
                    </c:choose>





            </c:forEach>
            <p style="display:inline-block;margin-top:30px;">
                <textarea disabled >${device.hints}</textarea>
            </p>
            <p style="margin-top:30px;">
                <input type="button" id="payBtn" value="立即支付">
            </p>
        </form>
    </div>
<script type="text/javascript">

    //表单验证
    $(function() {

        //$('.mui-title').html("在线充值");
        $("title").html("在线充值");

        $("#payBtn").unbind('click', function () {
        });
        $("#payBtn").bind('click', function () {

            var v_selectPlan = $("#selectPlan").val();
            console.info("v_selectPlan="+v_selectPlan);

            if (v_selectPlan.length == 0 ) {
                layer.msg('请选择充值套餐！', {
                    icon: 5
                });
                return false;
            }
            f_pay();

           // $("#formadd").submit();
        });
    });
    function f_pay(){
        var v_ret = true;
        var v_data = $("#selectPlan").val();
        var  v_datas= v_data.split(",");
        var amount = v_datas[1];
        if(amount == ''){
            return false;
        }
        var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
        if(!exp.test(amount)){
           // $("#errorMsg").html("<lable style=\"color:red\">输入的金额不正确</lable>");
            layer.msg('输入的金额不正确！', {
                icon: 5
            });
            return false;
        }

        var v_payType="${payType}";
        var v_deviceId=$("#deviceId").val();
        var v_planId = v_datas[0];
        var v_orderMoney=amount;
        var v_userId="${userId}";
        var v_cardId = "${card.id}";
        var v_actualAmount = v_datas[3];

       // alert(v_data);

       //alert("payType="+v_payType+" : v_deviceId="+v_deviceId+" : v_planId="+v_planId+" : orderMoney="+v_orderMoney   +"\n : userId="+v_userId+",actualAmount="+v_actualAmount+"\n,data="+v_data);
        var args = {"payType": v_payType, "deviceId": v_deviceId, "planId":v_planId,
                    "orderMoney": v_orderMoney, "userId": v_userId,"cardId":v_cardId,
                    "actualAmount":v_actualAmount};
        $.ajax({
            cache: false,
            type: "POST",
            url: '<%=request.getContextPath()%>/trade/createOrder.html', //请换上自己的接口地址
            dataType: "json",
            async: false,
            data: args,//JSON.stringify({ pId:value }),
            timeout: 30000,
            error: function (e) {
                alert("[error] : " + e.responseText);

            },
            success: function (data) {

                if(data.success){
                    v_url = "<%=request.getContextPath()%>/trade/payinfo.html?orderNo="+data.data;
                    //支付宝
                    if(data.payType=="1"){
                        tradePay(data.tradeNo,v_url);
                    }
                    else if(data.payType=="2"){
                        v_url = decodeURIComponent(v_url + "&id="+10000*Math.random());
                        wxTradePay1(data.appId,data.timeStamp,data.nonceStr,data.prepayId,data.paySign,v_url);
                    }
                    //layer.msg('验证成功！',{icon:1});
                    //layer.closeAll();
                }else{
                    layer.msg('验证失败:'+data.message,{icon:5});
                    //window.location.href= "<%=request.getContextPath()%>/trade/error.html?orderNo="+data.data+"&merId="+$("#merId").val();
                }
            }
        });
        return true;
    }
    function wxTradePay1(v_appId,v_timeStamp,v_nonceStr,v_prepay_id,v_paySign,v_url){
        //alert("[1] ; "+v_url);
        if (typeof WeixinJSBridge == "undefined"){
            //alert("[111] ; "+v_url)
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady1, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady1);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady1);
            }
        }else{
            //alert("[2] ; "+v_url);
            onBridgeReady1(v_appId,v_timeStamp,v_nonceStr,v_prepay_id,v_paySign,v_url);
        }
    }

    function onBridgeReady1(v_appId,v_timeStamp,v_nonceStr,v_prepay_id,v_paySign,v_url){
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":v_appId,
                    "timeStamp":v_timeStamp,
                    "nonceStr":v_nonceStr,
                    "package":"prepay_id="+v_prepay_id,
                    "signType":"MD5",
                    "paySign":v_paySign
                },     function(res){
                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                        window.location.href= v_url;
                    }
                    else{

                        var msg = res.err_msg;
                        var v_payStatus= "2";
                        if (msg == "get_brand_wcpay_request:cancel") {
                            var err_msg = "您取消了微信支付";
                            v_payStatus= "3";
                        } else if (res.err_code == 3) {
                            var err_msg = "您正在进行跨号支付正在为您转入扫码支付......";
                            v_payStatus="0";
                        } else if (msg == "get_brand_wcpay_request:fail") {
                            var err_msg = "微信支付失败错误信息：" + res.err_desc;
                        } else {
                            var err_msg = msg + "" + res.err_desc;
                        }
                        //alert(err_msg);
                        if(v_payStatus!="0"){
                            f_updatePayStatus(v_url+"&payStatus="+v_payStatus);
                        }
                        window.location.href= v_url.replace("payinfo.html","error.html")+"&msg="+encodeURI(encodeURI(err_msg));
                    }
                }
        );
    }


    $(".a_3d").click(function(){
        var v_id = $(this).attr('id');

        //alert($(this).attr("data")+","+$(this).text());
       if($(this).hasClass("a_3d")){
           $(this).removeClass("a_3d");
           $(this).addClass("a_2d");

           $("#selectPlan").val($(this).attr("id")+","+$(this).attr("data")+","+$(this).text()+","+$(this).attr("pdata"));
           //alert("selectPlan="+ $("#selectPlan").val());
       }
       else{
           $(this).addClass("a_3d");
           $(this).removeClass("a_2d");
           $("#selectPlan").val("");
       }


        $(".form-4 a").each(function(){
            //alert("[for] : "+v_id +" -> "+ $(this).attr("id"));
           if(v_id!=$(this).attr("id")) {

               $(this).addClass("a_3d");
               $(this).removeClass("a_2d");

           }
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