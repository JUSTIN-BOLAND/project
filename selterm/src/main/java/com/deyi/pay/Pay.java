package com.deyi.pay;

import com.deyi.dao.DealerPayMapper;
import com.deyi.dao.DeviceMapper;
import com.deyi.dao.FacilorMapper;
import com.deyi.dao.WxPublicMapper;


import com.deyi.entity.DealerPay;
import com.deyi.entity.Device;
import com.deyi.entity.Facilor;
import com.deyi.util.PayUtils;
import com.deyi.util.PropertiesUtil;
import com.deyi.util.RandomUtil;

import com.lb.al.AliPayPageInf;
import com.lb.msg.AliMsg;
import com.lb.wx.*;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by root on 2018/2/16 0016.
 */
@Service
public class Pay {
    @Autowired
    private DeviceMapper deviceDao;
    @Autowired
    private DealerPayMapper dealerPayDao;
    @Autowired
    private FacilorMapper facilorMapper;
    /**
     *
     * @param reqValues 0:payType 1:deviceId 2: orderMoney 3 : userId  4:subAppId 5 : notifyUrl 6:body  7:loginUserId
     * @return
     */
    public String  createOrder(String[] reqValues) {
       // ReturnVo<Object> vo =new ReturnVo<>();
        JSONObject json = new  JSONObject();
        if(reqValues != null) {


            String notifyUrl = reqValues[5];
            if( notifyUrl == null) notifyUrl ="";

            String reqName = "支付类型,设备编号,金额,授权用户,子商户Appid,通知URL,订单号";
            String[] reqNames =reqName.split(",");
            int[]  noCheck = new int[]{4};

            String[] checkRet = this.checkInputParameter(reqNames,reqValues,noCheck);
            if(checkRet != null){
                json.put("success", "0");

                json.put("resultCode", "1");

                json.put("errCode", checkRet[0]);
                json.put("errCodeDes", checkRet[1]);
                return json.toString();
            }
            Double moneyRate = 1d;
            String moneyRateStr =    PropertiesUtil.getProperty("money_rate");
            moneyRateStr = (moneyRateStr==null || moneyRateStr.trim().length() ==0  ? "1":moneyRateStr);
            try {

                moneyRate = Double.parseDouble(moneyRateStr);
            } catch (NumberFormatException e) {

            }
            String amount = reqValues[2].trim();
            Double dAmount=0d;
            try {
                dAmount = Double.parseDouble( amount);



                dAmount = dAmount / moneyRate;

            } catch (NumberFormatException e) {

                json.put("success", "0");
                json.put("resultCode", "1");

                json.put("errCode", "-7");
                json.put("errCodeDes","金额必须是数字");
                return json.toString();

            }
            if(dAmount <=0 ){

                json.put("success", "0");
                json.put("resultCode", "1");

                json.put("errCode", "-8");
                json.put("errCodeDes","金额必须大于0");
                return json.toString();

            }
            Device device = deviceDao.getDevice(Integer.parseInt(reqValues[1]));
            if(device==null){
                json.put("success", "0");
                json.put("resultCode", "1");

                json.put("errCode", "-9");
                json.put("errCodeDes","该设备不存在");
                return json.toString();
            }
            amount = dAmount+"";
            //生成订单号
            String orderNo = reqValues[6];
            String payType = reqValues[0];
            String  body = device.getDeviceName();
            String subject = device.getDeviceName();
            DealerPay dealerPay =  dealerPayDao.getDealerPayByDeviceId(Integer.parseInt(reqValues[1]));

            if("1".equals(reqValues[0].trim())) {
                //获取授权用户编号
                Facilor facilor = facilorMapper.selectByPrimaryKey(Long.valueOf(2));
                AliMsg aliMsg = new AliMsg();

                aliMsg.setDomain(PropertiesUtil.getProperty("ali_api_domain"));
                aliMsg.setAppId(facilor.getAppid());
                aliMsg.setPrivateKey(facilor.getSecret());
                aliMsg.setAliPublicKey(facilor.getKey());
                aliMsg.setSignType("RSA2");
                AliPayPageInf aliPayPageInf = new AliPayPageInf(aliMsg);

                String al_unotify_url = PropertiesUtil.getProperty("al_unotify_url");

                Map<String,String> hms = new LinkedHashMap<String,String>();
                hms.put("appAuthToken",dealerPay.getAppAuthToken());
                hms.put("sellerId",facilor.getMchid());
                hms.put("buyerId",reqValues[3]);
                hms.put("amount",amount);
                hms.put("orderNo",orderNo);
                hms.put("sysServiceProviderId",facilor.getMchid());
                hms.put("timeoutExpress","2");

                hms.put("subject",subject);
                hms.put("body",body);
                if(al_unotify_url!=null && al_unotify_url.trim().length() > 0 ) hms.put("notifyUrl",al_unotify_url);
                String tradeNo = aliPayPageInf.trade_create(hms);
                System.out.println("[createOrder] : aliPayPageInf="+aliPayPageInf.getErrorCode()+"+ -> "+aliPayPageInf.getErrorMsg());

                if(aliPayPageInf.getErrorCode() == 0 ){
                    System.out.println("[createOrder] : tradeNo="+tradeNo);
                    tradeNo = (tradeNo==null ? "":tradeNo);
                    json.put("success", "0");

                    json.put("resultCode", "0");
                    json.put("tradeNo", tradeNo);
                    json.put("orderNo", orderNo);

                }
                else{

                    json.put("success", "0");json.put("message", "");
                    json.put("resultCode", "1");

                    json.put("errCode", aliPayPageInf.getErrorCode());
                    json.put("errCodeDes", aliPayPageInf.getErrorMsg());
                    return json.toString();
                }


            }
            else if("2".equals(reqValues[0].trim())) {

                String curDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

                /// WxTradePay wxTradePay= new WxTradePay();
                try {
                    String wx_unotify_url = PropertiesUtil.getProperty("wx_unotify_url");

                    WxPublic wxPublic = new WxPublic();
                    wxPublic.setMchId(dealerPay.getWxMchId());
                    //FacilorMapper facilorMapper = context.getBean(FacilorMapper.class);
                    Facilor facilor = facilorMapper.selectByPrimaryKey(Long.valueOf(1));
                    com.lb.wx.Facilor wxFacilor = new com.lb.wx.Facilor();
                    wxFacilor.setAppid(facilor.getAppid());
                    wxFacilor.setId(facilor.getId());
                    wxFacilor.setKey(facilor.getKey());
                    wxFacilor.setSecret(facilor.getSecret());
                    wxFacilor.setCertpath(facilor.getCertpath());
                    wxFacilor.setMchid(facilor.getMchid());
                    WxTradePay wxTradePay = new WxTradePay(wxFacilor,wxPublic);
                    //wxTradePay.setWxNotifyUrl( URLEncoder.encode(wx_unotify_url));
                    if(wx_unotify_url!=null &&wx_unotify_url.trim().length() > 0 ) wxTradePay.setWxNotifyUrl( wx_unotify_url);
                    ResponseTrade trade = wxTradePay.create( reqValues[4],reqValues[3],null,orderNo,amount,body);
                    System.out.println("[createOrder] : WxErrorCode="+trade.getErrorCode());
                    if(PayStatusEmum.SUCCESS.getValue().toString().equals(trade.getErrorCode())) {
                        String prepayId = trade.getWxUnifiedRes().getPrepay_id();

                        WxMicroEntity microEntity = new WxMicroEntity();
                        String nonceStr = PayUtils.getRandomString(16);
                        //	String wx_unotify_url = PropertiesUtil.getProperty("wx_unotify_url");
                        String[] params = {"appId="+facilor.getAppid(),
                                "timeStamp="+curDate,
                                "nonceStr="+nonceStr,
                                "package=prepay_id="+prepayId,
                                "signType=MD5"};
                        String sign = this.sign(params, facilor.getKey());
                        String wxJs ="[\"" + facilor.getAppid() + "\",\"" + curDate + "\",\"" + nonceStr + "\",\"" + prepayId + "\",\"" + sign + "\" ]";


                        json.put("success", "0");

                        json.put("resultCode", "0");
                        json.put("tradeNo", wxJs);
                        json.put("orderNo", orderNo);
                        json.put("errCode", "");
                        json.put("errCodeDes", "");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    json.put("success", "0");

                    json.put("resultCode", "1");

                    json.put("errCode", "-100");
                    json.put("errCodeDes", e.getMessage().substring(0,10));
                    return json.toString();
                }




            }

        }

        return json.toString();

    }


    protected  String sign(String[] params, String key) throws UnsupportedEncodingException {

        String param = PayUtils.genSortParams(params);
        System.out.println("[sign] : param=" + param + ",key="+key+"");
        String sign = DigestUtils.md5Hex((param+"&key="+key).getBytes("utf-8")).toUpperCase();
        System.out.println("[sign] : sign=" + sign + "");
        return sign;
    }
    public String[] checkInputParameter(String[] names ,String[] values,int[] notCheck){
        String[] ret = null;
        boolean isCheck = true;
        for(int i=0; i< values.length ; i++){

            isCheck = true;
            if(notCheck!=null) {
                for (int j = 0; j < notCheck.length; j++) {
                    this.log("checkInput","["+i+" :"+j+"] : "+notCheck[j]+" -> isCheck="+isCheck);
                    if (notCheck[j] == i) {
                        isCheck = false;
                        this.log("checkInputParameter",i+" : "+notCheck[j]+" -> isCheck="+isCheck);
                        break;
                    }
                }
            }

            if(!isCheck) continue;
            if(values[i] == null || values[i].trim().length() ==0 ){
                this.log("checkInputParameter",i+" -> "+names[i]);
                return new String[]{(0-i-1)+"",names[i]+"不能为空"};
            }
        }
        return ret;

    }
    protected  void log(String method,String msg){
        System.out.println("【"+method+"】 : "+ msg);
    }
}
