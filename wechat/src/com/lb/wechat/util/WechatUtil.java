package com.lb.wechat.util;

import com.lb.wechat.entity.AccessToken;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by root on 2018/3/16 0016.
 */
public class WechatUtil {

    public static final String AppID = TokenThread.appid;
    public static final String AppSecret = TokenThread.appsecret;
    public static final String domain = TokenThread.domain;

    public static AccessToken getAccessToken(String appid, String appsecret){
        AccessToken token = null;
        JSONObject jsonObject = null;
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        try {
            String ret = HttpClient.send(url,null,"UTF-8","UTF-8");
            System.out.println(ret);
            jsonObject = JSONObject.fromObject(ret);
            if(jsonObject != null) {
                token = new AccessToken();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String respMessage = null;
        try
        {
            Map<String, String> requestMap = MessageUtil.parseXml(request);


            String fromUserName = (String)requestMap.get("FromUserName");

            String toUserName = (String)requestMap.get("ToUserName");

            String msgType = (String)requestMap.get("MsgType");

            String eventType = (String)requestMap.get("Event");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return respMessage;
    }

    public static String getFileExt(String contentType)
    {
        String fileExt = "";
        if ("image/jpeg".equals(contentType)) {
            fileExt = ".jpg";
        } else if ("audio/mpeg".equals(contentType)) {
            fileExt = ".mp3";
        } else if ("audio/amr".equals(contentType)) {
            fileExt = ".amr";
        } else if ("video/mp4".equals(contentType)) {
            fileExt = ".mp4";
        } else if ("video/mpeg4".equals(contentType)) {
            fileExt = ".mp4";
        }
        return fileExt;
    }

    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
