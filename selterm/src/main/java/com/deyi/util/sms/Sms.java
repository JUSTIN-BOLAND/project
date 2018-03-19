package com.deyi.util.sms;

import com.deyi.controller.TradePageContrller;
import com.deyi.util.HttpClient;
import com.deyi.util.RandomUtil;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;

/**
 * Created by root on 2018/2/11 0011.
 */
public class Sms {
    private static final Logger log = LoggerFactory.getLogger(Sms.class);
    private String userId;
    private String account;
    private String passwd;

    public String getSmsCode() {
        return smsCode;
    }

    private String smsCode;



    private String smsUrl ;

    public Sms(String userId,String account,String passwd){
        this.userId = userId;
        this.account = account;
        this.passwd = passwd;
        smsUrl="http://121.8.199.27:8868/sms.aspx?action=send&userid="+userId+"&account="+account+"&password="+passwd;
    }
    public String getSmsRandCode(){
        return RandomUtil.randomNum(6);
    }
    public void send(String mobile,String title,String operate){
        this.smsCode = getSmsRandCode();
        String content = String.format("【%s】 您正在进行%s操作，验证码为：%s  ，请忽告知他人！",title,operate,smsCode);
        this.send(mobile,content);
    }
    public void send(String mobile,String content) {

        //this.smsUrl += "&mobile=" + mobile + "&content=" + content + "&sendTime=&extno=";
        String requestParam  = "&mobile=" + mobile + "&content=" + content + "&sendTime=&extno=";
        System.out.println("requestParam="+requestParam);
        System.out.println("smsUrl="+smsUrl);
        try {
            String result = HttpClient.send(smsUrl, requestParam, "UTF-8", "UTF-8");
           log.info("result="+result);
            System.out.println("result="+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Sms sms = new Sms("92","sbt2","sbt20180207");
        sms.send("13592573831","扫扫通","用户注册");
    }
}
