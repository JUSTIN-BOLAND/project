/**
 * Created by root on 2017/10/31 0031.
 */


function log(obj) {
    $("#log").append(obj).append(" ").append("<br />");
}
// 由于js的载入是异步的，所以可以通过该方法，当AlipayJSBridgeReady事件发生后，再执行callback方法
function ready(callback) {
    if (window.AlipayJSBridge) {
        callback && callback();
    } else {
        document.addEventListener('AlipayJSBridgeReady', callback, false);
    }
}

function tradePay(tradeNO,v_url) {
    ready(function(){
        // 通过传入交易号唤起快捷调用方式(注意tradeNO大小写严格)
        AlipayJSBridge.call("tradePay", {
            tradeNO: tradeNO
        }, function (data) {
            //log(JSON.stringify(data));
            if ("9000" == data.resultCode) {
                //log("支付成功");
                window.location.href= v_url;
            }
            else{
                  var v_payStatus= "2";
                  if("8000" == data.resultCode) {
                      v_msg="正在处理中";
                      v_payStatus="0";
                  }
                 else  if("7001" == data.resultCode) {
                      v_msg="取消支付宝支付";
                      v_payStatus="3";
                  }
                  else  if("4000" == data.resultCode) v_msg="支付宝支付失败";
                  else  if("6004" == data.resultCode) v_msg="网络异常";
                  else  if("6002" == data.resultCode) v_msg="网络异常";
                  else  if("6001" == data.resultCode) v_msg="取消支付宝支付";
                // log("支付失败:tradeNo=${tradeNo}  \n"+ JSON.stringify(data));
                if(v_payStatus!="0"){
                    f_updatePayStatus(v_url+"&payStatus="+v_payStatus);
                }
                window.location.href= v_url.replace("payinfo.html","error.html")+"&msg="+encodeURI(encodeURI(v_msg));

            }
        });
    });
}

function wxTradePay(v_appId,v_timeStamp,v_nonceStr,v_prepay_id,v_paySign,v_url){
    alert("[1] ; "+v_url);
    if (typeof WeixinJSBridge == "undefined"){
        alert("[111] ; "+v_url)
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        alert("[2] ; "+v_url);
        onBridgeReady(v_appId,v_timeStamp,v_nonceStr,v_prepay_id,v_paySign,v_url);
    }
}
function f_updatePayStatus(v_url){
    var v_statusUrl = v_url.replace("payinfo.html","updatePayStatus.html");
    var v_result = false;

    $.ajax({
        url: v_statusUrl,
        context: document.body,
        async: false,
        timeout:5000,
        success: function(date){
            v_result = true;
        }
    });
    return v_result;
}
function onBridgeReady(v_appId,v_timeStamp,v_nonceStr,v_prepay_id,v_paySign,v_url){
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
                if (msg == "get_brand_wcpay_request:cancel") {
                    var err_msg = "您取消了微信支付";
                } else if (res.err_code == 3) {
                    var err_msg = "您正在进行跨号支付正在为您转入扫码支付......";
                } else if (msg == "get_brand_wcpay_request:fail") {
                    var err_msg = "微信支付失败错误信息：" + res.err_desc;
                } else {
                    var err_msg = msg + "" + res.err_desc;
                }
                alert("fail : " +err_msg+"\n"+ v_url);
            }
        }
    );
}
