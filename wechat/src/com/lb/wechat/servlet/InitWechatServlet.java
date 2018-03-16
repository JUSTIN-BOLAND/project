package com.lb.wechat.servlet;


import com.lb.wechat.util.TokenThread;
import com.lb.wechat.util.WechatUtil;

import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitWechatServlet
        extends HttpServlet
{
    public void init()
            throws ServletException
    {
        TokenThread.appid = getInitParameter("appid");
        TokenThread.appsecret = getInitParameter("appsecret");
        TokenThread.domain = getInitParameter("domain");
        TokenThread.apptoken = getInitParameter("apptoken");
        System.out.println("初使化参数{AppID}：" + WechatUtil.AppID);
        System.out.println("初使化参数{AppSecret}：" + WechatUtil.AppSecret);
        System.out.println("初使化参数{domain}：" + TokenThread.domain);
        System.out.println("初使化参数{apptoken}：" + TokenThread.apptoken);
        if (("".equals(TokenThread.appid)) || ("".equals(TokenThread.appsecret))) {
            System.err.println("appid and appsecret configuration error, please check carefully.");
        } else {
            new Thread(new TokenThread()).start();
        }
    }
}
