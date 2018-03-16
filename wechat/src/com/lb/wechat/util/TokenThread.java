package com.lb.wechat.util;

import com.lb.wechat.entity.AccessToken;

import java.util.Date;

public class TokenThread  implements Runnable
{
    public static String appid = "";
    public static String appsecret = "";
    public static String domain = "";
    public static String apptoken = "";
    public static AccessToken accessToken = null;

    public void run()
    {
        try
        {
            for (;;)
            {
                accessToken = WechatUtil.getAccessToken(appid, appsecret);
                if (accessToken != null)
                {
                    System.out.println(new Date() + "  获取access_token成功，有效时长{}秒 token:{}" + "--" + accessToken.getExpiresIn() + "--" + accessToken.getAccessToken());

                    Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
                    System.out.println(new Date() + "  休眠" + (accessToken.getExpiresIn() - 200) + "秒以后，重新获取token: ");
                }
                else
                {
                    Thread.sleep(60000L);
                    System.out.println(new Date() + "  如果access_token为null，60秒后再获取.");
                }
            }
        }
        catch (InterruptedException e)
        {
            try
            {
                Thread.sleep(60000L);
            }
            catch (InterruptedException e1)
            {
                System.err.println("获取access_token时出错");
            }
            System.err.println("获取access_token时出错");
        }
    }
}