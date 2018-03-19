/**
 * Created by root on 2018/2/7 0007.
 */
var os = function() {
    var ua = navigator.userAgent,
        isQB = /(?:MQQBrowser|QQ)/.test(ua),
        isWindowsPhone = /(?:Windows Phone)/.test(ua),
        isSymbian = /(?:SymbianOS)/.test(ua) || isWindowsPhone,
        isAndroid = /(?:Android)/.test(ua),
        isFireFox = /(?:Firefox)/.test(ua),
        isChrome = /(?:Chrome|CriOS)/.test(ua),
        isIpad = /(?:iPad|PlayBook)/.test(ua),
        isTablet = /(?:iPad|PlayBook)/.test(ua)||(isFireFox && /(?:Tablet)/.test(ua)),
        isSafari = /(?:Safari)/.test(ua),
        isPhone = /(?:iPhone)/.test(ua) && !isTablet,
        isOpera= /(?:Opera Mini)/.test(ua),
        isUC = /(?:UCWEB|UCBrowser)/.test(ua),
        isWeixin= /(?:MicroMessenger)/.test(ua),
        isBaiduWallet= /(?:BaiduWallet)/.test(ua),
        isPc = !isPhone && !isAndroid && !isSymbian;
    return {
        isQB: isQB,
        isTablet: isTablet,
        isPhone: isPhone,
        isAndroid : isAndroid,
        isPc : isPc,
        isOpera : isOpera,
        isUC: isUC,
        isIpad : isIpad,
        isWeixin : isWeixin,
        isBaiduWallet : isBaiduWallet
    };
}();
//平台、设备和操作系统
var system ={
    win : false,
    mac : false,
    xll : false
};
//检测平台
var p = navigator.platform;
system.win = p.indexOf("Win") == 0;
system.mac = p.indexOf("Mac") == 0;
system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
function f_checkOs(v_url) {
//跳转语句
    if (system.win || system.mac || system.xll) {//转向后台登陆页面
        window.location.href = v_url;
    } else {
    }
    var userAgent = navigator.userAgent;
    if (userAgent.indexOf("AlipayClient") > -1) {
        //window.location.href = '${aliAuthUrl}';

    } else if (userAgent.indexOf("MicroMessenger") > -1) {
        //window.location.href = '${wxAuthUrl}';
    }
    else {
        window.location.href = v_url;
    }
}
function f_OsType(){
    var userAgent = navigator.userAgent;
    if (userAgent.indexOf("AlipayClient") > -1) {
        return 1;

    } else if (userAgent.indexOf("MicroMessenger") > -1) {
       return 2;
    }
    else {
       return 0;
    }
}

function f_validIdCard(sId){
    var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
    var iSum=0 ;
    var info="" ;

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
function f_regx(r,s)
{
    if (r == null || r == ""){
        return false;
    }
    var patrn= new RegExp(r, 'ig');
    if (patrn.exec(s)) return true
    else return false
}
function f_regExp(r,s)
{
    if (r == null || r == ""){
        return "";
    }
   // alert("f_regExp : "+r+":"+s);
    var patrn= new RegExp(r, 'ig');
    var v_result = patrn.exec(s) ;
    if(v_result.length == 0 ) return "";
    return v_result[0];

}
function f_validField(v_dataType,v_value){

    if(f_regx("^n(\\d|\\{)+",v_dataType)){

        var v_result = f_regExp("\\{([^{}]+)\\}",v_dataType);

        v_reg="^\\d"+v_result+"$";

    }
    else if(v_dataType=="account"){
        v_reg="^[^\\u4E00-\\u9FA5\\uf900-\\ufa2d]{2,10}$";

    }
    else if(v_dataType=="money"){
        v_reg="^\\d+(\\.\\d{1,2})?$";

    }
    else if(v_dataType=="name"){
        v_reg="^[\\u4E00-\\u9FA5\\uf900-\\ufa2d]{2,6}$";

    }
    else if(v_dataType=="idCard"){
        //alert(v_dataType+" -> "+v_value);
        return f_validIdCard(v_value);

    }
    else if(v_dataType=="m"){
        v_reg="^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$";

    }
    //alert(v_reg+" : "+v_value);
    return f_regx(v_reg,v_value);
}
var v_isSmsSend = false;
var v_gSmsCode="";

function f_validForm() {
    var v_result = true;
    $("input").each(function(){
       // console.log("[f_validForm] : type="+$(this).attr("type"));
        if($(this).attr("type") == "text" ||  $(this).attr("type") == "password")
        {
            var v_title = $(this).parent().prev().html();
            var v_id= $(this).attr("id");
            var v_value =  $(this).val();
            var v_dataType = $(this).attr("datatype");
            var v_errormsg = $(this).attr("errormsg");
            if(v_id == "validCode" && !v_isSmsSend){
                layer.msg("请发送短信校验码!", {
                    icon: 5
                });
                $(this).focus();
                v_result = false;
                return false;
            }
            //console.log("[f_validForm] : type="+$(this).attr("type")+",v_title="+v_title+",v_value="+v_value+",len="+v_value.length)
            if (v_value.length == 0) {
                console.log("[f_validForm] :v_id="+v_id+",v_isSmsSend="+ v_isSmsSend);


                    layer.msg(v_title + "不能为空", {
                        icon: 5
                    });
                    $(this).focus();
                    v_result = false;
                    return false;

            }
            else {

                v_result = f_validField(v_dataType, $(this).val());
                //console.log("[f_validForm->not null] : type="+$(this).attr("type")+",v_result="+v_result);
                if ( v_result==false) {
                    //console.log("[f_validForm->not null] : v_errormsg="+v_errormsg+",v_result="+v_result+",v_dataType="+v_dataType);
                    if(v_dataType=="idCard") {
                        v_errormsg = v_result;
                        //alert(v_dataType+" : "+v_errormsg+" : "+v_result);
                    }
                    layer.msg(v_errormsg, {
                        icon: 5
                    });
                    $(this).focus();
                    v_result = false;
                    return false;
                }
                else{
                    //验证短信校验码


                    if(v_id == "validCode" && v_isSmsSend){
                         if(v_gSmsCode != v_value){
                             layer.msg("输入的校验码有误!", {
                                 icon: 5
                             });
                             $(this).focus();
                             v_result = false;
                             return false;
                         }
                    }
                }
            }
        }
    });
    console.log("[f_validForm] : v_result="+v_result);
    return v_result;

}


/*获取验证码*/
var time =60;
var i=null;

function getTimer(){
    time--;
    if(time==0){
        clearInterval(i);
        $("#sendSmsCode").show();
        $("#SmsCodeTime").hide();
        time=60;
        v_isSmsSend = false;
    }
    $("#SmsCodeTime").find("del").text(time);
}

$(function() {
    $("#sendSmsCode").click(function() {
        //alert("lb");
        var v_domainUrl = $(this).attr("domainUrl");
        var v_optType = $(this).attr("optType");
        var v_mobile = $("#mobile").val();
        console.log("[sendSmsCodeClk] :v_domainUrl="+v_domainUrl+",v_optType="+ v_optType+",v_mobile="+v_mobile);
        if( v_mobile.length == 0 ) {
            layer.msg('请输入手机号！', {
                icon : 5
            });
            return ;
        }
        if( f_smsSend(v_domainUrl,v_mobile,v_optType))
        {
            v_isSmsSend = true;
            $("#sendSmsCode").hide();
            $("#SmsCodeTime").show();
            i=setInterval(getTimer,1000);

        }
        else
        {
            layer.msg('短信发送失败！', {
                icon : 5
            });


        }

    });
});function f_isExistAjax(v_domainUrl,v_url){
    var v_result = false;

    $.ajax({
        url: v_domainUrl+v_url,
        context: document.body,
        async: false,
        timeout:5000,
        success: function(date){
            v_result = date;
        }
    });
    return v_result;
}


function f_isExistCardName(v_domainUrl){

    var v_cardNo = $("#name").val();

    return f_isExistAjax(v_domainUrl,'/trade/uniqueCardName.html?cardName='+v_cardNo);
}

function f_isExistCardNo(v_domainUrl){

    var v_cardNo = $("#cardNo").val();
    var v_deviceId =  $("#deviceId").val();


    return f_isExistAjax(v_domainUrl,'/trade/isExistCard.html?cardNo='+v_cardNo+"&deviceId="+v_deviceId);
}


function f_smsSend(v_domainUrl,v_mobile,v_optType){
    var v_isSend = true;


    var args = {

        "mobile":v_mobile,
        "operate":v_optType
    };
    $.ajax({
        cache:false,
        type:"POST",
        url:v_domainUrl+"/trade/sendSms.html", //请换上自己的接口地址

        dataType:"json",
        //contentType: 'application/json',
        async:false,

        data:args,//JSON.stringify({ pId:value }),
        timeout:30000,
        error:function(e){
            alert("[error] : "+ e.responseText);

        },
        success:function(data){


            if(data.length == 0)
            {
                alert('[failure] : '+data.msg);
                v_isSend = false;
            }
            else {
                v_gSmsCode=data;
                v_isSend = true;
            }

        }
    });
    return  v_isSend;
}
function f_ajaxUpdate(v_domain, v_method){
    $.ajax({
        url: v_domain+"/trade/"+v_method+".html",
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
$(function() {
    if($("#userCenterSpan").length > 0 )
    {
        $("#userCenterSpan").css("cursor","pointer");
        $("#userCenterSpan").unbind('click', function () {
        });
        $("#userCenterSpan").bind('click', function () {
            window.location.href = "<%=request.getContextPath()%>/trade/toUserCenter.html";
        });
    }
});