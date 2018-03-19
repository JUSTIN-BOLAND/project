package com.deyi.util.sms;

/**
 * Created by root on 2018/2/11 0011.
 */
public class SmsUtil {
    private static  Sms sms;
    static{
        sms = new Sms("92","sbt2","sbt20180207");
    }

    public static String  send(String mobile,String operate){
         sms.send(mobile,"扫扫通",operate);
         return sms.getSmsCode();
    }
    public static void main(String[] args){
       System.out.println( SmsUtil.send("13592573831","密码重置"));
    }
}
