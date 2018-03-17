package com.secray.utils.common;

import sun.rmi.runtime.*;

/**
 * Created by root on 2017/7/24 0024.
 */
public class LogUtil {
    private static  long start = System.currentTimeMillis();
    public static void log(String method,String msg){
        long end  = System.currentTimeMillis();
        System.out.println("【"+method+"】【"+(end - start )/1000+"秒】 : " + msg );
        start = System.currentTimeMillis();

    }

    public static  void main(String[] args){
        String domain="www.bailu.cn";
        String httpCode = ShellUtil.executeRet(" curl -sL -w '%{http_code}' '"+ domain + "' -o /dev/nu" );
        httpCode = StrUtil.trimEnter(httpCode);
        LogUtil.log("tt",httpCode + " -> " +httpCode.length() +" : "+ httpCode.trim().length());
        if(httpCode.equals("200")){
            LogUtil.log("tt","==");
        }
        else
        {
            LogUtil.log("tt","<>");
        }
        long start= 2012;
        long end=102012;
        System.out.println("【method】【"+(end - start )/1000+"秒】 : "  );
    }

}
